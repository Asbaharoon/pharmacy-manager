package util;

import entity.AvocatAdmin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AvocatAdminDAO;

public class AvocatsTableViewUtil {

    public static void fillTableColumns(TableView<AvocatAdmin> tableView) {
        tableView.getColumns().addAll(getIdColumn(),
                getNomColumn(),
                getPrenomColumn());
    }

    public static void fillTableData(TableView<AvocatAdmin> tableView) {
        tableView.getItems().setAll(getAvocatsList());
    }

    public static ObservableList<AvocatAdmin> getAvocatsList() {
        return FXCollections.observableArrayList(new AvocatAdminDAO().getAll());
    }

    public static TableColumn<AvocatAdmin, Integer> getIdColumn() {
        TableColumn<AvocatAdmin, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        idCol.setMaxWidth(50);
        idCol.setPrefWidth(50);
        idCol.setMinWidth(50);
        idCol.setStyle( "-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<AvocatAdmin, String> getNomColumn() {
        TableColumn<AvocatAdmin, String> nomCol = new TableColumn<>("NOM");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        return nomCol;
    }

    public static TableColumn<AvocatAdmin, String> getPrenomColumn() {
        TableColumn<AvocatAdmin, String> prenomCol = new TableColumn<>("PRENOM");
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        return prenomCol;
    }
}
