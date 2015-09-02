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
