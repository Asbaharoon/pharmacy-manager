package controller;

import entity.Fournisseur;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.FournisseurDAO;
import util.Clock;
import util.FournisseursTableViewUtil;
import util.SpreadsheetUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FournisseurController implements Initializable {

    public TableView<Fournisseur> fournisseursTableView;
    public FournisseurDAO fournisseurDAO;
    public Label dateAndTimeLabel;
    public Button modifyButton;
    public Button deleteButton;
    public TextField searchBarTextField;
    public Button searchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.setDateAndTimeAsync(dateAndTimeLabel);
        searchButton.setDefaultButton(true);
        fournisseurDAO = new FournisseurDAO();
        FournisseursTableViewUtil.fillTableColumns(fournisseursTableView);
        FournisseursTableViewUtil.fillTableData(fournisseursTableView, fournisseurDAO);
    }

    public void onClickOpenAddWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fournisseur_popup_view.fxml"));
        Parent node = loader.load();
        FournisseurMajController majController = loader.getController();
        majController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Ajout d'un fournisseur");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        FournisseursTableViewUtil.fillTableData(fournisseursTableView, fournisseurDAO);
    }

    public void onClickModifyWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fournisseur_popup_view.fxml"));
        Parent node = loader.load();

        FournisseurMajController majController = loader.getController();
        Fournisseur selectedFournisseur = fournisseursTableView.getSelectionModel().getSelectedItem();
        majController.setType(1);
        majController.setFields(selectedFournisseur);

        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Modification d'un fournisseur");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        FournisseursTableViewUtil.fillTableData(fournisseursTableView, fournisseurDAO);
    }

    public void onClickDeleteFournisseur() throws IOException {
        Fournisseur selectedFornisseur = fournisseursTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmation_dialog.fxml"));
        Parent node = loader.load();
        ConfirmationDialogController dialogController = loader.getController();
        dialogController.contentLabel.setText("Etes-vous sûr que vous voulez supprimer \"[" + selectedFornisseur.getId()
                + "] " + selectedFornisseur.getNom() + " " + selectedFornisseur.getPrenom() + "\" ?");
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Boite de confirmation");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        if (dialogController.getResponse()) {
            new FournisseurDAO().delete(selectedFornisseur);
            FournisseursTableViewUtil.fillTableData(fournisseursTableView, fournisseurDAO);
        }
    }
    
    public void onClickFilter() throws InterruptedException {
        onUnselectDisableActions();
        String str = searchBarTextField.getText();
        List<Fournisseur> filteredList = fournisseurDAO.filter(str);
        FournisseursTableViewUtil.filterTableData(fournisseursTableView, filteredList);
    }

    public void onClickImport(ActionEvent actionEvent) throws IOException {
        SpreadsheetUtil.load(actionEvent, fournisseursTableView);
    }

    public void onClickExport(ActionEvent actionEvent) throws IOException {
        SpreadsheetUtil.saveF(actionEvent, fournisseursTableView);
    }

    public void refresh() {
        FournisseursTableViewUtil.fillTableData(fournisseursTableView, fournisseurDAO);
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
