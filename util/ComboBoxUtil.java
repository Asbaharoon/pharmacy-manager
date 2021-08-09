package util;

import entity.AvocatAdmin;
import entity.Tribunal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.AvocatAdminDAO;
import model.TribunalDAO;

public class ComboBoxUtil {

    public static void fillTribunalsComboBox(ComboBox<Tribunal> comboBox) {
        comboBox.getItems().setAll(getTribunalsList());
    }

    public static void fillAvocatsComboBox(ComboBox<AvocatAdmin> comboBox) {
        comboBox.getItems().setAll(getAvocatsList());
    }

    public static ObservableList<Tribunal> getTribunalsList() {
        return FXCollections.observableArrayList(new TribunalDAO().getAll());
    }

    public static ObservableList<AvocatAdmin> getAvocatsList() {
        return FXCollections.observableArrayList(new AvocatAdminDAO().getAll());
    }


}
