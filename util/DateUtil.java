package util;

import java.time.LocalDate;

public class DateUtil {
    public static LocalDate arabicFormatToLocalDate(String arabicFormat) {
        String[] split = arabicFormat.split("/", 3);
        int year = Integer.parseInt(split[2]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[0]);
        return LocalDate.of(year, month, day);
    }

    public static String localDateToArabicFormat(LocalDate date) {
        String strDate = date.toString();
        String[] split2 = strDate.split("-", 3);
        return split2[2] + "/" + split2[1] + "/" + split2[0];
    }
}
