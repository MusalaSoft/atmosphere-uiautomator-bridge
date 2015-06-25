package com.musala.atmosphere.uiautomator.accessibility;

import android.os.Build;

import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerCompatMatcher;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerMatcher;

/**
 * Factory for instantiating {@link UiElementPropertiesMatcher} implementations, depending on the API Level of the
 * current device.
 * 
 * @author denis.bialev
 *
 */
public class UiElementPropertiesContainerMatcherFactory {
    private static final int LEGACY_UI_AUTOMATOR_API = Build.VERSION_CODES.JELLY_BEAN_MR1;

    /**
     * Retrieve {@link UiElementPropertiesContainerMatcher} instance, compatible with the current device.
     * 
     * @return - {@link UiElementPropertiesContainerMatcher} instance, compatible with the current device
     */
    public static UiElementPropertiesContainerMatcher getPropertiesContainerMatcher() {
        int currentApi = Build.VERSION.SDK_INT;
        if (currentApi <= LEGACY_UI_AUTOMATOR_API) {
            return new UiElementPropertiesContainerMatcher();
        } else {
            return new UiElementPropertiesContainerCompatMatcher();
        }
    }

}
