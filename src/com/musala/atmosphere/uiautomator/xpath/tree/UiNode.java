package com.musala.atmosphere.uiautomator.xpath.tree;

import java.util.List;

import com.musala.atmosphere.uiautomator.xpath.xml.XmlBounds;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Common interface for UI nodes representing the {@link AccessibilityNodeInfo}'s UI attributes that are used in the XML
 * dump.
 *
 * @author yordan.petrov
 *
 */
public interface UiNode {
    XmlBounds getBounds();

    String getClassName();

    String getPackage();

    String getResourceId();

    String getContentDesc();

    String getText();

    boolean isCheckable();

    boolean isChecked();

    boolean isClickable();

    boolean isFocusable();

    boolean isFocused();

    boolean isLongClickable();

    boolean isPassword();

    boolean isScrollable();

    boolean isEnabled();

    boolean isSelected();

    boolean isVisible();

    int getIndex();

    String getPath();

    /**
     * Gets the list of direct child nodes.<br/>
     * <b>Note:</b> the method is intentionally named this way so that XPath queries using the <i>node</i> tag can
     * successfully traverse the structure.
     *
     * @return a list of direct child nodes
     */
    List<UiNode> getNode();
}
