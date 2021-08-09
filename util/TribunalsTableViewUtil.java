package util;

import entity.Tribunal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TribunalDAO;

public class TribunalsTableViewUtil {

    public static void fillTableColumns(TableView<Tribunal> tableView) {
        tableView.getColumns().addAll(getIdColumn(),
                getNomColumn(),
                getAdresseColumn());
    }

    public static void fillTableData(TableView<Tribunal> tableView) {
        tableView.getItems().setAll(getTribunalsList());
    }

    public static ObservableList<Tribunal> getTribunalsList() {
        return FXCollections.observableArrayList(new TribunalDAO().getAll());
    }

    public static TableColumn<Tribunal, Integer> getIdColumn() {
        TableColumn<Tribunal, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        idCol.setMaxWidth(50);
        idCol.setPrefWidth(50);
        idCol.setMinWidth(50);
        idCol.setStyle( "-fx-alignment: CENTER;");
        return idCol;
    }

    public static TableColumn<Tribunal, String> getNomColumn() {
        TableColumn<Tribunal, String> nomCol = new TableColumn<>("NOM");
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        return nomCol;
    }

    public static TableColumn<Tribunal, String> getAdresseColumn() {
        TableColumn<Tribunal, String> adrCol = new TableColumn<>("ADRESSE");
        adrCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        return adrCol;
    }
}
