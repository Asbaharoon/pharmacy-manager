package util;

import entity.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;

public class VenteProduitTableViewUtil {

    public static void fillTableColumns(TableView tableView) {
        tableView.getColumns().addAll(
                getIdCol(),
                getDesigCol(),
                getPrixCol(),
                getQteCol()
        );
    }

    public static void insertData(TableView tableView, List<Produit> produits) {
        ArrayList<VenteProduitHelper> helperDataList = new ArrayList<>();
        produits.forEach(produit -> {
            helperDataList.add(new VenteProduitHelper(produit.getId(), produit.getDesignation(), produit.getPrixVente(), "0"));
        });

        ObservableList<VenteProduitHelper> dataList = FXCollections.observableArrayList(helperDataList);
        tableView.setItems(dataList);
    }

    public static TableColumn<VenteProduitHelper, Long> getIdCol() {
        TableColumn<VenteProduitHelper, Long> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }

    public static TableColumn<VenteProduitHelper, String> getDesigCol() {
        TableColumn<VenteProduitHelper, String> desigCol = new TableColumn<>("Designation");
        desigCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        return desigCol;
    }

    public static TableColumn<VenteProduitHelper, Long> getPrixCol() {
        TableColumn<VenteProduitHelper, Long> prixCol = new TableColumn<>("Prix Vente");
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        return prixCol;
    }

    public static TableColumn<VenteProduitHelper, String> getQteCol() {
        TableColumn<VenteProduitHelper, String> qteCol = new TableColumn<>("Quantite");
        qteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        qteCol.setCellFactory(TextFieldTableCell.forTableColumn());

        qteCol.setOnEditCommit(
                (TableColumn.CellEditEvent<VenteProduitHelper, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQte(t.getNewValue())
        );
        return qteCol;
    }


}
