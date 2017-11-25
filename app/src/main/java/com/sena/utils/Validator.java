package com.sena.utils;

import android.text.TextUtils;



    /*
    | ---------------
    | Class containing functions to validate: input data, strings ..
    | ---------------
    */


public class Validator {

    /**
     * Checks that a value is not empty
     *
     * @param value The String Value to validate
     * @return boolean, TRUE if its not Empty
     */

    public static boolean isNotEmpty(String value) {
        return !TextUtils.isEmpty(value);
    }

    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value);
    }

    public static boolean isNotEmpty(String... value) {
        for (String s :
                value) {
            if (TextUtils.isEmpty(s.trim())) return false;
        }
        return true;
    }



    /**
     * Validate the Email Address.
     *
     * @param target The Email Address to validate
     * @return boolean, TRUE is its valid
     */
    public final static boolean isValidEmail(String target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Matches the two PIN to check if they do match.
     *
     * @param pin        The first PIN
     * @param repeat_pin The repeat PIN
     * @return true if the two PINs match
     */
    public static boolean pinsMatch(String pin, String repeat_pin) {
        return pin.equals(repeat_pin);
    }
}
