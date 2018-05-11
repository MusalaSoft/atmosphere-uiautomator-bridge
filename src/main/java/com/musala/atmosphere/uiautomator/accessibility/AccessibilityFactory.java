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

package com.musala.atmosphere.uiautomator.accessibility;

import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementMatcher;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerMatcherCompat;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerMatcherImpl;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcherCompat;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcherImpl;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperCompat;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperImpl;
import com.musala.atmosphere.uiautomator.util.AccessibilityElementBuilder;
import com.musala.atmosphere.uiautomator.util.AccessibilityElementBuilderCompat;
import com.musala.atmosphere.uiautomator.util.AccessibilityElementBuilderImpl;
import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.tree.UiRoot;
import com.musala.atmosphere.uiautomator.xpath.tree.accessibility.AccessibilityNodeInfoRoot;
import com.musala.atmosphere.uiautomator.xpath.tree.accessibility.AccessibilityNodeInfoWrapperCompat;
import com.musala.atmosphere.uiautomator.xpath.tree.accessibility.AccessibilityNodeInfoWrapperImpl;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Factory for all accessibility related classes that depend on the device API level.
 *
 * @author denis.bialev
 * @author yordan.petrov
 *
 */
public class AccessibilityFactory {
    private static final boolean IS_LEGACY = Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1;

    /**
     * Returns an {@link AccessibilityHelper} instance compatible with the current API level.
     *
     * @return an {@link AccessibilityHelper} instance compatible with the current API level
     */
    public static AccessibilityHelper getAccessibilityHelper() {
        return IS_LEGACY ? new AccessibilityHelperCompat() : new AccessibilityHelperImpl();
    }

    /**
     * Returns an {@link AccessibilityElementBuilder} instance compatible with the current API level.
     *
     * @return an {@link AccessibilityElementBuilder} instance compatible with the current API level
     */
    public static AccessibilityElementBuilder getAccessibilityElementBuilder() {
        return IS_LEGACY ? new AccessibilityElementBuilderCompat() : new AccessibilityElementBuilderImpl();
    }

    /**
     * Returns an {@link UiElementMatcher} instance matching {@link UiElementPropertiesContainer} compatible with the
     * current API level.
     *
     * @return an {@link UiElementMatcher} instance compatible with the current API level
     */
    public static UiElementMatcher<UiElementPropertiesContainer> getUiElementPropertiesContainerMatcher() {
        return IS_LEGACY ? new UiElementPropertiesContainerMatcherCompat()
                : new UiElementPropertiesContainerMatcherImpl();
    }

    /**
     * Returns an {@link UiElementMatcher} instance matching {@link UiElementSelector} compatible with the current API
     * level.
     *
     * @return an {@link UiElementMatcher} instance compatible with the current API level
     */
    public static UiElementMatcher<UiElementSelector> getUiElementSelectorMatcher() {
        return IS_LEGACY ? new UiElementSelectorMatcherCompat() : new UiElementSelectorMatcherImpl();
    }

    /**
     * Gets an {@link UiRoot} instance wrapping a given {@link AccessibilityNodeInfo} compatible wit the current API
     * level.
     *
     * @param root
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     * @return an {@link UiRoot} instance compatible wit the current API level
     */
    public static UiRoot getUiRoot(AccessibilityNodeInfo root) {
        UiNode rootUiNode = IS_LEGACY ? new AccessibilityNodeInfoWrapperCompat(root)
                : new AccessibilityNodeInfoWrapperImpl(root);
        return new AccessibilityNodeInfoRoot(rootUiNode);
    }
}
