package controller;

import entity.Categorie;
import entity.Produit;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ProduitDAO;
import util.Clock;
import util.ComboBoxUtil;
import util.ProduitsTableViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    public TableView<Produit> produitsTableView;
    public Button modifyButton;
    public Button deleteButton;
    public StockController thisController;
    public TextField searchBarTextField;
    public ComboBox<Categorie> categoriesComboBox;
    public ComboBox<String> PaComboBox;

    public Label dateAndTimeLabel;
    public Button searchButton;

    private ProduitDAO produitDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.setDateAndTimeAsync(dateAndTimeLabel);
        searchButton.setDefaultButton(true);
        produitDAO = new ProduitDAO();
        ProduitsTableViewUtil.fillTableColumns(produitsTableView);
        ProduitsTableViewUtil.fillTableData(produitsTableView, produitDAO);
        ComboBoxUtil.fillCategorieComboBox(categoriesComboBox, (byte) 1);
        ComboBoxUtil.fillPaComboBox(PaComboBox, produitDAO);
    }

    public void onClickOpenAddWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/produit_popup_view.fxml"));
        Parent node = loader.load();
        ProduitMajController majController = loader.getController();
        majController.setParentController(thisController);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Ajout d'un produit");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        ProduitsTableViewUtil.fillTableData(produitsTableView, produitDAO);
        ComboBoxUtil.fillPaComboBox(PaComboBox, produitDAO);
    }

    public void onClickOpenModifyWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/produit_popup_view.fxml"));
        Parent node = loader.load();

        // SETUP POPUP INFOS
        ProduitMajController majController = loader.getController();
        Produit selectedProduit = produitsTableView.getSelectionModel().getSelectedItem();
        majController.setType(1); // 1 for modification
        majController.setFields(selectedProduit);

        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Modification d'un produit");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        ProduitsTableViewUtil.fillTableData(produitsTableView, produitDAO);
        ComboBoxUtil.fillPaComboBox(PaComboBox, produitDAO);
    }

    public void onClickDeleteProduit() throws IOException {
        Produit selectedProduit = produitsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmation_dialog.fxml"));
        Parent node = loader.load();
        ConfirmationDialogController dialogController = loader.getController();
        dialogController.contentLabel.setText("Etes-vous sûr que vous voulez supprimer \"[" + selectedProduit.getId()
                + "] " + selectedProduit.getDesignation() + "\" ?");
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Boite de confirmation");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        if (dialogController.getResponse()) {
            new ProduitDAO().delete(selectedProduit);
            ProduitsTableViewUtil.fillTableData(produitsTableView, produitDAO);
        }
    }

    public void onClickFilter() throws InterruptedException {
        String str = searchBarTextField.getText();
        Categorie cat = categoriesComboBox.getSelectionModel().getSelectedItem();
        String pa = PaComboBox.getSelectionModel().getSelectedItem();

        List<Produit> filteredProdsList = produitDAO.filter(str, cat, pa);
        ProduitsTableViewUtil.filterTableData(produitsTableView, filteredProdsList);
    }

    public void refresh() {
        ProduitsTableViewUtil.fillTableData(produitsTableView, produitDAO);
    }

    public void onSelectEnableActions() {
        modifyButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    public void onUnselectDisableActions() {
        modifyButton.setDisable(true);
        deleteButton.setDisable(true);
    }







}
