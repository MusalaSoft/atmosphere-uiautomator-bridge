package com.musala.atmosphere.uiautomator.notificationbar;

import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class that is responsible for opening the notification bar on the device.
 * 
 * @author simeon.ivanov
 * 
 */

public class NotificationBarOpener implements Dispatchable {

    @Override
    public Object handle(Object[] args) {
        UiDevice device = UiDevice.getInstance();

        return device.openNotification();
    }
}
