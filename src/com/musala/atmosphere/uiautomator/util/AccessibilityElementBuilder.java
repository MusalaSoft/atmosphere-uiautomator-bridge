package com.musala.atmosphere.uiautomator.util;

import java.util.Stack;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.geometry.Bounds;
import com.musala.atmosphere.commons.geometry.Point;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;

/**
 * Class that builds an {@link AccessibilityElement} based on {@link AccessibilityNodeInfo}.
 * 
 * @author yordan.petrov
 * 
 */
public class AccessibilityElementBuilder {
    private static final String PATH_SEPARATOR = ", ";

    /**
     * Builds an {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}.
     * 
     * @param nodeInfo
     *        - the {@link AccessibilityNodeInfo} the {@link AccessibilityElement} is being built on
     * @return an {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}
     */
    public static AccessibilityElement build(AccessibilityNodeInfo nodeInfo, int index) {
        if (nodeInfo == null) {
            return null;
        }

        AccessibilityElement element = new AccessibilityElement();
        setCommonProperties(nodeInfo, element, index);

        return element;
    }

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
     * @return an {@link AccessibilityElement} based on the given {@link AccessibilityNodeInfo}
     */
    public static AccessibilityElement build(AccessibilityNodeInfo nodeInfo, Stack<Integer> pathIndexes, int index) {
        if (nodeInfo == null) {
            return null;
        }

        AccessibilityElement element = new AccessibilityElement();
        setCommonProperties(nodeInfo, element, index);
        element.setPath(getPathRepresentation(pathIndexes));

        return element;
    }

    private static void setCommonProperties(AccessibilityNodeInfo nodeInfo, AccessibilityElement element, int index) {
        element.setText(charSeqToString(nodeInfo.getText()));
        element.setClassName(charSeqToString(nodeInfo.getClassName()));
        element.setPackageName(charSeqToString(nodeInfo.getPackageName()));
        element.setContentDescriptor(charSeqToString(nodeInfo.getContentDescription()));
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
     * Converts a given {@link CharSequence char sequence} to string.
     * 
     * @param charSequence
     *        - the {@link CharSequence char sequence} to be converted
     * @return a String build from the given {@link CharSequence char sequence}
     */
    private static String charSeqToString(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }

        final StringBuilder builder = new StringBuilder(charSequence.length());
        builder.append(charSequence);
        return builder.toString();
    }

    /**
     * Builds a string representation of the path from the root of the hierarchy to the element returned from the
     * {@link AccessibilityElementBuilder builder}.
     * 
     * @param pathIndexes
     *        - path from the root of the hierarchy to the element returned from the {@link AccessibilityElementBuilder
     *        builder}, containing elements indexes
     * @return string representation of the path
     */
    private static String getPathRepresentation(Stack<Integer> pathIndexes) {
        StringBuilder pathRepresentation = new StringBuilder();

        if (!pathIndexes.isEmpty()) {
            pathRepresentation.append(pathIndexes.pop());
        }

        while (!pathIndexes.isEmpty()) {
            pathRepresentation.append(PATH_SEPARATOR).append(pathIndexes.pop());
        }

        return pathRepresentation.toString();
    }
}
