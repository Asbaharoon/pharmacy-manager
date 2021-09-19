package controller;

import entity.Client;
import entity.Commande;
import entity.Produit;
import entity.Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CommandeDAO;
import model.ProduitDAO;
import util.Clock;
import util.CommandesTableViewUtil;
import util.ProduitsTableViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CommandeController implements Initializable {
    public CommandeController thisController;
    public Button modifyButton;
    public Button deleteButton;
    public TextField searchBarTextField;
    public ComboBox<String> etatPaimentComboBox;
    public TextField minSearchTextField;
    public TextField maxSearchTextField;
    public Label dateAndTimeLabel;
    public Button searchButton;
    public Button moreButton;
    public TableView<Commande> cmdsTableView;

    private CommandeDAO commandeDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock.setDateAndTimeAsync(dateAndTimeLabel);
        searchButton.setDefaultButton(true);
        commandeDAO = new CommandeDAO();
        CommandesTableViewUtil.fillTableColumns(cmdsTableView);
        CommandesTableViewUtil.fillTableData(cmdsTableView, commandeDAO);
    }

    public void onClickOpenAddWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cmd_popup_view.fxml"));
        Parent node = loader.load();
        CommandeMajController majController = loader.getController();
        majController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Ajout d'une commande");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();

        CommandesTableViewUtil.fillTableData(cmdsTableView, commandeDAO);
    }

    public void onClickOpenModificationWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cmd_popup_view.fxml"));
        Parent node = loader.load();
        CommandeMajController majController = loader.getController();
        Commande selectedCommande = cmdsTableView.getSelectionModel().getSelectedItem();
        majController.setType(1);
        majController.setFields(selectedCommande);

        majController.setParentController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Modification d'une commande");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        CommandesTableViewUtil.fillTableData(cmdsTableView, commandeDAO);
    }


    public void onClickDeleteCmd(ActionEvent actionEvent) throws IOException {
        Commande selectedCmd = cmdsTableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmation_dialog.fxml"));
        Parent node = loader.load();
        ConfirmationDialogController dialogController = loader.getController();
        dialogController.contentLabel.setText("Etes-vous sûr que vous voulez supprimer la commande \"[ID: " + selectedCmd.getId()
                + "] ?");
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Boite de confirmation");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        onUnselectDisableActions();
        if (dialogController.getResponse()) {
            commandeDAO.delete(selectedCmd);
            CommandesTableViewUtil.fillTableData(cmdsTableView, commandeDAO);
        }
    }

    public void onClickShowMore(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cmd_details_popup.fxml"));
        Parent node = loader.load();
        CommandeDetailsController detailsController = loader.getController();
        Commande commande = cmdsTableView.getSelectionModel().getSelectedItem();
        detailsController.setParentController(this);
        detailsController.setFields(commande);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Details d'une commande");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
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
