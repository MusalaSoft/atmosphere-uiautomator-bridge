package com.musala.atmosphere.uiautomator.accessibility;

import android.os.Build;

import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerCompatMatcher;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerMatcher;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorCompatMatcher;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcher;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperCompat;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperImpl;
import com.musala.atmosphere.uiautomator.util.AccessibilityElementBuilder;
import com.musala.atmosphere.uiautomator.util.AccessibilityElementCompatBuilder;

/**
 * 
 * @author denis.bialev
 *
 */
public class AccessibilityFactory {
    private static AccessibilityHelper accessibilityHelper;

    private static AccessibilityElementBuilder accessibilityElementBuilder;

    private static UiElementPropertiesContainerMatcher uiElementPropertiesContainerMatcher;

    private static UiElementSelectorMatcher uiElementSelectorMatcher;

    private static final int LEGACY_UI_AUTOMATOR_API = Build.VERSION_CODES.JELLY_BEAN_MR1;

    public static void initialize() {
        int currentApi = Build.VERSION.SDK_INT;
        if (currentApi <= LEGACY_UI_AUTOMATOR_API) {
            uiElementSelectorMatcher = new UiElementSelectorMatcher();
            accessibilityHelper = new AccessibilityHelperCompat();
            accessibilityElementBuilder = new AccessibilityElementBuilder();
            uiElementPropertiesContainerMatcher = new UiElementPropertiesContainerMatcher();
        } else {
            uiElementSelectorMatcher = new UiElementSelectorCompatMatcher();
            accessibilityHelper = new AccessibilityHelperImpl();
            accessibilityElementBuilder = new AccessibilityElementCompatBuilder();
            uiElementPropertiesContainerMatcher = new UiElementPropertiesContainerCompatMatcher();
        }
    }

    public static AccessibilityHelper getAccessibilityHelper() {
        return accessibilityHelper;
    }

    public static AccessibilityElementBuilder getAccessibilityElementBuilder() {
        return accessibilityElementBuilder;
    }

    public static UiElementPropertiesContainerMatcher getUiElementPropertiesContainerMatcher() {
        return uiElementPropertiesContainerMatcher;
    }

    public static UiElementSelectorMatcher getUiElementSelectorMatcher() {
        return uiElementSelectorMatcher;
    }
}
