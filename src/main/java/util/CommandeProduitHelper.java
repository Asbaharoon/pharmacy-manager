package util;

import entity.CommandeProduit;
import entity.Produit;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ProduitDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CommandeProduitHelper {
    private SimpleLongProperty fieldId;
    private SimpleStringProperty fieldDesig;
    //private SimpleLongProperty fieldPrix;
    private SimpleStringProperty fieldQte;
    private SimpleObjectProperty<BigDecimal> fieldPrix;

    public CommandeProduitHelper(long id, String designation, BigDecimal prix, String quantite) {
        fieldId = new SimpleLongProperty(id);
        fieldDesig = new SimpleStringProperty(designation);
        fieldPrix = new SimpleObjectProperty<>(prix);
        fieldQte = new SimpleStringProperty(quantite);
    }

    public CommandeProduitHelper() {

    }

    public ObservableList<CommandeProduitHelper> help(List<CommandeProduit> list) {
        List<CommandeProduitHelper> helpingList = new ArrayList<>();
        list.forEach(item -> {
            Produit produit = item.getProduit();
            helpingList.add(new CommandeProduitHelper(item.getId(), produit.getDesignation(), produit.getPrixAchat(), String.valueOf(item.getQteCommandee())));
        });
        return FXCollections.observableArrayList(helpingList);
    }

    public Long getId() {
        return fieldId.get();
    }

    public String getQuantite() {
        return fieldQte.get();
    }

    public String getDesignation() {
        return fieldDesig.get();
    }

    public BigDecimal getPrix() {
        return fieldPrix.get();
    }

    public void setQte(String qte) {
        fieldQte.set(qte);
    }


}
