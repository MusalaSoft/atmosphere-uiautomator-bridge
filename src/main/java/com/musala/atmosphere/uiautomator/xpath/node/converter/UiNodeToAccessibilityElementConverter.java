package com.musala.atmosphere.uiautomator.xpath.node.converter;

import org.apache.commons.jxpath.Pointer;

import com.musala.atmosphere.commons.geometry.Bounds;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.xml.XmlBounds;

/**
 * Converter class. Converts {@link UiNode} to {@link AccessibilityElement}.
 *
 * @author yordan.petrov
 *
 */
public class UiNodeToAccessibilityElementConverter {
    /**
     * Converts a given {@link UiNode} to an {@link AccessibilityElement} equivalent.
     *
     * @param node
     *        - the node to be converted
     * @return an {@link AccessibilityElement} instance equivalent to the given node
     */
    public static AccessibilityElement convertNode(UiNode node) {
        AccessibilityElement element = new AccessibilityElement();
        XmlBounds nodeXmlBounds = node.getBounds();
        Bounds nodeBounds = nodeXmlBounds.getBounds();
        element.setBounds(nodeBounds);
        element.setCheckable(node.isCheckable());
        element.setChecked(node.isChecked());
        element.setClassName(node.getClassName());
        element.setClickable(node.isClickable());
        element.setContentDescriptor(node.getContentDesc());
        element.setEnabled(node.isEnabled());
        element.setFocusable(node.isFocusable());
        element.setFocused(node.isFocused());
        element.setIndex(node.getIndex());
        element.setLongClickable(node.isLongClickable());
        element.setPackageName(node.getPackage());
        element.setPassword(node.isPassword());
        element.setResourceId(node.getResourceId());
        element.setScrollable(node.isScrollable());
        element.setSelected(node.isSelected());
        element.setText(node.getText());
        element.setPath(node.getPath());

        return element;
    }

    public static String convertPath(Pointer pointer) {
        String pointerPath = pointer.asPath();
        String[] nodes = pointerPath.split("/");

        /*
         * The format of the pointer is like "/child/child[x]/.../child[y]". After the split the first two "nodes" are:
         * "" and "child". If a third is missing we are at the root.
         */
        if (nodes.length < 3) {
            return "";
        }

        StringBuilder pathBuilder = new StringBuilder();
        // Since we have handled the case with the root
        for (int index = 2; index < nodes.length; index++) {
            // the format is 'node[someNumber]' so we drop 'node[' and ']'
            String nodeIndex = nodes[index].substring(5, nodes[index].length() - 1);
            Integer indexValue = Integer.valueOf(nodeIndex) - 1;
            pathBuilder.append(indexValue).append(AccessibilityElement.PATH_SEPARATOR);
        }

        return pathBuilder.toString();
    }
}
