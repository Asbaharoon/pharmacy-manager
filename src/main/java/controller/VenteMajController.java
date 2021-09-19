package controller;

import entity.Vente;
import entity.VenteProduit;
import entity.Client;
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
import model.VenteDAO;
import model.ProduitDAO;
import model.VenteProduitDAO;
import util.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VenteMajController implements Initializable {

    public TextField dateHeureTextField;
    public ComboBox<Client> clientsComboBox;
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

    private VenteController parentController;
    private ProduitDAO produitDAO;
    private long id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produitDAO = new ProduitDAO();

        dateHeureTextField.setText(Utils.setCurrentDateTime());
        selectedProdsTableView.layout();
        selectedProdsTableView.setEditable(true);
        //ProduitsTableViewUtil.fillTableColumnsVerCmd(selectedProdsTableView);
        VenteProduitTableViewUtil.fillTableColumns(selectedProdsTableView);
        ComboBoxUtil.fillClientsComboBox(clientsComboBox);
    }

    public void setType(int type) {
        if (type == 1) {
            mainButton.setOnAction(this::onClickModifyCmd);
            titleLabel.setText("Modifier une vente");
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

    public void onClickAddVente(ActionEvent actionEvent) {
        // setup vente insertion
        try {
            Vente vente = new Vente();
            vente.setDateHeuere(Utils.stringToLocalDateTime(dateHeureTextField.getText()));
            setVente(vente);
            new VenteDAO().save(vente);

            // setup vente produit insertion

            ArrayList<VenteProduitHelper> prods = new ArrayList<>();
            for (int i = 0; i < selectedProdsTableView.getItems().size(); i++) {
                prods.add((VenteProduitHelper) selectedProdsTableView.getItems().get(i));
            }
            prods.forEach(prod -> {
                new VenteProduitDAO().save(new VenteProduit(produitDAO.getById(prod.getId()), vente, Integer.parseInt(prod.getQuantite())));
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
            Vente vente = new Vente();
            vente.setId(this.id);
            vente.setDateHeuere(Utils.formatDateTime(dateHeureTextField.getText()));
            setVente(vente);
            new VenteDAO().update(vente);
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
        //VenteProduitTableViewUtil.insertTableData(selectedProdsTableView, products);
        VenteProduitTableViewUtil.insertData(selectedProdsTableView, products);
    }

    public void setVente(Vente vente) {
        vente.setClient(clientsComboBox.getSelectionModel().getSelectedItem());
        vente.setEtatPaiment(getEtatPaiment());
        vente.setPrixtotal(getPrixTotal());
        if (!etatToggleButton.isSelected()) {
            vente.setRestePaiement(BigDecimal.valueOf(Long.parseLong(resteTextField.getText())));
        }
    }

    public void setFields(Vente vente) {
        this.id = vente.getId();
        dateHeureTextField.setText(vente.getDateHeuere().toString());
        clientsComboBox.getSelectionModel().select(vente.getClient());
        List<VenteProduit> venteProds = new VenteProduitDAO().getAllByVente(vente);
        ObservableList<VenteProduitHelper> helpingList = new VenteProduitHelper().help(venteProds);
        selectedProdsTableView.setItems(helpingList);
        etatToggleButton.setSelected(vente.getEtatPaiement() == 0);
        if (vente.getRestePaiement() != null) {
            resteTextField.setText(vente.getRestePaiement().toString());
        } else {
            resteTextField.setDisable(true);
        }
    }

    public void setParentController(VenteController controller) {
        parentController = controller;
    }

    public void updateTotalPrice(MouseEvent mouseEvent) {
        BigDecimal totalPrice = getPrixTotal();
        prixTotalTextField.setText(totalPrice + " DH");
    }

    public BigDecimal getPrixTotal() {
        ArrayList<VenteProduitHelper> prods = new ArrayList<>();
        for (int i = 0; i < selectedProdsTableView.getItems().size(); i++) {
            prods.add((VenteProduitHelper) selectedProdsTableView.getItems().get(i));
        }
        BigDecimal total = new BigDecimal(0);
        for (int i = 0; i < prods.size(); i++) {
            total = total.add(new BigDecimal(prods.get(i).getQuantite()).multiply(prods.get(i).getPrix()));
        }
        return total;
    }
}
