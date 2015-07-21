package com.musala.atmosphere.uiautomator.util;

import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;

/**
 * Utility class responsible for converting {@link UiElementDescriptor} to string and this same string to a
 * {@link UiSelector} instance.
 * 
 * @author georgi.gaydarov
 * 
 */
public class UiSelectorParser {

    public static UiSelector convertSelector(UiElementDescriptor descriptor) {
        UiSelector selector = new UiSelector();
        Boolean checked = descriptor.isChecked();
        String className = descriptor.getClassName();
        Boolean clickable = descriptor.isClickable();
        String description = descriptor.getContentDescription();
        Boolean enabled = descriptor.isEnabled();
        Boolean focusable = descriptor.isFocusable();
        Boolean focused = descriptor.isFocused();
        Integer index = descriptor.getIndex();
        Boolean longClickable = descriptor.isLongClickable();
        String packageName = descriptor.getPackageName();
        Boolean scrollable = descriptor.isScrollable();
        Boolean selected = descriptor.isSelected();
        String text = descriptor.getText();
        String resourceId = descriptor.getResourceId();

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
