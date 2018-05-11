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

package com.musala.atmosphere.uiautomator.helper;

import java.io.File;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Defines a utility for operating with {@link AccessibilityNodeInfo accessibility nodes} based upon the screen UI
 * elements hierarchy.
 *
 * @author vassil.angelov
 *
 */
public interface AccessibilityHelper {

    /**
     * Gets the root {@link AccessibilityNodeInfo node} in the active window.
     *
     * @return the root {@link AccessibilityNodeInfo node} in the active window
     */
    AccessibilityNodeInfo getRootInActiveWindow();

    /**
     * Using {@link AccessibilityNodeInfo accessibility nodes} this method will walk the layout hierarchy and generates
     * an XML dump to the location specified by file.
     *
     * @param root
     *        - the root accessibility node
     *
     * @param file
     *        - the file to dump to
     */
    void dumpWindowToFile(AccessibilityNodeInfo root, File file);

    void initializeAccessibilityEventListener();

    /**
     * Gets the text of the last detected toast message.
     *
     * @return the text of the last detected toast message or <code>null</code> if such is not detected yet
     */
    String getLastToast();
}
