package com.musala.atmosphere.uiautomator.uidump;

import java.io.File;
import java.lang.reflect.Method;

import android.graphics.Point;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import com.android.uiautomator.core.AccessibilityNodeInfoDumper;
import com.android.uiautomator.core.UiAutomatorBridge;
import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class that is responsible for dumping the current screen of the device to a XML file.
 * 
 * @author yordan.petrov
 * 
 */
public class UiXmlDumper implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        String filename = (String) args[0];

        Method getBridgeMethod = UiDevice.class.getDeclaredMethod("getAutomatorBridge");
        getBridgeMethod.setAccessible(true);
        UiAutomatorBridge automatorBridge = (UiAutomatorBridge) getBridgeMethod.invoke(UiDevice.getInstance());

        AccessibilityNodeInfo root = automatorBridge.getRootInActiveWindow();
        if (root != null) {
            Display display = automatorBridge.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            AccessibilityNodeInfoDumper.dumpWindowToFile(root,
                                                         new File(filename),
                                                         display.getRotation(),
                                                         size.x,
                                                         size.y);
        }

        return UIAutomatorRequest.VOID_RESPONSE;
    }
}
