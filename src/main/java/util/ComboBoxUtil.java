package util;

import entity.Categorie;
import entity.Client;
import entity.Fournisseur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.CategorieDAO;
import model.ClientDAO;
import model.FournisseurDAO;
import model.ProduitDAO;

public class ComboBoxUtil {

    public static void fillCategorieComboBox(ComboBox<Categorie> comboBox, byte windowType) {
        // WINDOW TYPE == 1 => PARENT WINDOW
        // WINDOW TYPE == 2 => CHILDREN WINDOWS
        if (windowType == 1) {
            Categorie categorie = new Categorie();
            categorie.setNom("Toutes les categories");
            comboBox.getItems().add(categorie);
        }
        comboBox.getItems().addAll(getCategoriesList());
    }

    public static ObservableList<Categorie> getCategoriesList() {
        return FXCollections.observableArrayList(new CategorieDAO().getAll());
    }

    public static void fillPaComboBox(ComboBox<String> comboBox, ProduitDAO dao) {
        comboBox.getItems().clear();
        //comboBox.getItems().add("Tous les principes actifs");
        comboBox.getItems().addAll(getPaList(dao));
    }

    public static ObservableList<String> getPaList(ProduitDAO dao) {
        return FXCollections.observableArrayList(dao.getPAs());
    }

    public static void fillFournisseursComboBox(ComboBox<Fournisseur> comboBox) {
        comboBox.getItems().addAll(getFournisseursList());
    }

    public static void fillClientsComboBox(ComboBox<Client> comboBox) {
        comboBox.getItems().addAll(getClientsList());
    }

    public static ObservableList<Client> getClientsList() {
        return FXCollections.observableArrayList(new ClientDAO().getAll());

    }

    public static ObservableList<Fournisseur> getFournisseursList() {
        return FXCollections.observableArrayList(new FournisseurDAO().getAll());
    }
}
