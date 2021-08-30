package controller;

import entity.Fournisseur;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.FournisseurDAO;
import util.TransitionsUtil;
import util.Utils;

public class FournisseurMajController {
    public ImageView titleIcon;
    public Label titleLabel;
    public ImageView statusIcon;
    public Button mainButton;
    public TextField telTextField;
    public TextField prenomTextField;
    public TextField nomTextField;
    public Label feedbackLabel;

    private long id;
    public FournisseurController parentController;

    public void setType(int type) {
        if (type == 1) {
            mainButton.setOnAction(this::onClickModifyFournisseur);
            titleLabel.setText("Modifier un fournisseur");
            titleIcon.setImage(new Image("images\\edit_grey.png"));
        }
    }

    public void onClickAddFournisseur(ActionEvent actionEvent) {
        try {
            setFeedbackLogic();
            Fournisseur fournisseur = new Fournisseur();
            setFournisseur(fournisseur);
            new FournisseurDAO().save(fournisseur);

            // CLEAR ALL FIELDS
            nomTextField.setText("");
            prenomTextField.setText("");
            telTextField.setText("");

            // SHOW SUCCESS ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, "Fournisseur ajouté avec success!", "green");
            TransitionsUtil.playIconTransition(statusIcon, "correct-symbol.png");
            parentController.refresh();
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, e.getMessage(), "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void onClickModifyFournisseur(ActionEvent actionEvent) {
        try {
            setFeedbackLogic();
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(id);
            setFournisseur(fournisseur);
            new FournisseurDAO().update(fournisseur);
            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, e.getMessage(), "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void setParentController(FournisseurController controller) {
        this.parentController = controller;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        fournisseur.setNom(nomTextField.getText().toUpperCase());
        fournisseur.setPrenom(Utils.capFirstLetter(prenomTextField.getText()));
        fournisseur.setTelePrt(telTextField.getText());
    }

    public void setFields(Fournisseur fournisseur) {
        this.id = fournisseur.getId();
        this.nomTextField.setText(fournisseur.getNom());
        this.nomTextField.setPromptText(fournisseur.getNom());
        this.prenomTextField.setText(fournisseur.getPrenom());
        this.prenomTextField.setPromptText(fournisseur.getPrenom());
        this.telTextField.setText(fournisseur.getTelePrt());
        this.telTextField.setPromptText(fournisseur.getTelePrt());
    }

    public void setFeedbackLogic() throws Exception {
        if ((prenomTextField.getText().isEmpty() || nomTextField.getText().isEmpty())
                && !telTextField.getText().isEmpty() && telTextField.getText().length() != 10) {
            throw new Exception("Champ(s) vide(s) et Numéro de téléphone invalide!");
        } else if (prenomTextField.getText().isEmpty() || nomTextField.getText().isEmpty() || telTextField.getText().isEmpty()) {
            throw new Exception("Champ(s) vide(s)!");
        } else if (telTextField.getText().length() != 10) {
            throw new Exception("Numéro de téléphone invalide!");
        }
    }

}
