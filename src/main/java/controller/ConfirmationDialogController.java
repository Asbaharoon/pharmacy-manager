package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConfirmationDialogController {

    public Button cancelButton;
    public boolean isConfirmed;
    public Text contentLabel;

    public void closeWindow(ActionEvent actionEvent) {
        Stage thisStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        thisStage.close();
    }

    public boolean getResponse() {
        return this.isConfirmed;
    }

    public void confirmDeletion(ActionEvent actionEvent) {
        this.isConfirmed = true;
        closeWindow(actionEvent);
    }
}
