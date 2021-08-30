package util;

import entity.Categorie;
import entity.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ProduitDAO;

import java.math.BigDecimal;
import java.util.List;

public class ProduitsTableViewUtil {
    public static void fillTableColumns(TableView<Produit> tableView) {
        tableView.getColumns().addAll(
                getIdColumn(),
                getDesignationColumn(),
                getLaboColumn(),
                getPaColumn(),
                getCategorieColumn(),
                getPrixAchatColumn(),
                getPrixVenteColumn(),
                getQteStockColumn()
        );
    }

    public static void filterTableData(TableView<Produit> tableView, List<Produit> produitList) {
        tableView.getItems().setAll(FXCollections.observableArrayList(produitList));
    }

    public static void fillTableData(TableView<Produit> tableView, ProduitDAO dao) {
        tableView.getItems().setAll(getProduitsList(dao));
    }

    public static ObservableList<Produit> getProduitsList(ProduitDAO dao) {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static TableColumn<Produit, Integer> getIdColumn() {
        TableColumn<Produit, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        idCol.setMinWidth(50);
        idCol.setStyle( "-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<Produit, String> getDesignationColumn() {
        TableColumn<Produit, String> designationCol = new TableColumn<>("Designation");
        designationCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        designationCol.setPrefWidth(270);
        designationCol.setMinWidth(270);
        return designationCol;
    }

    public static TableColumn<Produit, String> getLaboColumn() {
        TableColumn<Produit, String> laboCol = new TableColumn<>("Laboratoire");
        laboCol.setCellValueFactory(new PropertyValueFactory<>("labo"));
        return laboCol;
    }

    public static TableColumn<Produit, String> getPaColumn() {
        TableColumn<Produit, String> PaCol = new TableColumn<>("Principe Actif");
        PaCol.setCellValueFactory(new PropertyValueFactory<>("principeActif"));
        return PaCol;
    }

    public static TableColumn<Produit, BigDecimal> getPrixAchatColumn() {
        TableColumn<Produit, BigDecimal> prixAchatCol = new TableColumn<>("Prix Achat");
        prixAchatCol.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
        prixAchatCol.setCellFactory(col -> new TableCell<Produit, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal prixAchat, boolean empty) {
                super.updateItem(prixAchat, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(prixAchat.toString() + " DH");
                }
            }
        });
        return prixAchatCol;
    }

    public static TableColumn<Produit, BigDecimal> getPrixVenteColumn() {
        TableColumn<Produit, BigDecimal> prixVenteCol = new TableColumn<>("Prix Vente");
        prixVenteCol.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
        prixVenteCol.setCellFactory(col -> new TableCell<Produit, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal prixVente, boolean empty) {
                super.updateItem(prixVente, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(prixVente.toString() + " DH");
                }
            }
        });
        return prixVenteCol;
    }

    public static TableColumn<Produit, Integer> getQteStockColumn() {
        TableColumn<Produit, Integer> qteStockCol = new TableColumn<>("Quantité");
        qteStockCol.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        return qteStockCol;
    }

    public static TableColumn<Produit, Categorie> getCategorieColumn() {
        TableColumn<Produit, Categorie> categorieColumn = new TableColumn<>("Categorie");
        categorieColumn.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        categorieColumn.setCellFactory(col -> new TableCell<Produit, Categorie>() {
            @Override
            protected void updateItem(Categorie categorie, boolean empty) {
                if(categorie != null) {
                    super.updateItem(categorie, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(categorie.getNom());
                    }
                } else {
                    setText(null);
                }
            }});
        return categorieColumn;
    }
}
