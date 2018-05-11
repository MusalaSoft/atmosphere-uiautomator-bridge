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
