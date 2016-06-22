package com.musala.atmosphere.uiautomator.util;

import java.util.Stack;

import com.musala.atmosphere.commons.geometry.Bounds;
import com.musala.atmosphere.commons.geometry.Point;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * {@link AccessibilityElementBuilder} implementation intended <b>only for API 17</b>.<br/>
 * For API 18 and above use {@link AccessibilityElementBuilderImpl}.
 *
 * @author yordan.petrov
 *
 */
public class AccessibilityElementBuilderCompat implements AccessibilityElementBuilder {
    @Override
    public AccessibilityElement build(AccessibilityNodeInfo nodeInfo, int index) {
        if (nodeInfo == null) {
            return null;
        }

        AccessibilityElement element = new AccessibilityElement();
        setCommonProperties(nodeInfo, element, index);

        return element;
    }

    @Override
    public AccessibilityElement build(AccessibilityNodeInfo nodeInfo,
                                      Stack<Integer> pathIndexes,
                                      String pathToLocalRoot,
                                      int index) {
        if (nodeInfo == null) {
            return null;
        }

        AccessibilityElement element = new AccessibilityElement();
        setCommonProperties(nodeInfo, element, index);
        element.setPath(getPathRepresentation(pathToLocalRoot, pathIndexes));

        return element;
    }

    protected void setCommonProperties(AccessibilityNodeInfo nodeInfo, AccessibilityElement element, int index) {
        element.setText(CharSequenceUtils.getString(nodeInfo.getText()));
        element.setClassName(CharSequenceUtils.getString(nodeInfo.getClassName()));
        element.setPackageName(CharSequenceUtils.getString(nodeInfo.getPackageName()));
        element.setContentDescriptor(CharSequenceUtils.getString(nodeInfo.getContentDescription()));
        element.setChechkable(nodeInfo.isCheckable());
        element.setChechked(nodeInfo.isChecked());
        element.setClickable(nodeInfo.isClickable());
        element.setEnabled(nodeInfo.isEnabled());
        element.setFocusable(nodeInfo.isFocusable());
        element.setFocused(nodeInfo.isFocused());
        element.setScrollable(nodeInfo.isScrollable());
        element.setLongClickable(nodeInfo.isLongClickable());
        element.setPassword(nodeInfo.isPassword());
        element.setSelected(nodeInfo.isSelected());
        element.setIndex(index);

        Rect boundsRect = new Rect();
        nodeInfo.getBoundsInScreen(boundsRect);
        Point topLeft = new Point(boundsRect.left, boundsRect.top);
        Point bottomRight = new Point(boundsRect.right, boundsRect.bottom);
        Bounds bounds = new Bounds(topLeft, bottomRight);

        element.setBounds(bounds);
    }

    /**
     * Builds a string representation of the path from the root of the hierarchy to the element returned from the
     * {@link AccessibilityElementBuilderCompat builder}.
     *
     * @param pathToLocalRoot
     *        - path from the absolute root to the local root of the hierarchy
     * @param pathIndexes
     *        - path from the root of the hierarchy to the element returned from the
     *        {@link AccessibilityElementBuilderCompat builder}, containing elements indexes
     * @return string representation of the path
     */
    private static String getPathRepresentation(String pathToLocalRoot, Stack<Integer> pathIndexes) {
        StringBuilder pathRepresentation = new StringBuilder(pathToLocalRoot);

        while (!pathIndexes.isEmpty()) {
            pathRepresentation.append(pathIndexes.pop()).append(AccessibilityElement.PATH_SEPARATOR);
        }

        return pathRepresentation.toString();
    }
}
