// This file is part of the ATMOSPHERE mobile testing framework.
// Copyright (C) 2016 MusalaSoft
//
// ATMOSPHERE is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// ATMOSPHERE is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with ATMOSPHERE.  If not, see <http://www.gnu.org/licenses/>.

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
     * Gets the list of direct child nodes.<br>
     * <b>Note:</b> the method is intentionally named this way so that XPath queries using the <i>node</i> tag can
     * successfully traverse the structure.
     *
     * @return a list of direct child nodes
     */
    List<UiNode> getNode();
}
