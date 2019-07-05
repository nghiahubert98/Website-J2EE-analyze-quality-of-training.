package uit.utils;

public class ValidateUtils {
    public static Boolean canConvertToInt(final String str) {
        try {
           if (str != null && !str.isEmpty()) {
               Integer.parseInt(str);
               return true;
           } else {
               return false;
           }
        } catch (Exception e) {
           return false;
        }
    }
    
    public static Boolean canConvertToBoolean(final String str) {
        try {
            if (str != null && !str.isEmpty()) {
                Boolean.parseBoolean(str);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static Boolean canConvertToFloat(final String str) {
        try {
            if (str != null && !str.isEmpty()) {
                Float.parseFloat(str);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }   
    }
}
