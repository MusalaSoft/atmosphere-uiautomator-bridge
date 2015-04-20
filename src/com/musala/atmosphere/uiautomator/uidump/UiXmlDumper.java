package com.musala.atmosphere.uiautomator.uidump;

import java.io.File;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperFactory;

/**
 * Class that is responsible for dumping the current screen of the device to a XML file.
 * 
 * @author yordan.petrov
 * 
 */
public class UiXmlDumper implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        AccessibilityHelper helper = AccessibilityHelperFactory.getHelper();
        AccessibilityNodeInfo root = helper.getRootInActiveWindow();

        if (root != null) {
            String filename = (String) args[0];
            helper.dumpWindowToFile(root, new File(filename));
        }

        return UIAutomatorRequest.VOID_RESPONSE;
    }
}
