package com.musala.atmosphere.uiautomator.util;

import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;

/**
 * Utility class responsible for converting {@link UiElementPropertiesContainer} to string and this same string to a
 * {@link UiSelector} instance.
 * 
 * @author georgi.gaydarov
 * 
 */
public class UiSelectorParser {

    public static UiSelector convertSelector(UiElementPropertiesContainer propertiesContainer) {
        UiSelector selector = new UiSelector();
        Boolean checked = propertiesContainer.isChecked();
        String className = propertiesContainer.getClassName();
        Boolean clickable = propertiesContainer.isClickable();
        String description = propertiesContainer.getContentDescriptor();
        Boolean enabled = propertiesContainer.isEnabled();
        Boolean focusable = propertiesContainer.isFocusable();
        Boolean focused = propertiesContainer.isFocused();
        Integer index = propertiesContainer.getIndex();
        Boolean longClickable = propertiesContainer.isLongClickable();
        String packageName = propertiesContainer.getPackageName();
        Boolean scrollable = propertiesContainer.isScrollable();
        Boolean selected = propertiesContainer.isSelected();
        String text = propertiesContainer.getText();
        String resourceId = propertiesContainer.getResourceId();

        if (checked != null) {
            selector = selector.checked(checked);
        }

        if (className != null) {
            selector = selector.className(className);
        }

        if (clickable != null) {
            selector = selector.clickable(clickable);
        }

        if (description != null) {
            selector = selector.description(description);
        }

        if (enabled != null) {
            selector = selector.enabled(enabled);
        }

        if (focusable != null) {
            selector = selector.focused(focusable);
        }

        if (focused != null) {
            selector = selector.focused(focused);
        }

        if (index != null) {
            selector = selector.index(index);
        }

        if (longClickable != null) {
            selector = selector.longClickable(longClickable);
        }

        if (packageName != null) {
            selector = selector.packageName(packageName);
        }

        if (scrollable != null) {
            selector = selector.scrollable(scrollable);
        }

        if (selected != null) {
            selector = selector.selected(selected);
        }

        if (text != null) {
            selector = selector.text(text);
        }

        if (resourceId != null) {
            selector = selector.resourceId(resourceId);
        }

        return selector;
    }
}
