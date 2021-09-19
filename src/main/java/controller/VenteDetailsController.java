package controller;

import entity.Produit;
import entity.Vente;
import entity.VenteProduit;
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
import model.VenteProduitDAO;
import util.VenteProduitHelper;
import util.VenteProduitTableViewUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VenteDetailsController implements Initializable {
    public TextField dateHeureTextField;
    public TextField clientTextField;
    public TextField resteTextField;
    public TableView<VenteProduitHelper> produitsTableView;
    public TextField prixTotalTextField;
    public TextField etatPaimentTextField;
    private VenteController parentController;
    private List<VenteProduit> venteProds;
    private Vente vente;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VenteProduitTableViewUtil.fillTableColumns(produitsTableView);

    }

    public void setParentController(VenteController controller) {
        parentController = controller;
    }

    public void openAvoirAssociationWindow(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/avoir_association_popup.fxml"));
        Parent node = loader.load();
        AvoirAssociationController avoirAssociationController = loader.getController();
        Produit produit = new ProduitDAO().getById(produitsTableView.getSelectionModel().getSelectedItem().getId());
        VenteProduit venteProduit = new VenteProduitDAO().getByVenteAndProduit(vente, produit);
        avoirAssociationController.setFields(venteProduit, produit, dateHeureTextField.getText());
        //detailsController.setCommandeController(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(node));
        stage.setTitle("Association a un avoir");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }


    public void setFields(Vente vente) {
        this.vente = vente;
        dateHeureTextField.setText(vente.getDateHeuere().toString());
        clientTextField.setText(vente.getClient().toString());
        venteProds = new VenteProduitDAO().getAllByVente(vente);
        ObservableList<VenteProduitHelper> helpingList = new VenteProduitHelper().help(venteProds);
        produitsTableView.setItems(helpingList);
        prixTotalTextField.setText(vente.getPrixTotal() + " DH");
        if (vente.getEtatPaiement() == 0) {
            etatPaimentTextField.setText("Payé");
        } else {
            etatPaimentTextField.setText("Non Payé");
            resteTextField.setText(vente.getRestePaiement().toString());
        }
    }


}
