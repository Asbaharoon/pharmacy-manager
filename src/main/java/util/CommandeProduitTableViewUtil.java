package util;

import entity.CommandeProduit;
import entity.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.ArrayList;
import java.util.List;

public class CommandeProduitTableViewUtil {

    public static void fillTableColumns(TableView tableView) {
        tableView.getColumns().addAll(
                getIdCol(),
                getDesigCol(),
                getPrixCol(),
                getQteCol()
        );
    }

    public static void insertData(TableView tableView, List<Produit> produits) {
        ArrayList<CommandeProduitHelper> helperDataList = new ArrayList<>();
        produits.forEach(produit -> {
            helperDataList.add(new CommandeProduitHelper(produit.getId(), produit.getDesignation(), produit.getPrixAchat(), "0"));
        });

        ObservableList<CommandeProduitHelper> dataList = FXCollections.observableArrayList(helperDataList);
        tableView.setItems(dataList);
    }

    public static TableColumn<CommandeProduitHelper, Long> getIdCol() {
        TableColumn<CommandeProduitHelper, Long> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        return idCol;
    }

    public static TableColumn<CommandeProduitHelper, String> getDesigCol() {
        TableColumn<CommandeProduitHelper, String> desigCol = new TableColumn<>("Designation");
        desigCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        return desigCol;
    }

    public static TableColumn<CommandeProduitHelper, Long> getPrixCol() {
        TableColumn<CommandeProduitHelper, Long> prixCol = new TableColumn<>("Prix Achat");
        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        return prixCol;
    }

    public static TableColumn<CommandeProduitHelper, String> getQteCol() {
        TableColumn<CommandeProduitHelper, String> qteCol = new TableColumn<>("Quantite");
        qteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        qteCol.setCellFactory(TextFieldTableCell.forTableColumn());

        qteCol.setOnEditCommit(
                (TableColumn.CellEditEvent<CommandeProduitHelper, String> t) ->
                        ( t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setQte(t.getNewValue())
        );
        return qteCol;
    }


}
