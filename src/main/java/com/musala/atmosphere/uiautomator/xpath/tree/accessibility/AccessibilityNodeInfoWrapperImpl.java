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

package com.musala.atmosphere.uiautomator.xpath.tree.accessibility;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.util.CharSequenceUtils;
import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * {@link UiNode} implementation that extends {@link AccessibilityNodeInfoWrapper} intended <b>only for API level 18 and
 * aboce</b>.<br>
 * For API level 17 use {@link AccessibilityNodeInfoWrapperCompat}.
 *
 * @author yordan.petrov
 *
 */
public class AccessibilityNodeInfoWrapperImpl extends AccessibilityNodeInfoWrapper {
    /**
     * Constructs an {@link AccessibilityNodeInfoWrapperImpl} by a given {@link AccessibilityNodeInfo}. Adds an empty
     * path ('') and index 0.
     *
     * @param accessibilityNode
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     */
    public AccessibilityNodeInfoWrapperImpl(AccessibilityNodeInfo accessibilityNode) {
        super(accessibilityNode, 0, "");
    }

    /**
     * Constructs an {@link AccessibilityNodeInfoWrapperImpl} by a given {@link AccessibilityNodeInfo} and an index.
     * Adds an empty path - ''.
     *
     * @param accessibilityNode
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     * @param index
     *        - the index of the node in the hierarchy
     */
    public AccessibilityNodeInfoWrapperImpl(AccessibilityNodeInfo accessibilityNode, int index) {
        super(accessibilityNode, index);
    }

    /**
     * Constructs an {@link AccessibilityNodeInfoWrapperImpl} by a given {@link AccessibilityNodeInfo}, index and path.
     *
     * @param ani
     *        - the {@link AccessibilityNodeInfo} to be wrapped
     * @param index
     *        - the index of the node in the hierarchy
     * @param path
     *        - the path to the node from the root node - indexes of the ancestors separated by
     *        {@link AccessibilityElement#PATH_SEPARATOR}
     */
    public AccessibilityNodeInfoWrapperImpl(AccessibilityNodeInfo ani, int index, String path) {
        super(ani, index, path);
    }

    @Override
    public String getResourceId() {
        return CharSequenceUtils.getString(accessibilityNode.getViewIdResourceName());
    }

    @Override
    protected UiNode wrapChild(AccessibilityNodeInfo child, int index, String path) {
        return new AccessibilityNodeInfoWrapperImpl(child, index, path);
    }

    @Override
    public int hashCode() {
        return accessibilityNode.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AccessibilityNodeInfoWrapperImpl)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        AccessibilityNodeInfoWrapperImpl other = (AccessibilityNodeInfoWrapperImpl) obj;
        return other.accessibilityNode.equals(this.accessibilityNode);
    }
}
