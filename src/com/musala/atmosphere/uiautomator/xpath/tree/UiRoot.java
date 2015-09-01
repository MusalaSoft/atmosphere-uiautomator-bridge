package com.musala.atmosphere.uiautomator.xpath.tree;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Common interface for UI root containing a link to the root UI node of the {@link AccessibilityNodeInfo} hierarchy.
 *
 * @author yordan.petrov
 *
 */
public interface UiRoot {
    /**
     * Gets the link to the root UI node of the {@link AccessibilityNodeInfo} hierarchy.
     *
     * @return a link to the root UI node
     */
    UiNode getNode();

    /**
     * Just returns <code>true</code> for XPath query compatibility.
     *
     * @return returns <code>true</code>
     */
    boolean isVisible();
}
