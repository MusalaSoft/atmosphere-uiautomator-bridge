package com.musala.atmosphere.uiautomator.helper;

import java.io.File;
import java.lang.reflect.Field;

import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import com.android.uiautomator.core.AccessibilityNodeInfoDumper;
import com.android.uiautomator.core.UiAutomatorBridge;
import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.uiautomator.exception.IncompatibleAndroidSdkException;

/**
 * {@link AccessibilityHelper} implementation compatible with API level 18 and above.
 * 
 * @author vassil.angelov
 *
 */
public class AccessibilityHelperImpl implements AccessibilityHelper {

    private static final String UI_DEVICE_AUTOMATION_BRIDGE_FIELD_NAME = "mUiAutomationBridge";

    private static final String INCOMPATIBILITY_ERROR_FORMAT = "%s is compatible with Android SDK API level 18 and above and the device has API level %d";

    private static final String INCOMPATIBILITY_ERROR_MESSAGE = String.format(INCOMPATIBILITY_ERROR_FORMAT,
                                                                              AccessibilityHelperImpl.class.getSimpleName(),
                                                                              Build.VERSION.SDK_INT);

    private UiAutomatorBridge uiAutomatorBridge;

    /**
     * Creates new accessibility helper compatible with API level 18 and above.
     * 
     * @throws IncompatibleAndroidSdkException
     *         if the device SDK API level is below 18
     */
    public AccessibilityHelperImpl() {

        try {
            Field automationBridgeField = UiDevice.class.getDeclaredField(UI_DEVICE_AUTOMATION_BRIDGE_FIELD_NAME);
            automationBridgeField.setAccessible(true);
            uiAutomatorBridge = (UiAutomatorBridge) automationBridgeField.get(UiDevice.getInstance());
        } catch (SecurityException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalAccessException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (IllegalArgumentException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        } catch (NoSuchFieldException e) {
            throw new IncompatibleAndroidSdkException(INCOMPATIBILITY_ERROR_MESSAGE, e);
        }
    }

    @Override
    public AccessibilityNodeInfo getRootInActiveWindow() {
        return uiAutomatorBridge.getRootInActiveWindow();
    }

    @Override
    public void dumpWindowToFile(AccessibilityNodeInfo root, File file) {
        Display display = uiAutomatorBridge.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        AccessibilityNodeInfoDumper.dumpWindowToFile(root, file, display.getRotation(), size.x, size.y);
    }
}
