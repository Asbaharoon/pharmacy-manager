package controller;

import entity.Client;
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
import model.ClientDAO;
import util.ClientsTableViewUtil;
import util.Clock;
import util.SpreadsheetUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public TableView<Client> clientsTableView;
    public ClientDAO clientDAO;
    public Label dateAndTimeLabel;
    public Button modifyButton;
    public Button deleteButton;
    public TextField searchBarTextField;
    public Button searchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.setDateAndTimeAsync(dateAndTimeLabel);
        searchButton.setDefaultButton(true);
        clientDAO = new ClientDAO();
        ClientsTableViewUtil.fillTableColumns(clientsTableView);
        ClientsTableViewUtil.fillTableData(clientsTableView, clientDAO);
    }

    public void onClickOpenAddWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client_popup_view.fxml"));
        Parent node = loader.load();
        ClientMajController majController = loader.getController();
        majController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Ajout d'un client");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        ClientsTableViewUtil.fillTableData(clientsTableView, clientDAO);
    }

    public void onClickOpenModificationWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client_popup_view.fxml"));
        Parent node = loader.load();

        ClientMajController majController = loader.getController();
        Client selectedClient = clientsTableView.getSelectionModel().getSelectedItem();
        majController.setType(1);
        majController.setFields(selectedClient);

        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Modification d'un client");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        ClientsTableViewUtil.fillTableData(clientsTableView, clientDAO);
    }

    public void onClickDeleteClient() throws IOException {
        Client selectedClient = clientsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmation_dialog.fxml"));
        Parent node = loader.load();
        ConfirmationDialogController dialogController = loader.getController();
        dialogController.contentLabel.setText("Etes-vous sûr que vous voulez supprimer \"[" + selectedClient.getId()
                + "] " + selectedClient.getNom() + " " + selectedClient.getPrenom() + "\" ?");
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Boite de confirmation");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        if (dialogController.getResponse()) {
            new ClientDAO().delete(selectedClient);
            ClientsTableViewUtil.fillTableData(clientsTableView, clientDAO);
        }
    }

    public void onClickFilter() throws InterruptedException {
        onUnselectDisableActions();
        String str = searchBarTextField.getText();
        List<Client> filteredList = clientDAO.filter(str);
        ClientsTableViewUtil.filterTableData(clientsTableView, filteredList);
    }

    public void onClickImport(ActionEvent actionEvent) throws IOException {
        SpreadsheetUtil.load(actionEvent, clientsTableView);
    }

    public void onClickExport(ActionEvent actionEvent) throws IOException {
        SpreadsheetUtil.saveC(actionEvent, clientsTableView);
    }

    public void refresh() {
        ClientsTableViewUtil.fillTableData(clientsTableView, clientDAO);
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


