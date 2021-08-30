package controller;

import entity.Client;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.ClientDAO;
import util.TransitionsUtil;
import util.Utils;

public class ClientMajController {
    public ImageView titleIcon;
    public Label titleLabel;
    public ImageView statusIcon;
    public Button mainButton;
    public TextField telTextField;
    public TextField prenomTextField;
    public TextField nomTextField;
    public Label feedbackLabel;

    private long id;
    public ClientController parentController;

    public void setType(int type) {
        if (type == 1) {
            mainButton.setOnAction(this::onClickModifyClient);
            titleLabel.setText("Modifier un client");
            titleIcon.setImage(new Image("images\\edit_grey.png"));
        }
    }

    public void onClickAddClient(ActionEvent actionEvent) {
        try {
            setFeedbackLogic();
            Client client = new Client();
            setClient(client);
            new ClientDAO().save(client);

            // CLEAR ALL FIELDS
            nomTextField.setText("");
            prenomTextField.setText("");
            telTextField.setText("");

            // SHOW SUCCESS ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, "Client ajouté avec success!", "green");
            TransitionsUtil.playIconTransition(statusIcon, "correct-symbol.png");
            parentController.refresh();
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, e.getMessage(), "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void onClickModifyClient(ActionEvent actionEvent) {
        try {
            setFeedbackLogic();
            Client client = new Client();
            client.setId(id);
            setClient(client);
            new ClientDAO().update(client);
            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.hide();
        } catch (Exception e) {
            // SHOW FAIL ICON
            TransitionsUtil.playLabelTransition(feedbackLabel, e.getMessage(), "red");
            TransitionsUtil.playIconTransition(statusIcon, "wrong_symbol.png");
        }
    }

    public void setParentController(ClientController controller) {
        this.parentController = controller;
    }

    public void setClient(Client client) {
        client.setNom(nomTextField.getText().toUpperCase());
        client.setPrenom(Utils.capFirstLetter(prenomTextField.getText()));
        client.setTelePrt(telTextField.getText());
    }

    public void setFields(Client client) {
        this.id = client.getId();
        this.nomTextField.setText(client.getNom());
        this.nomTextField.setPromptText(client.getNom());
        this.prenomTextField.setText(client.getPrenom());
        this.prenomTextField.setPromptText(client.getPrenom());
        this.telTextField.setText(client.getTelePrt());
        this.telTextField.setPromptText(client.getTelePrt());
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
