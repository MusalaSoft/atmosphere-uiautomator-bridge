package com.musala.atmosphere.uiautomator.util;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;

/**
 * Class that extends {@link AccessibilityElementBuilder} by adding functionality for API 18 and above.
 * 
 * @author denis.bialev
 * 
 */
public class AccessibilityElementCompatBuilder extends AccessibilityElementBuilder {

    @Override
    protected void setCommonProperties(AccessibilityNodeInfo nodeInfo, AccessibilityElement element, int index) {
        super.setCommonProperties(nodeInfo, element, index);
        element.setResourceId(nodeInfo.getViewIdResourceName());
    }
}
