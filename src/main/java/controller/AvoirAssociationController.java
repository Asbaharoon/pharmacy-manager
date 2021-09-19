package controller;

import entity.Avoir;
import entity.Produit;
import entity.VenteProduit;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AvoirDAO;
import util.Utils;

import java.time.LocalDateTime;

public class AvoirAssociationController {
    public TextField produitDesigTextField;
    public TextField qteRendueTextField;
    public TextField MotifTextField;
    private String dateTime;
    private VenteProduit venteProduit;

    public void setFields(VenteProduit venteProduit, Produit produit, String dateTime) {
        produitDesigTextField.setText(produit.getDesignation());
        this.venteProduit = venteProduit;
        this.dateTime = dateTime;
    }

    public void onClickAssocier(ActionEvent actionEvent) {
        String motif = MotifTextField.getText();
        LocalDateTime localDateTime = Utils.formatDateTime(dateTime);
        int qteRendue = Integer.parseInt(qteRendueTextField.getText());
        Avoir avoir = new Avoir(venteProduit, motif, localDateTime, qteRendue);
        new AvoirDAO().save(avoir);
        Stage thisStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        thisStage.close();
    }
}
