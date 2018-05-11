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
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.android.uiautomator.core.AccessibilityNodeInfoDumper;
import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.uiautomator.exception.IncompatibleAndroidSdkException;
import com.musala.atmosphere.uiautomator.helper.util.AccessibilityEventHandler;
import com.musala.atmosphere.uiautomator.helper.util.AccessibilityEventHandlerCompat;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/**
 * {@link AccessibilityHelper} implementation compatible <b>only</b> with API level 17.<br>
 * For API 18 use {@link AccessibilityHelperImpl}.
 *
 * @author vassil.angelov
 *
 */
public class AccessibilityHelperCompat implements AccessibilityHelper {

    private static final String UI_AUTOMATOR_BRIDGE_ADD_ACCESSIBILITY_EVENT_LISTENER_METHOD_NAME = "addAccessibilityEventListener";

    private static final String ACCESSIBILITY_EVENT_LISTENER_INTERFACE_NAME = "AccessibilityEventListener";

    private static final String UI_DEVICE_GET_AUTOMATOR_BRIDGE_METHOD_NAME = "getAutomatorBridge";

    private static final String UI_TEST_AUTOMATION_BRIDGE_CLASS_NAME = "android.accessibilityservice.UiTestAutomationBridge";

    private static final String UI_TEST_AUTOMATION_BRIDGE_GET_ROOT_NODE_METHOD_NAME = "getRootAccessibilityNodeInfoInActiveWindow";

    private static final String NODE_INFO_DUMPER_DUMP_WINDOW_TO_FILE_METHOD_NAME = "dumpWindowToFile";

    private static final String INCOMPATIBILITY_ERROR_FORMAT = "%s is compatible only with Android SDK API level 17 and the device has API level %d";

    private static final String INCOMPATIBILITY_ERROR_MESSAGE = String.format(INCOMPATIBILITY_ERROR_FORMAT,
                                                                              AccessibilityHelperCompat.class.getSimpleName(),
                                                                              Build.VERSION.SDK_INT);

    private static volatile AccessibilityEventHandler eventHandler;

    private Object automatorBridge;

    private Class<?> uiTestAutomationBridgeClass;

    /**
     * Creates new accessibility helper compatible with API level 17.
     *
     * @throws IncompatibleAndroidSdkException
     *         if the device SDK API level is not compatible with level 17
     */
    public AccessibilityHelperCompat() {
        try {
            Method getBridgeMethod = UiDevice.class.getDeclaredMethod(UI_DEVICE_GET_AUTOMATOR_BRIDGE_METHOD_NAME);
            getBridgeMethod.setAccessible(true);
            automatorBridge = getBridgeMethod.invoke(UiDevice.getInstance());

            uiTestAutomationBridgeClass = Class.forName(UI_TEST_AUTOMATION_BRIDGE_CLASS_NAME);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (SecurityException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalAccessException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalArgumentException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (InvocationTargetException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (ClassNotFoundException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        }

    }

    /**
     * Gets the root {@link AccessibilityNodeInfo node} in the active window.
     *
     * @return the root {@link AccessibilityNodeInfo node} in the active window
     *
     * @throws IncompatibleAndroidSdkException
     *         if the device SDK API level is not compatible with level 17
     */
    @Override
    public AccessibilityNodeInfo getRootInActiveWindow() {
        try {
            Method getRootMethod = uiTestAutomationBridgeClass.getDeclaredMethod(UI_TEST_AUTOMATION_BRIDGE_GET_ROOT_NODE_METHOD_NAME);
            getRootMethod.setAccessible(true);
            return (AccessibilityNodeInfo) getRootMethod.invoke(automatorBridge);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (SecurityException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalAccessException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalArgumentException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (InvocationTargetException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        }
    }

    /**
     * Using {@link AccessibilityNodeInfo accessibility nodes} this method will walk the layout hierarchy and generates
     * an XML dump to the location specified by file.
     *
     * @param root
     *        - the root accessibility node
     *
     * @param file
     *        - the file to dump to
     * @throws IncompatibleAndroidSdkException
     *         if the device SDK API level is not compatible with level 17
     */
    @Override
    public void dumpWindowToFile(AccessibilityNodeInfo root, File file) {
        try {
            Method dumpMethod = AccessibilityNodeInfoDumper.class.getDeclaredMethod(NODE_INFO_DUMPER_DUMP_WINDOW_TO_FILE_METHOD_NAME,
                                                                                    AccessibilityNodeInfo.class,
                                                                                    File.class);
            dumpMethod.invoke(null, root, file);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (SecurityException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalAccessException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalArgumentException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (InvocationTargetException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        }
    }

    @Override
    public void initializeAccessibilityEventListener() {
        if (eventHandler != null) {
            return;
        }

        Class<?> eventListenerInterface = getInnerClass(ACCESSIBILITY_EVENT_LISTENER_INTERFACE_NAME);

        if (eventListenerInterface == null) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE);
        }

        InvocationHandler accessibilityHandler = new AccessibilityEventHandlerCompat();
        Object eventListenerInstance = getProxyForClass(eventListenerInterface, accessibilityHandler);

        try {
            Method addListenerMethod = automatorBridge.getClass().getDeclaredMethod(
                                                                                    UI_AUTOMATOR_BRIDGE_ADD_ACCESSIBILITY_EVENT_LISTENER_METHOD_NAME,
                                                                                    eventListenerInterface);
            addListenerMethod.setAccessible(true);
            addListenerMethod.invoke(automatorBridge, eventListenerInstance);

            eventHandler = (AccessibilityEventHandler) accessibilityHandler;
        } catch (NoSuchMethodException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (SecurityException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalAccessException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalArgumentException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (InvocationTargetException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        }
    }

    private Class<?> getInnerClass(String className) {
        try {
            Class<?>[] automatorBridgeClasses = automatorBridge.getClass().getDeclaredClasses();
            for (Class<?> clazz : automatorBridgeClasses) {
                if (clazz.getSimpleName().contains(className)) {
                    return clazz;
                }
            }

            return null;
        } catch (SecurityException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalArgumentException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        }
    }

    private Object getProxyForClass(Class<?> clazz, InvocationHandler handler) {
        try {
            return Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[] {clazz}, handler);
        } catch (SecurityException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalArgumentException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        }
    }

    @Override
    public String getLastToast() {
        return eventHandler.getLastToast();
    }
}
