package util;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class TransitionsUtil {

    public static void playIconTransition (ImageView icon, String pngName) {
        File file = new File("src\\main\\resources\\images\\" + pngName);
        Image image = new Image(file.toURI().toString());
        icon.setImage(image);
        FadeTransition fadeIn = fadeIn(icon);
        FadeTransition fadeOut = fadeOut(icon);
        SequentialTransition transition = new SequentialTransition(icon, fadeIn, fadeOut);
        transition.play();
    }

    public static void playLabelTransition(Label label, String text, String colorHex) {
        label.setStyle("-fx-text-fill: " + colorHex);
        label.setText(text);
        label.setVisible(true);
        FadeTransition fadeIn = fadeIn(label);
        FadeTransition fadeOut = fadeOut(label);
        SequentialTransition transition = new SequentialTransition(label, fadeIn, fadeOut);
        transition.play();
    }

    private static FadeTransition fadeIn(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(1), node);
        fade.setFromValue(0);
        fade.setToValue(1);
        return fade;
    }

    private static FadeTransition fadeOut(Node node) {
        FadeTransition fade = new FadeTransition(Duration.seconds(4), node);
        fade.setFromValue(1);
        fade.setToValue(0);
        return fade;
    }

}
