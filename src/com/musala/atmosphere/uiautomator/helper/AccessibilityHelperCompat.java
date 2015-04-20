package com.musala.atmosphere.uiautomator.helper;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

import com.android.uiautomator.core.AccessibilityNodeInfoDumper;
import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.uiautomator.exception.IncompatibleAndroidSdkException;

/**
 * {@link AccessibilityHelper} implementation compatible <b>only</b> with API level 17.
 * 
 * @author vassil.angelov
 *
 */
public class AccessibilityHelperCompat implements AccessibilityHelper {

    private static final String UI_DEVICE_GET_AUTOMATOR_BRIDGE_METHOD_NAME = "getAutomatorBridge";

    private static final String UI_TEST_AUTOMATION_BRIDGE_CLASS_NAME = "android.accessibilityservice.UiTestAutomationBridge";

    private static final String UI_TEST_AUTOMATION_BRIDGE_GET_ROOT_NODE_METHOD_NAME = "getRootAccessibilityNodeInfoInActiveWindow";

    private static final String NODE_INFO_DUMPER_DUMP_WINDOW_TO_FILE_METHOD_NAME = "dumpWindowToFile";

    private static final String INCOMPATIBILITY_ERROR_FORMAT = "%s is compatible only with Android SDK API level 17 and the device has API level %d";

    private static final String INCOMPATIBILITY_ERROR_MESSAGE = String.format(INCOMPATIBILITY_ERROR_FORMAT,
                                                                              AccessibilityHelperCompat.class.getSimpleName(),
                                                                              Build.VERSION.SDK_INT);

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
}
