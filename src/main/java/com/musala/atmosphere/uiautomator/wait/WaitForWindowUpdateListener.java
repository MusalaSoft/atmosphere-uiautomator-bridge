package com.musala.atmosphere.uiautomator.wait;

import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * A class responsible for handling a wait for window update event operation.
 * 
 * @author delyan.dimitrov
 * 
 */
public class WaitForWindowUpdateListener implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        String packageName = (String) args[0];
        int timeout = (Integer) args[1];

        UiDevice device = UiDevice.getInstance();
        return device.waitForWindowUpdate(packageName, timeout);
    }
}
