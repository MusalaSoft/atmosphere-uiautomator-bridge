package com.musala.atmosphere.uiautomator.notificationbar;

import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class that is responsible for opening the quick settings menu on the device.
 * 
 * @author simeon.ivanov
 * 
 */

public class QuickSettingsOpener implements Dispatchable {
    @Override
    public Object handle(Object[] args) {
        UiDevice device = UiDevice.getInstance();

        return device.openQuickSettings();
    }
}
