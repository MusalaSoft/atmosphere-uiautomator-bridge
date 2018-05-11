// This file is part of the ATMOSPHERE mobile testing framework.
// Copyright (C) 2016 MusalaSoft
//
// ATMOSPHERE is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// ATMOSPHERE is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with ATMOSPHERE.  If not, see <http://www.gnu.org/licenses/>.

package com.musala.atmosphere.uiautomator.util;

import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.commons.ui.selector.CssAttribute;
import com.musala.atmosphere.commons.ui.selector.UiElementSelectionOption;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.util.Pair;

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
        Boolean clickable = propertiesContainer.isClickable();
        Boolean enabled = propertiesContainer.isEnabled();
        Boolean focusable = propertiesContainer.isFocusable();
        Boolean focused = propertiesContainer.isFocused();
        Integer index = propertiesContainer.getIndex();
        Boolean longClickable = propertiesContainer.isLongClickable();
        Boolean scrollable = propertiesContainer.isScrollable();
        Boolean selected = propertiesContainer.isSelected();
        String resourceId = propertiesContainer.getResourceId();

        if (propertiesContainer instanceof UiElementSelector) {
            UiElementSelector uiElementSelector = (UiElementSelector) propertiesContainer;

            Pair<String, UiElementSelectionOption> selectorClass = uiElementSelector.getStringValueWithSelectionOption(CssAttribute.CLASS_NAME);
            Pair<String, UiElementSelectionOption> selectorPackage = uiElementSelector.getStringValueWithSelectionOption(CssAttribute.PACKAGE_NAME);
            Pair<String, UiElementSelectionOption> selectorText = uiElementSelector.getStringValueWithSelectionOption(CssAttribute.TEXT);
            Pair<String, UiElementSelectionOption> selectorDescription = uiElementSelector.getStringValueWithSelectionOption(CssAttribute.CONTENT_DESCRIPTION);

            if (selectorClass != null) {
                selector = setUiSelectorClassNameWithSelectionOption(selector, selectorClass);
            }

            if (selectorPackage != null) {
                selector = setUiSelectorPackageWithSelectionOption(selector, selectorPackage);
            }

            if (selectorText != null) {
                selector = setUiSelectorTextWithSelectionOption(selector, selectorText);
            }

            if (selectorDescription != null) {
                selector = setUiSelectorDescriptionWithSelectionOption(selector, selectorDescription);
            }
        } else {
            String className = propertiesContainer.getClassName();
            String packageName = propertiesContainer.getPackageName();
            String text = propertiesContainer.getText();
            String description = propertiesContainer.getContentDescriptor();

            if (className != null) {
                selector = selector.className(className);
            }

            if (packageName != null) {
                selector = selector.packageName(packageName);
            }

            if (text != null) {
                selector = selector.text(text);
            }

            if (description != null) {
                selector = selector.description(description);
            }
        }

        if (checked != null) {
            selector = selector.checked(checked);
        }

        if (clickable != null) {
            selector = selector.clickable(clickable);
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

        if (scrollable != null) {
            selector = selector.scrollable(scrollable);
        }

        if (selected != null) {
            selector = selector.selected(selected);
        }

        if (resourceId != null) {
            selector = selector.resourceId(resourceId);
        }

        return selector;
    }

    private static UiSelector setUiSelectorClassNameWithSelectionOption(UiSelector uiSelector, Pair<String, UiElementSelectionOption> propertyPair) {
        switch (propertyPair.getValue()) {
            case CONTAINS:
                String pattern = "^.*%s.*$";
                uiSelector = uiSelector.classNameMatches(String.format(pattern, propertyPair.getKey()));
                break;
            case EQUALS:
                uiSelector = uiSelector.className(propertyPair.getKey());
                break;
            case WORD_MATCH:
                uiSelector = uiSelector.classNameMatches(propertyPair.getKey());
                break;
            default:
                uiSelector = uiSelector.className(propertyPair.getKey());
                break;
        }

        return uiSelector;
    }

    private static UiSelector setUiSelectorPackageWithSelectionOption(UiSelector uiSelector, Pair<String, UiElementSelectionOption> propertyPair) {
        switch (propertyPair.getValue()) {
            case CONTAINS:
                String pattern = "^.*%s.*$";
                uiSelector = uiSelector.packageNameMatches(String.format(pattern, propertyPair.getKey()));
                break;
            case EQUALS:
                uiSelector = uiSelector.packageName(propertyPair.getKey());
                break;
            case WORD_MATCH:
                uiSelector = uiSelector.packageNameMatches(propertyPair.getKey());
                break;
            default:
                uiSelector = uiSelector.packageName(propertyPair.getKey());
                break;
        }

        return uiSelector;
    }

    private static UiSelector setUiSelectorTextWithSelectionOption(UiSelector uiSelector, Pair<String, UiElementSelectionOption> propertyPair) {
        switch (propertyPair.getValue()) {
            case CONTAINS:
                uiSelector = uiSelector.textContains(propertyPair.getKey());
                break;
            case EQUALS:
                uiSelector = uiSelector.text(propertyPair.getKey());
                break;
            case WORD_MATCH:
                uiSelector = uiSelector.textMatches(propertyPair.getKey());
                break;
            default:
                uiSelector = uiSelector.text(propertyPair.getKey());
                break;

        }

        return uiSelector;
    }

    private static UiSelector setUiSelectorDescriptionWithSelectionOption(UiSelector uiSelector, Pair<String, UiElementSelectionOption> propertyPair) {
        switch (propertyPair.getValue()) {
            case CONTAINS:
                uiSelector = uiSelector.descriptionContains(propertyPair.getKey());
                break;
            case EQUALS:
                uiSelector = uiSelector.description(propertyPair.getKey());
                break;
            case WORD_MATCH:
                uiSelector = uiSelector.descriptionMatches(propertyPair.getKey());
                break;
            default:
                uiSelector = uiSelector.description(propertyPair.getKey());
                break;
        }

        return uiSelector;
    }
}
