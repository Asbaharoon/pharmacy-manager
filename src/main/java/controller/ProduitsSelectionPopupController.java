package controller;

import entity.Categorie;
import entity.Produit;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ProduitDAO;
import util.ProduitsTableViewUtil;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProduitsSelectionPopupController implements Initializable {

    public TableView<Produit> produitsTableView;
    public Button searchButton;
    public ComboBox<String> PaComboBox;
    public ComboBox<Categorie> categoriesComboBox;
    public TextField searchBarTextField;
    public Label addNewProductClickableLabel;
    public Label selectedProductsLabel;
    private CommandeMajController commandeController;
    private VenteMajController venteController;

    private ProduitDAO produitDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        produitDAO = new ProduitDAO();
        produitsTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        searchButton.setDefaultButton(true);
        ProduitsTableViewUtil.fillTableColumns(produitsTableView);
        ProduitsTableViewUtil.fillTableData(produitsTableView, produitDAO);
    }

    public void setParentController(VenteMajController controller) {
        venteController = controller;
    }

    public void setParentController(CommandeMajController controller) {
        commandeController = controller;
    }

    public void onClickFilter(ActionEvent actionEvent) throws InterruptedException {
        String str = searchBarTextField.getText();
        Categorie cat = categoriesComboBox.getSelectionModel().getSelectedItem();
        String pa = PaComboBox.getSelectionModel().getSelectedItem();
        List<Produit> filteredProdsList = produitDAO.filter(str, cat, pa);
        ProduitsTableViewUtil.filterTableData(produitsTableView, filteredProdsList);
    }

    public void onClickValidateSelection(ActionEvent actionEvent) {
        List<Produit> selectedProducts = produitsTableView.getSelectionModel().getSelectedItems();
        try {
            commandeController.updateTableView(selectedProducts);
        } catch(Exception e) {
            venteController.updateTableView(selectedProducts);
        }
        Stage thisStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        thisStage.close();
    }

    public void updateSelectedProductsLabel() {
        int count = produitsTableView.getSelectionModel().getSelectedItems().size();
        selectedProductsLabel.setText("(" + count + ") Produit(s) sélectionnés.");
    }

}
