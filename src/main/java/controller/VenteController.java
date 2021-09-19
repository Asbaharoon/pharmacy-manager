package controller;

import entity.Vente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.VenteDAO;
import util.Clock;
import util.VentesTableViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VenteController implements Initializable {

    public VenteController thisController;
    public Button modifyButton;
    public Button deleteButton;
    public TextField searchBarTextField;
    public ComboBox<String> etatPaimentComboBox;
    public TextField minSearchTextField;
    public TextField maxSearchTextField;
    public Label dateAndTimeLabel;
    public Button searchButton;
    public Button detailsButton;
    public TableView<Vente> ventesTableView;

    private VenteDAO venteDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.setDateAndTimeAsync(dateAndTimeLabel);
        searchButton.setDefaultButton(true);
        venteDAO = new VenteDAO();
        VentesTableViewUtil.fillTableColumns(ventesTableView);
        VentesTableViewUtil.fillTableData(ventesTableView, venteDAO);
    }

    public void onClickOpenAddWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventes_popup_view.fxml"));
        Parent node = loader.load();
        VenteMajController majController = loader.getController();
        majController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Ajout d'une vente");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();

        VentesTableViewUtil.fillTableData(ventesTableView, venteDAO);
    }

    public void onClickOpenModificationWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventes_popup_view.fxml"));
        Parent node = loader.load();
        VenteMajController majController = loader.getController();
        Vente selectedVente = ventesTableView.getSelectionModel().getSelectedItem();
        majController.setType(1);
        majController.setFields(selectedVente);

        majController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Modification d'une vente");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        VentesTableViewUtil.fillTableData(ventesTableView, venteDAO);
    }


    public void onClickDeleteVente(ActionEvent actionEvent) throws IOException {
        Vente selectedCmd = ventesTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmation_dialog.fxml"));
        Parent node = loader.load();
        ConfirmationDialogController dialogController = loader.getController();
        dialogController.contentLabel.setText("Etes-vous sûr que vous voulez supprimer la vente \"[ID: " + selectedCmd.getId()
                + "] ?");
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Boite de confirmation");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        if (dialogController.getResponse()) {
            venteDAO.delete(selectedCmd);
            VentesTableViewUtil.fillTableData(ventesTableView, venteDAO);
        }
    }

    public void onClickShowMore(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vente_details_popup.fxml"));
        Parent node = loader.load();
        VenteDetailsController detailsController = loader.getController();
        Vente vente = ventesTableView.getSelectionModel().getSelectedItem();
        detailsController.setParentController(this);
        detailsController.setFields(vente);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Details d'une vente");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void openAvoirsList() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/avoirs_list.fxml"));
        Parent node = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Liste des avoirs");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void onSelectEnableActions() {
        modifyButton.setDisable(false);
        deleteButton.setDisable(false);
        detailsButton.setDisable(false);
    }

    public void onUnselectDisableActions() {
        modifyButton.setDisable(true);
        deleteButton.setDisable(true);
        detailsButton.setDisable(true);
    }




}
