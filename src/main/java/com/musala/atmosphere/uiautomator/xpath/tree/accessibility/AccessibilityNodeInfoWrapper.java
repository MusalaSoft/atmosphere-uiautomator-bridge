package com.musala.atmosphere.uiautomator.xpath.tree.accessibility;

import java.util.ArrayList;
import java.util.List;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.util.CharSequenceUtils;
import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.xml.AndroidBounds;
import com.musala.atmosphere.uiautomator.xpath.xml.XmlBounds;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * {@link UiNode} abstract implementation that wraps an {@link AccessibilityNodeInfo} object.
 *
 * @author yordan.petrov
 *
 */
public abstract class AccessibilityNodeInfoWrapper implements UiNode {
    protected AccessibilityNodeInfo accessibilityNode;

    private int index;

    private String path;

    private XmlBounds bounds;

    /**
     * Constructs an {@link AccessibilityNodeInfoWrapper} by a given {@link AccessibilityNodeInfo}. Adds an empty path
     * ('') and index 0.
     *
     * @param accessibilityNode
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     */
    public AccessibilityNodeInfoWrapper(AccessibilityNodeInfo accessibilityNode) {
        this(accessibilityNode, 0, "");
    }

    /**
     * Constructs an {@link AccessibilityNodeInfoWrapper} by a given {@link AccessibilityNodeInfo} and an index. Adds an
     * empty path - ''.
     *
     * @param accessibilityNode
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     * @param index
     *        - the index of the node in the hierarchy
     */
    public AccessibilityNodeInfoWrapper(AccessibilityNodeInfo accessibilityNode, int index) {
        this(accessibilityNode, index, "");
    }

    /**
     * Constructs an {@link AccessibilityNodeInfoWrapper} by a given {@link AccessibilityNodeInfo}, index and path.
     *
     * @param accessibilityNode
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     * @param index
     *        - the index of the node in the hierarchy
     * @param path
     *        - the path to the node from the root node - indexes of the ancestors separated by
     *        {@link AccessibilityElement#PATH_SEPARATOR}
     */
    public AccessibilityNodeInfoWrapper(AccessibilityNodeInfo accessibilityNode, int index, String path) {
        this.accessibilityNode = accessibilityNode;
        this.index = index;
        this.path = path;
        Rect rectBounds = new Rect();
        accessibilityNode.getBoundsInScreen(rectBounds);
        this.bounds = new AndroidBounds(rectBounds);
    }

    @Override
    public XmlBounds getBounds() {
        return bounds;
    }

    @Override
    public String getClassName() {
        return CharSequenceUtils.getString(accessibilityNode.getClassName());
    }

    @Override
    public String getPackage() {
        return CharSequenceUtils.getString(accessibilityNode.getPackageName());
    }

    @Override
    public abstract String getResourceId();

    @Override
    public String getContentDesc() {
        return CharSequenceUtils.getString(accessibilityNode.getContentDescription());
    }

    @Override
    public String getText() {
        return CharSequenceUtils.getString(accessibilityNode.getText());
    }

    @Override
    public boolean isCheckable() {
        return accessibilityNode.isCheckable();
    }

    @Override
    public boolean isChecked() {
        return accessibilityNode.isChecked();
    }

    @Override
    public boolean isClickable() {
        return accessibilityNode.isClickable();
    }

    @Override
    public boolean isFocusable() {
        return accessibilityNode.isFocusable();
    }

    @Override
    public boolean isFocused() {
        return accessibilityNode.isFocused();
    }

    @Override
    public boolean isLongClickable() {
        return accessibilityNode.isLongClickable();
    }

    @Override
    public boolean isPassword() {
        return accessibilityNode.isPassword();
    }

    @Override
    public boolean isScrollable() {
        return accessibilityNode.isScrollable();
    }

    @Override
    public boolean isEnabled() {
        return accessibilityNode.isEnabled();
    }

    @Override
    public boolean isSelected() {
        return accessibilityNode.isSelected();
    }

    @Override
    public int getIndex() {
        return index;
    }

    /**
     * Used when {@link AccessibilityNodeInfoWrapper#getNode()} is called. Wraps a single {@link AccessibilityNodeInfo}
     * child into {@link UiNode}.
     *
     * @param child
     *        - the child to be wrapped
     * @param index
     *        - the index of the child
     * @param path
     *        - the path to the child
     * @return {@link UiNode} wrapping the given child
     */
    protected abstract UiNode wrapChild(AccessibilityNodeInfo child, int index, String path);

    /**
     * Wrapping the hash code as well. Used for comparison as we don't want obtain more than one wrapper for object.
     */
    @Override
    public int hashCode() {
        return accessibilityNode.hashCode();
    }

    /**
     * Two wrappers are equal when they wrap the same object.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AccessibilityNodeInfoWrapper)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        AccessibilityNodeInfoWrapper other = (AccessibilityNodeInfoWrapper) obj;
        return other.accessibilityNode.equals(this.accessibilityNode);
    }

    @Override
    public List<UiNode> getNode() {
        List<UiNode> wrappedChildren = new ArrayList<UiNode>(accessibilityNode.getChildCount());

        for (int index = 0; index < accessibilityNode.getChildCount(); index++) {
            AccessibilityNodeInfo child = accessibilityNode.getChild(index);

            UiNode wrapper = null;
            if (child != null) {
                String childPath = new StringBuilder().append(path)
                                                      .append(index)
                                                      .append(AccessibilityElement.PATH_SEPARATOR)
                                                      .toString();
                wrapper = wrapChild(child, index, childPath);
            }

            // We add null values as well to keep the order
            wrappedChildren.add(wrapper);
        }

        return wrappedChildren;
    }

    @Override
    public boolean isVisible() {
        return accessibilityNode.isVisibleToUser();
    }

    @Override
    public String getPath() {
        return path;
    }
}
