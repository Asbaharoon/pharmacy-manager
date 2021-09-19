package util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Utils {
    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static LocalDateTime stringToLocalDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }


    public static String capFirstLetter(String str) {
        if (!str.isEmpty()){
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();

        }
        return null;
    }

    public static LocalDateTime formatDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }

    public static String beautifyPhoneNumber(String number) { // Adds a space after each 2 numbers
        char[] charArray = number.toCharArray();
        StringBuilder beautifiedNumber = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            if (i != 0 && i % 2 == 0) {
                assert false;
                beautifiedNumber.append(" ");
                beautifiedNumber.append(charArray[i]);
            } else {
                beautifiedNumber.append(charArray[i]);
            }
        }
        return beautifiedNumber.toString();
    }

    public static String setCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        String date = dateFormat.format(Calendar.getInstance().getTime());
        String time = timeFormat.format(Calendar.getInstance().getTime());
        return date + " " + time;
    }
}
