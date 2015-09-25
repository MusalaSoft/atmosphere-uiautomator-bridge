package com.musala.atmosphere.uiautomator.util;

/**
 * Utilities for {@link CharSequence} objects.
 *
 * @author yordan.petrov
 *
 */
public class CharSequenceUtils {
    /**
     * Converts a given {@link CharSequence char sequence} to string.
     *
     * @param charSequence
     *        - the {@link CharSequence char sequence} to be converted
     * @return a String build from the given {@link CharSequence char sequence}
     */
    public static String getString(CharSequence sequence) {
        if (sequence == null) {
            return null;
        }

        // sequence.toString() may have been overridden
        return new StringBuilder(sequence.length()).append(sequence).toString();
    }
}
