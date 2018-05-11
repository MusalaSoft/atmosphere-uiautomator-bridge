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

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.util.structure.tree.Node;
import com.musala.atmosphere.commons.util.structure.tree.Tree;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Class that builds a {@link Tree tree} based on a root {@link AccessibilityNodeInfo}.
 *
 * @author yordan.petrov
 *
 */
public class TreeBuilder {
    /**
     * Recursively builds a {@link Node node} with {@link AccessibilityElement} based on the given
     * {@link AccessibilityNodeInfo}.
     *
     * @param nodeInfo
     *        - the {@link AccessibilityNodeInfo} on which the {@link Node node} is being built
     * @param index
     *        - index of the node
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return a {@link Node node} with {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}
     */
    private static Node<AccessibilityElement> buildNode(AccessibilityNodeInfo nodeInfo,
                                                        int index,
                                                        boolean visibleOnly) {
        if (nodeInfo == null) {
            return new Node<AccessibilityElement>(null);
        }

        if (visibleOnly && !nodeInfo.isVisibleToUser()) {
            return new Node<AccessibilityElement>(null);
        }

        AccessibilityElementBuilder accessibilityElementBuilder = AccessibilityFactory.getAccessibilityElementBuilder();
        AccessibilityElement elementData = accessibilityElementBuilder.build(nodeInfo, index);

        Node<AccessibilityElement> builtNode = new Node<AccessibilityElement>(elementData);

        int childCount = nodeInfo.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            AccessibilityNodeInfo childNodeInfo = nodeInfo.getChild(childIndex);

            Node<AccessibilityElement> childNode = buildNode(childNodeInfo, childIndex, visibleOnly);
            builtNode.addChild(childNode);
        }

        return builtNode;
    }

    /**
     * Builds a {@link Tree tree} with {@link AccessibilityElement} based on the given root
     * {@link AccessibilityNodeInfo}.
     *
     * @param root
     *        - the root {@link AccessibilityNodeInfo} on which the {@link Tree tree} is being built
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return a {@link Tree tree} with {@link AccessibilityElement} based on the given root
     *         {@link AccessibilityNodeInfo}
     */

    public static Tree<AccessibilityElement> buildTree(AccessibilityNodeInfo root, boolean visibleOnly) {
        Node<AccessibilityElement> rootNode = buildNode(root, 0, visibleOnly);
        Tree<AccessibilityElement> builtTree = new Tree<AccessibilityElement>(rootNode);
        return builtTree;
    }
}
