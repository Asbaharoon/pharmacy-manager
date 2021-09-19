package util;

import entity.Client;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ClientDAO;

import java.util.List;

public class ClientsTableViewUtil {

    public static void fillTableColumns(TableView<Client> tableView) {
        tableView.getColumns().addAll(
                getIdColumn(),
                getNomColumn(),
                getPrenomColumn(),
                getTelColumn()
        );
    }

    public static void filterTableData(TableView<Client> tableView, List<Client> list) {
        tableView.getItems().setAll(FXCollections.observableArrayList(list));
    }

    public static void fillTableData(TableView<Client> tableView, ClientDAO dao) {
        tableView.getItems().setAll(getClientsList(dao));
    }

    public static ObservableList<Client> getClientsList(ClientDAO dao) {
        return FXCollections.observableArrayList(dao.getAll());
    }

    public static TableColumn<Client, Integer> getIdColumn() {
        TableColumn<Client, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);
        idCol.setMinWidth(50);
        idCol.setMaxWidth(50);
        idCol.setStyle( "-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<Client, String> getNomColumn() {
        TableColumn<Client, String> nomCol = new TableColumn<>("Nom");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        return nomCol;

    }

    public static TableColumn<Client, String> getPrenomColumn() {
        TableColumn<Client, String> prenomCol = new TableColumn<>("Prenom");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        return prenomCol;
    }

    public static TableColumn<Client, String> getTelColumn() {
        TableColumn<Client, String> telCol = new TableColumn<>("Telephone");
        telCol.setCellValueFactory(new PropertyValueFactory<>("telePrt"));
        telCol.setCellFactory(col -> new TableCell<Client, String>(){
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
