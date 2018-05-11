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

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.util.structure.tree.Tree;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.util.TreeBuilder;

/**
 * Class that is responsible for building a {@link Tree tree} representation of the current screen of the device.
 * 
 * @author yordan.petrov
 * 
 */
public class UiTreeBuilder implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        boolean visibleOnly = (Boolean) args[0];

        AccessibilityHelper helper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo root = helper.getRootInActiveWindow();
        Tree<AccessibilityElement> tree = TreeBuilder.buildTree(root, visibleOnly);

        return TreeBuilder.buildTree(root, visibleOnly);
    }
}
