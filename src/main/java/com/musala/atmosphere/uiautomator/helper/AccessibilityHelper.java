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