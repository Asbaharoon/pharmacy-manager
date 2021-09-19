package controller;

import entity.Commande;
import entity.CommandeProduit;
import entity.Fournisseur;
import entity.Produit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CommandeDAO;
import model.CommandeProduitDAO;
import model.ProduitDAO;
import util.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CommandeMajController implements Initializable {

    public TextField dateHeureTextField;
    public ComboBox<Fournisseur> fournisseursComboBox;
    public Button produitsSelectionButton;
    public TableView selectedProdsTableView;
    public TextField prixTotalTextField;
    public ToggleButton etatToggleButton;
    public TextField resteTextField;

    public Button mainButton;

    public ImageView statusIcon;
    public Label feedbackLabel;
    public Label titleLabel;
    public ImageView titleIcon;

    private CommandeController parentController;
    private ProduitDAO produitDAO;
    private long id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produitDAO = new ProduitDAO();

        dateHeureTextField.setText(Utils.setCurrentDateTime());
        selectedProdsTableView.layout();
        selectedProdsTableView.setEditable(true);
        //ProduitsTableViewUtil.fillTableColumnsVerCmd(selectedProdsTableView);
        CommandeProduitTableViewUtil.fillTableColumns(selectedProdsTableView);
        ComboBoxUtil.fillFournisseursComboBox(fournisseursComboBox);
    }

    public void setType(int type) {
        if (type == 1) {
            mainButton.setOnAction(this::onClickModifyCmd);
            titleLabel.setText("Modifier une commande");
            titleIcon.setImage(new Image("images\\edit_grey.png"));
        }
    }

    public void onClickOpenSelectionWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/select_produit_popup_view.fxml"));
        Parent node = loader.load();
        ProduitsSelectionPopupController popupController = loader.getController();
        popupController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Selectionne des produits");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void onClickAddCommande(ActionEvent actionEvent) {
        // setup commande insertion
        try {
            Commande commande = new Commande();
            commande.setDateHeuere(Utils.stringToLocalDateTime(dateHeureTextField.getText()));
            setCommande(commande);
            new CommandeDAO().save(commande);

            // setup commande produit insertion

            ArrayList<CommandeProduitHelper> prods = new ArrayList<>();
            for (int i = 0; i < selectedProdsTableView.getItems().size(); i++) {
                prods.add((CommandeProduitHelper) selectedProdsTableView.getItems().get(i));
            }
            prods.forEach(prod -> {
                new CommandeProduitDAO().save(new CommandeProduit(produitDAO.getById(prod.getId()), commande, Integer.parseInt(prod.getQuantite())));
            });
            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, "Un problème est survenu, vérifiez les informations saisies", "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void onClickModifyCmd(ActionEvent actionEvent) {
        try {
            Commande commande = new Commande();
            commande.setId(this.id);
            commande.setDateHeuere(Utils.formatDateTime(dateHeureTextField.getText()));
            setCommande(commande);
            new CommandeDAO().update(commande);
            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, "Un problème est survenu, vérifiez les informations saisies", "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void toggleResteTextField() {
        resteTextField.setDisable(etatToggleButton.isSelected());
    }

    public byte getEtatPaiment() {
        byte etat = 0;
        if (!etatToggleButton.isSelected()) {
            etat = 1;
        }
        return etat;
    }

    public void updateTableView(List<Produit> products) {
        //CommandeProduitTableViewUtil.insertTableData(selectedProdsTableView, products);
        CommandeProduitTableViewUtil.insertData(selectedProdsTableView, products);
    }

    public void setCommande(Commande commande) {
        commande.setFournisseur(fournisseursComboBox.getSelectionModel().getSelectedItem());
        commande.setEtatPaiment(getEtatPaiment());
        commande.setPrixtotal(getPrixTotal());
        if (!etatToggleButton.isSelected()) {
            commande.setRestePaiement(BigDecimal.valueOf(Long.parseLong(resteTextField.getText())));
        }
    }

    public void setFields(Commande commande) {
        this.id = commande.getId();
        dateHeureTextField.setText(commande.getDateHeuere().toString());
        fournisseursComboBox.getSelectionModel().select(commande.getFournisseur());
        List<CommandeProduit> cmdProds = new CommandeProduitDAO().getAllByCmd(commande);
        ObservableList<CommandeProduitHelper> helpingList = new CommandeProduitHelper().help(cmdProds);
        selectedProdsTableView.setItems(helpingList);
        etatToggleButton.setSelected(commande.getEtatPaiment() == 0);
        if (commande.getRestePaiement() != null) {
            resteTextField.setText(commande.getRestePaiement().toString());
        } else {
            resteTextField.setDisable(true);
        }
    }

    public void setParentController(CommandeController controller) {
        parentController = controller;
    }

    public void updateTotalPrice(MouseEvent mouseEvent) {
        BigDecimal totalPrice = getPrixTotal();
        prixTotalTextField.setText(totalPrice + " DH");
    }

    public BigDecimal getPrixTotal() {
        ArrayList<CommandeProduitHelper> prods = new ArrayList<>();
        for (int i = 0; i < selectedProdsTableView.getItems().size(); i++) {
            prods.add((CommandeProduitHelper) selectedProdsTableView.getItems().get(i));
        }
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < prods.size(); i++) {
            total = total.add(new BigDecimal(prods.get(i).getQuantite()).multiply(prods.get(i).getPrix()));
        }
        return total;
    }
}
