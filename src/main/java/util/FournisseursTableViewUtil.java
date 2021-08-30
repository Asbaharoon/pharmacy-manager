package util;

import entity.Fournisseur;
import entity.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.FournisseurDAO;

import java.util.List;

public class FournisseursTableViewUtil {
    public static void fillTableColumns(TableView<Fournisseur> tableView) {
        tableView.getColumns().addAll(
                getIdColumn(),
                getNomColumn(),
                getPrenomColumn(),
                getTelColumn()
        );
    }

    public static void filterTableData(TableView<Fournisseur> tableView, List<Fournisseur> list) {
        tableView.getItems().setAll(FXCollections.observableArrayList(list));
    }

    public static void fillTableData(TableView<Fournisseur> tableView, FournisseurDAO dao) {
        tableView.getItems().setAll(getFournisseursList(dao));
    }

    public static ObservableList<Fournisseur> getFournisseursList(FournisseurDAO dao) {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static TableColumn<Fournisseur, Integer> getIdColumn() {
        TableColumn<Fournisseur, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        idCol.setMinWidth(50);
        idCol.setMaxWidth(50);
        idCol.setStyle( "-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<Fournisseur, String> getNomColumn() {
        TableColumn<Fournisseur, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        return nomCol;

    }

    public static TableColumn<Fournisseur, String> getPrenomColumn() {
        TableColumn<Fournisseur, String> prenomCol = new TableColumn<>("Prenom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        return prenomCol;
    }

    public static TableColumn<Fournisseur, String> getTelColumn() {
        TableColumn<Fournisseur, String> telCol = new TableColumn<>("Telephone");
        telCol.setCellValueFactory(new PropertyValueFactory<>("telePrt"));
        telCol.setCellFactory(col -> new TableCell<Fournisseur, String>(){
            @Override
            protected void updateItem(String phoneNumber, boolean empty) {
                super.updateItem(phoneNumber, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(Utils.beautifyPhoneNumber(phoneNumber));
                }
            }
        });
        return telCol;
    }
}
