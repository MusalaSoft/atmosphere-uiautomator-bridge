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

import java.util.Stack;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Common interface for classes that build an {@link AccessibilityElement} based on {@link AccessibilityNodeInfo}.
 *
 * @author yordan.petrov
 *
 */
public interface AccessibilityElementBuilder {
    /**
     * Builds an {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}.
     *
     * @param nodeInfo
     *        - the {@link AccessibilityNodeInfo} the {@link AccessibilityElement} is being built on
     * @param index
     *        - index of the node
     * @return an {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}
     */
    public AccessibilityElement build(AccessibilityNodeInfo nodeInfo, int index);

    /**
     * Builds an {@link AccessibilityElement} based on a given {@link AccessibilityNodeInfo accessibility node
     * information} and the corresponding path from the root of the hierarchy to this element, represented with
     * element's indexes.
     *
     * @param nodeInfo
     *        - the {@link AccessibilityNodeInfo} the {@link AccessibilityElement} is being built on
     * @param pathIndexes
     *        - the path from the root of the hierarchy to the element returned from the
     *        {@link AccessibilityElementBuilder builder}.
     * @param pathToLocalRoot
     *        - path from the absolute root to the local root of the hierarchy
     * @param index
     *        - index of the node
     * @return an {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}
     */
    public AccessibilityElement build(AccessibilityNodeInfo nodeInfo,
                                      Stack<Integer> pathIndexes,
                                      String pathToLocalRoot,
                                      int index);
}
