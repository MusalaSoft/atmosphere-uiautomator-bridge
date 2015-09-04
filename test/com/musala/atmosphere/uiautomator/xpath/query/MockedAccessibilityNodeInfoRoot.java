package com.musala.atmosphere.uiautomator.xpath.query;

import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.tree.UiRoot;
import com.musala.atmosphere.uiautomator.xpath.tree.accessibility.AccessibilityNodeInfoRoot;

/**
 * {@link UiRoot} implementation
 *
 * @author yordan.petrov
 *
 */
public class MockedAccessibilityNodeInfoRoot implements UiRoot {
    private UiNode mockedNode;

    /**
     * Creates {@link AccessibilityNodeInfoRoot} instance.
     * 
     * @param mockedNode
     *        - {@link UiNode} used for creating the instance
     */
    public MockedAccessibilityNodeInfoRoot(UiNode mockedNode) {
        this.mockedNode = mockedNode;
    }

    @Override
    public UiNode getNode() {
        return mockedNode;
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
