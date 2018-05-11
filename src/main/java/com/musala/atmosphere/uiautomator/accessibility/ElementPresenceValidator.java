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
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementMatcher;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.util.AccessibilityNodeTraverser;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Class that is responsible for validating that a given element is present on the screen.
 *
 * @author yordan.petrov
 *
 */
public class ElementPresenceValidator implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        AccessibilityElement element = (AccessibilityElement) args[0];
        boolean visibleOnly = (Boolean) args[1];

        AccessibilityHelper helper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo root = helper.getRootInActiveWindow();

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(root, "");
        UiElementMatcher<UiElementPropertiesContainer> propertiesMatcher = AccessibilityFactory.getUiElementPropertiesContainerMatcher();

        AccessibilityNodeInfo foundElement = traverser.getCorrespondingAccessibilityNodeInfo(element,
                                                                                             propertiesMatcher,
                                                                                             visibleOnly);
        return foundElement != null;
    }
}
