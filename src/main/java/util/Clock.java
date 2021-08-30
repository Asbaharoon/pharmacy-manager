package util;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Clock {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static void setDateAndTimeAsync(Label dateAndTimeLabel) {
        Thread thread = new Thread(() -> {

            Runnable updater = () -> {
                String time = timeFormat.format(Calendar.getInstance().getTime());
                String date = dateFormat.format(Calendar.getInstance().getTime());
                dateAndTimeLabel.setText(time + " " + date);
            };

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }

                // UI update is run on the Application thread
                Platform.runLater(updater);
            }
        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    }
}
