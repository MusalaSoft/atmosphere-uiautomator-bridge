package com.musala.atmosphere.uiautomator.util;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * {@link AccessibilityElementBuilder} implementation that extends {@link AccessibilityElementBuilderCompat} by adding
 * functionality for <b>API 18 and above</b>. <br>
 * For API 17 use {@link AccessibilityElementBuilderCompat}.
 *
 * @author denis.bialev
 *
 */
public class AccessibilityElementBuilderImpl extends AccessibilityElementBuilderCompat {
    @Override
    protected void setCommonProperties(AccessibilityNodeInfo nodeInfo, AccessibilityElement element, int index) {
        super.setCommonProperties(nodeInfo, element, index);
        element.setResourceId(nodeInfo.getViewIdResourceName());
    }
}
