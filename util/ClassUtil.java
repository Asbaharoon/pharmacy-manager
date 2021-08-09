package util;

import java.lang.reflect.Field;

public class ClassUtil {
    public static boolean verifyProperties(Object obj) throws IllegalAccessException {
        Field[] prsnFields = obj.getClass().getDeclaredFields();
        for (Field field : prsnFields) {
            if (field.getType().toString().equals("int") || field.getType().toString().equals("long")) {
                if (field.get(obj).equals(0)) {
                    return false;
                }
            } else {
                if (field.get(obj) == null) {
                    System.err.println(field.getName());
                    return false;
                }
            }
        }
        return true;
    }
}
