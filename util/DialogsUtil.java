package util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogsUtil {
    public static void showDossierSuccess() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Dossier saisi avec succès!");
        successAlert.setHeaderText(null);
        successAlert.setTitle("Boîte d'information");
        successAlert.showAndWait();
    }

    public static void showDossierFailure() {
        Alert failureAlert = new Alert(Alert.AlertType.ERROR, "Un ou plusieurs champs sont vides.");
        failureAlert.setHeaderText("Le saisi du dossier a échoué!");
        failureAlert.setTitle("Boite d'information");
        failureAlert.show();
    }

    public static boolean showDossierDeletionConfirmation(int dossierId) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce dossier" + " [N° " + dossierId + "]?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setTitle("Bote de confirmation de suppression");
        Optional<ButtonType> choice = confirmationAlert.showAndWait();
        if (choice.get() == ButtonType.OK) {
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Dossier supprimé avec succès!");
            successAlert.setHeaderText(null);
            successAlert.setTitle("Boîte d'information");
            successAlert.show();
            return true;
        } else {
            Alert successAlert = new Alert(Alert.AlertType.WARNING, "Operation de suppression annulé!");
            successAlert.setHeaderText(null);
            successAlert.setTitle("Boîte d'information");
            successAlert.show();
            return false;
        }
    }

    public static boolean showTribunalDeletionConfirmation(String tribunalName) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce tribunal" + " [" + tribunalName + "]?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setTitle("Bote de confirmation de suppression");
        Optional<ButtonType> choice = confirmationAlert.showAndWait();
        if (choice.get() == ButtonType.CANCEL){
            Alert successAlert = new Alert(Alert.AlertType.WARNING, "Operation de suppression annulé!");
            successAlert.setHeaderText(null);
            successAlert.setTitle("Boîte d'information");
            successAlert.show();
            return false;
        } else {
            return true;
        }
    }

    public static void showTribunalDeletionSuccess() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Tribunal supprimé avec succès!");
        successAlert.setHeaderText(null);
        successAlert.setTitle("Boîte d'information");
        successAlert.show();
    }

    public static void showTribunalDeletionError() {
        Alert failureAlert = new Alert(Alert.AlertType.ERROR, "Un ou plusieurs dossiers contiennent deja ce tribunal!");
        failureAlert.setHeaderText("Le suppression du tribunal a échoué!");
        failureAlert.setTitle("Boite d'information");
        failureAlert.show();
    }

    public static boolean showAvocatDeletionConfirmation(String avocatName) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce avocat" + " [" + avocatName + "]?");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setTitle("Bote de confirmation de suppression");
        Optional<ButtonType> choice = confirmationAlert.showAndWait();
        if (choice.get() == ButtonType.CANCEL){
            Alert successAlert = new Alert(Alert.AlertType.WARNING, "Operation de suppression annulé!");
            successAlert.setHeaderText(null);
            successAlert.setTitle("Boîte d'information");
            successAlert.show();
            return false;
        } else {
            return true;
        }
    }

    public static void showAvocatDeletionSuccess() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Avocat supprimé avec succès!");
        successAlert.setHeaderText(null);
        successAlert.setTitle("Boîte d'information");
        successAlert.show();
    }

    public static void showAvocatDeletionError() {
        Alert failureAlert = new Alert(Alert.AlertType.ERROR, "Un ou plusieurs dossiers contiennent deja ce avocat!");
        failureAlert.setHeaderText("Le suppression du tribunal a échoué!");
        failureAlert.setTitle("Boite d'information");
        failureAlert.show();
    }

}
