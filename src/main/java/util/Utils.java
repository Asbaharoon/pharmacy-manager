package util;

public class Utils {
    public static boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String capFirstLetter(String str) {
        if (!str.isEmpty()){
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();

        }
        return null;
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
}
