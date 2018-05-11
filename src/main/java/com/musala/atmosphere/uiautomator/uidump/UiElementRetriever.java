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

package com.musala.atmosphere.uiautomator.uidump;

import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementMatcher;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.util.AccessibilityNodeTraverser;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Class used to get all {@link AccessibilityElement UI elements} present on the screen and matching a given selector.
 *
 * @author denis.bialev
 *
 */
public class UiElementRetriever implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        UiElementSelector selector = (UiElementSelector) args[0];
        Boolean visibleOnly = (Boolean) args[1];

        AccessibilityHelper accessibilityHelper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo accessibilityRootNode = accessibilityHelper.getRootInActiveWindow();

        AccessibilityNodeTraverser accessibilityNodeTraverser = new AccessibilityNodeTraverser(accessibilityRootNode,
                                                                                               "");

        UiElementMatcher<UiElementSelector> selectorMatcher = AccessibilityFactory.getUiElementSelectorMatcher();

        return accessibilityNodeTraverser.find(selectorMatcher, selector, visibleOnly);
    }
}
