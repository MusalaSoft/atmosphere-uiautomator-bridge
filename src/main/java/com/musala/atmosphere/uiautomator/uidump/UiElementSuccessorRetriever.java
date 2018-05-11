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

import com.musala.atmosphere.commons.exceptions.UiElementFetchingException;
import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementMatcher;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.util.AccessibilityNodeTraverser;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Class that retrieves {@link AccessibilityElement child UI elements} for a given {@link AccessibilityElement
 * accessibility element}. Found children are present on the current screen and matching the given
 * {@link UiElementSelector selector}.
 *
 * @author filareta.yordanova
 *
 */
public class UiElementSuccessorRetriever implements Dispatchable {
    @Override
    public Object handle(Object[] args) throws Exception {
        AccessibilityElement parentElement = (AccessibilityElement) args[0];
        UiElementSelector successorSelector = (UiElementSelector) args[1];
        Boolean directChildrenOnly = (Boolean) args[2];
        Boolean visibleOnly = (Boolean) args[3];

        AccessibilityHelper accessibilityHelper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo accessibilityRootNode = accessibilityHelper.getRootInActiveWindow();

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(accessibilityRootNode, "");
        UiElementMatcher<UiElementPropertiesContainer> propertiesMatcher = AccessibilityFactory.getUiElementPropertiesContainerMatcher();

        AccessibilityNodeInfo parentAccessibilityNode = traverser.getCorrespondingAccessibilityNodeInfo(parentElement,
                                                                                                        propertiesMatcher,
                                                                                                        visibleOnly);

        if (parentAccessibilityNode == null) {
            throw new UiElementFetchingException("Element, for which retrieving children is requested, is not present on the screen.");
        }

        AccessibilityNodeTraverser successorsTraverser = new AccessibilityNodeTraverser(parentAccessibilityNode,
                                                                                        parentElement.getPath());

        UiElementMatcher<UiElementSelector> successorSelectorMatcher = AccessibilityFactory.getUiElementSelectorMatcher();

        return successorsTraverser.getChildren(successorSelectorMatcher,
                                               successorSelector,
                                               directChildrenOnly,
                                               visibleOnly);
    }
}
