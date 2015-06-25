package com.musala.atmosphere.uiautomator.helper;

import android.os.Build;

import com.musala.atmosphere.uiautomator.exception.IncompatibleAndroidSdkException;

/**
 * Factory for instantiating {@link AccessibilityHelper} implementations.
 * 
 * @author vassil.angelov
 *
 */
public class AccessibilityHelperFactory {

    private static final int LEGACY_UI_AUTOMATOR_API = Build.VERSION_CODES.JELLY_BEAN_MR1;

    /**
     * Retrieve {@link AccessibilityHelper} instance, compatible with the current device.
     * 
     * @return - {@link AccessibilityHelper} instance, compatible with the current device
     * @throws IncompatibleAndroidSdkException
     *         if even the most compatible {@link AccessibilityHelper} instance is still incompatible with the current
     *         device SDK API
     */
    public static AccessibilityHelper getHelper() {
        int currentApi = Build.VERSION.SDK_INT;
        if (currentApi <= LEGACY_UI_AUTOMATOR_API) {
            return new AccessibilityHelperCompat();
        } else {
            return new AccessibilityHelperImpl();
        }
    }
}
