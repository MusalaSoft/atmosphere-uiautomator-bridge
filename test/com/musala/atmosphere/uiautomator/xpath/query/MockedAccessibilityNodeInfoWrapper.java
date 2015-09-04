package com.musala.atmosphere.uiautomator.xpath.query;

import java.util.ArrayList;
import java.util.List;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.util.CharSequenceUtils;
import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.tree.accessibility.AccessibilityNodeInfoWrapper;
import com.musala.atmosphere.uiautomator.xpath.xml.XmlBounds;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * {@link UiNode} implementation representing {@link AccessibilityNodeInfoWrapper}.
 *
 * @author yordan.petrov
 *
 */
class MockedAccessibilityNodeInfoWrapper implements UiNode {
    private AccessibilityNodeInfo mockedNode;

    private int index;

    private String path;

    private XmlBounds bounds;

    /**
     * Constructs an {@link MockedAccessibilityNodeInfoWrapper} by a given {@link AccessibilityNodeInfo}, index and
     * path.
     *
     * @param accessibilityNode
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     * @param index
     *        - the index of the node in the hierarchy
     * @param path
     *        - the path to the node from the root node - indexes of the ancestors separated by
     *        {@link AccessibilityElement#PATH_SEPARATOR}
     */
    public MockedAccessibilityNodeInfoWrapper(AccessibilityNodeInfo mockedNode, int index, String path) {
        this.mockedNode = mockedNode;
        this.index = index;
        this.path = path;
        this.bounds = new FakeXmlBounds(0, 0, 100, 100);
    }

    /**
     * Constructs an {@link MockedAccessibilityNodeInfoWrapper} by a given {@link AccessibilityNodeInfo}. Adds an empty
     * path ('') and index 0.
     *
     * @param accessibilityNode
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     */
    public MockedAccessibilityNodeInfoWrapper(AccessibilityNodeInfo mockedNode) {
        this(mockedNode, 0, "");
    }

    @Override
    public XmlBounds getBounds() {
        return bounds;
    }

    @Override
    public String getClassName() {
        return CharSequenceUtils.getString(mockedNode.getClassName());
    }

    @Override
    public String getPackage() {
        return CharSequenceUtils.getString(mockedNode.getPackageName());
    }

    @Override
    public String getResourceId() {
        return CharSequenceUtils.getString(mockedNode.getViewIdResourceName());
    }

    @Override
    public String getContentDesc() {
        return CharSequenceUtils.getString(mockedNode.getContentDescription());
    }

    @Override
    public String getText() {
        return CharSequenceUtils.getString(mockedNode.getText());
    }

    @Override
    public boolean isCheckable() {
        return mockedNode.isCheckable();
    }

    @Override
    public boolean isChecked() {
        return mockedNode.isChecked();
    }

    @Override
    public boolean isClickable() {
        return mockedNode.isClickable();
    }

    @Override
    public boolean isFocusable() {
        return mockedNode.isFocusable();
    }

    @Override
    public boolean isFocused() {
        return mockedNode.isFocused();
    }

    @Override
    public boolean isLongClickable() {
        return mockedNode.isLongClickable();
    }

    @Override
    public boolean isPassword() {
        return mockedNode.isPassword();
    }

    @Override
    public boolean isScrollable() {
        return mockedNode.isScrollable();
    }

    @Override
    public boolean isEnabled() {
        return mockedNode.isEnabled();
    }

    @Override
    public boolean isSelected() {
        return mockedNode.isSelected();
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public List<UiNode> getNode() {
        List<UiNode> wrappedChildren = new ArrayList<UiNode>(mockedNode.getChildCount());

        for (int index = 0; index < mockedNode.getChildCount(); index++) {
            AccessibilityNodeInfo child = mockedNode.getChild(index);

            UiNode wrapper = null;
            if (child != null) {
                String childPath = new StringBuilder().append(path)
                                                      .append(index)
                                                      .append(AccessibilityElement.PATH_SEPARATOR)
                                                      .toString();
                wrapper = new MockedAccessibilityNodeInfoWrapper(child, index, childPath);
            }

            // We add null values as well to keep the order
            wrappedChildren.add(wrapper);
        }

        return wrappedChildren;
    }

    @Override
    public boolean isVisible() {
        return mockedNode.isVisibleToUser();
    }
}
