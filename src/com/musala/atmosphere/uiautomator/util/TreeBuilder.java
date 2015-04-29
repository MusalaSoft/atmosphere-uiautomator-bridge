package com.musala.atmosphere.uiautomator.util;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.util.structure.tree.Node;
import com.musala.atmosphere.commons.util.structure.tree.Tree;

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
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return a {@link Node node} with {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}
     */
    private static Node<AccessibilityElement> buildNode(AccessibilityNodeInfo nodeInfo, int index, boolean visibleOnly) {
        if (nodeInfo == null) {
            return null;
        }

        AccessibilityElement elementData = AccessibilityElementBuilder.build(nodeInfo, index);
        Node<AccessibilityElement> builtNode = new Node<AccessibilityElement>(elementData);

        int childCount = nodeInfo.getChildCount();
        for (int childIndex = 0; childIndex < childCount; childIndex++) {
            if (visibleOnly && nodeInfo != null && !nodeInfo.isVisibleToUser()) {
                continue;
            }

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