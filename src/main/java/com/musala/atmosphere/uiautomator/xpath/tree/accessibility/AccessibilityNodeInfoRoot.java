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

package com.musala.atmosphere.uiautomator.xpath.tree.accessibility;

import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.tree.UiRoot;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * {@link UiRoot} implementation using {@link UiNode} wrapper around {@link AccessibilityNodeInfo}.
 *
 * @author yordan.petrov
 *
 */
public class AccessibilityNodeInfoRoot implements UiRoot {
    private UiNode rootNode;

    /**
     * Constructs an {@link AccessibilityNodeInfoRoot} by a given {@link UiNode} root node.
     *
     * @param rootNode
     *        - the root node
     */
    public AccessibilityNodeInfoRoot(UiNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public UiNode getNode() {
        return rootNode;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
