package controller;

import entity.Produit;
import entity.Commande;
import entity.CommandeProduit;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ProduitDAO;
import model.CommandeProduitDAO;
import util.CommandeProduitHelper;
import util.CommandeProduitTableViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CommandeDetailsController implements Initializable {
    public TextField dateHeureTextField;
    public TextField fournisseurTextField;
    public TextField resteTextField;
    public TableView<CommandeProduitHelper> produitsTableView;
    public TextField prixTotalTextField;
    public TextField etatPaimentTextField;
    private CommandeController parentController;
    private List<CommandeProduit> commandeProds;
    private Commande commande;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CommandeProduitTableViewUtil.fillTableColumns(produitsTableView);

    }

    public void setParentController(CommandeController controller) {
        parentController = controller;
    }
    


    public void setFields(Commande commande) {
        this.commande = commande;
        dateHeureTextField.setText(commande.getDateHeuere().toString());
        fournisseurTextField.setText(commande.getFournisseur().toString());
        commandeProds = new CommandeProduitDAO().getAllByCmd(commande);
        ObservableList<CommandeProduitHelper> helpingList = new CommandeProduitHelper().help(commandeProds);
        produitsTableView.setItems(helpingList);
        prixTotalTextField.setText(commande.getPrixTotal() + " DH");
        if (commande.getEtatPaiment() == 0) {
            etatPaimentTextField.setText("Payé");
        } else {
            etatPaimentTextField.setText("Non Payé");
            resteTextField.setText(commande.getRestePaiement().toString());
        }
    }


}
