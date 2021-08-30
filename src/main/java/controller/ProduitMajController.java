package controller;

import entity.Categorie;
import entity.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ProduitDAO;
import util.ComboBoxUtil;
import util.TransitionsUtil;
import util.Utils;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ProduitMajController implements Initializable {

    public ImageView titleIcon;
    public Label titleLabel;

    public TextField desigTextField;
    public TextField laboTextField;
    public ComboBox<Categorie> categorieComboBox;
    public TextField principeActifTextField;
    public TextField prixAchatTextField;
    public TextField prixVenteTextField;
    public TextField qteStockTextField;

    public Label feedbackLabel;
    public ImageView statusIcon;
    public Button mainButton;

    public long id;
    private StockController parentController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ComboBoxUtil.fillCategorieComboBox(categorieComboBox, (byte) 2);
    }

    public void setType(int type) {
        if (type == 1) {
            mainButton.setOnAction(this::onClickModifyProduit);
            titleLabel.setText("Modifier un produit");
            titleIcon.setImage(new Image("images\\edit_grey.png"));
        }
    }

    public void onClickAddProduit() {
        try {
            setFeedbackLogic();
            Produit produit = new Produit();
            setProduit(produit);
            new ProduitDAO().save(produit);

            // CLEAR ALL FIELDS
            desigTextField.setText("");
            laboTextField.setText("");
            principeActifTextField.setText("");
            prixAchatTextField.setText("");
            prixVenteTextField.setText("");
            categorieComboBox.getSelectionModel().clearSelection();
            qteStockTextField.setText("");

            // SHOW SUCCESS ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, "Produit ajouté avec success!", "green");
            TransitionsUtil.playIconTransition(statusIcon, "correct-symbol.png");
            parentController.refresh();

        } catch (java.lang.NumberFormatException e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, "Format invalide, utilisez des nombres!", "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, e.getMessage(), "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void onClickModifyProduit(ActionEvent actionEvent) {
        try {
            setFeedbackLogic();
            Produit produit = new Produit();
            produit.setId(id);
            setProduit(produit);
            new ProduitDAO().update(produit);
            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        } catch (java.lang.NumberFormatException e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, "Format invalide, utilisez des nombres!", "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, e.getMessage(), "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void setParentController(StockController controller) {
        this.parentController = controller;
    }

    public void setProduit(Produit produit) {
        produit.setDesignation(Utils.capFirstLetter(desigTextField.getText()));
        produit.setCategorie(categorieComboBox.getSelectionModel().getSelectedItem());
        produit.setLabo(laboTextField.getText());
        produit.setPrincipeActif(principeActifTextField.getText());
        produit.setPrixAchat(BigDecimal.valueOf(Long.parseLong(prixAchatTextField.getText())));
        produit.setPrixVente(BigDecimal.valueOf(Long.parseLong(prixVenteTextField.getText())));
        produit.setQteStock(Integer.parseInt(qteStockTextField.getText()));
    }

    public void setFields(Produit produit) {
        this.id = produit.getId();
        this.desigTextField.setText(produit.getDesignation());
        this.desigTextField.setPromptText(produit.getDesignation());
        this.laboTextField.setText(produit.getLabo());
        this.laboTextField.setPromptText(produit.getLabo());
        this.principeActifTextField.setText(produit.getPrincipeActif());
        this.principeActifTextField.setPromptText(produit.getPrincipeActif());
        this.prixAchatTextField.setText(String.valueOf(produit.getPrixAchat().intValue()));
        this.prixAchatTextField.setPromptText(produit.getPrixAchat().toString());
        this.prixVenteTextField.setText(String.valueOf(produit.getPrixVente().intValue()));
        this.prixVenteTextField.setPromptText(produit.getPrixVente().toString());
        this.qteStockTextField.setText(String.valueOf(produit.getQteStock()));
        this.qteStockTextField.setPromptText(String.valueOf(produit.getQteStock()));
        this.categorieComboBox.getSelectionModel().select(produit.getCategorie());
    }

    public void setFeedbackLogic() throws Exception {
        if (desigTextField.getText().isEmpty() || laboTextField.getText().isEmpty()
                || principeActifTextField.getText().isEmpty() || categorieComboBox.getSelectionModel().getSelectedItem() == null
                || prixAchatTextField.getText().isEmpty() || prixVenteTextField.getText().isEmpty()
                || qteStockTextField.getText().isEmpty()) {
            throw new Exception("Champ(s) vide(s)!");
        }
    }


}
