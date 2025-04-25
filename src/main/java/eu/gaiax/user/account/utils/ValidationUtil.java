package eu.gaiax.user.account.utils;

import java.util.regex.Pattern;

/**
 * For validating input parameters
 */
public class ValidationUtil {

    /**
     * Validating email address
     *
     * @param emailStr ...
     * @return ...
     */
    public static boolean validateEmail(String emailStr) {
        return Pattern
                .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                .matcher(emailStr)
                .find();
    }

}
