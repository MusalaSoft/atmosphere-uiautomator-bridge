package com.musala.atmosphere.uiautomator.accessibility;

import android.os.Build;

import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorCompatMatcher;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcher;

/**
 * Factory for instantiating {@link UiElementSelectorMatcher} implementations, depending on the API Level of the current
 * device.
 * 
 * @author denis.bialev
 *
 */
public class UiElementSelectorMatcherFactory {
    private static final int LEGACY_UI_AUTOMATOR_API = Build.VERSION_CODES.JELLY_BEAN_MR1;

    /**
     * Retrieve {@link UiElementSelectorMatcher} instance, compatible with the current device.
     * 
     * @return - {@link UiElementSelectorMatcher} instance, compatible with the current device
     */
    public static UiElementSelectorMatcher getSelectorMatcher() {
        int currentApi = Build.VERSION.SDK_INT;
        if (currentApi <= LEGACY_UI_AUTOMATOR_API) {
            return new UiElementSelectorMatcher();
        } else {
            return new UiElementSelectorCompatMatcher();
        }
    }

}
