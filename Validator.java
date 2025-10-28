package util;

public class Validator {
    public static boolean isValidText(String text) {
        return text != null && !text.trim().isEmpty();
    }
}
