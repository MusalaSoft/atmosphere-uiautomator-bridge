package com.musala.atmosphere.uiautomator.notificationbar;

import java.io.IOException;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.commons.ad.FileTransferConstants;
import com.musala.atmosphere.commons.ad.util.FileObjectTransferManager;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class that is responsible for opening the notification bar on the device.
 * 
 * @author simeon.ivanov
 * 
 */

public class NotificationBarOpener implements Dispatchable {
    private static final String TAG = NotificationBarOpener.class.toString();

    private FileObjectTransferManager fileObjectTransferManager = new FileObjectTransferManager();

    @Override
    public void handle(Object[] args) {
        UiDevice device = UiDevice.getInstance();

        Boolean response = device.openNotification();
        try {
            fileObjectTransferManager.writeObjectToFile(response, FileTransferConstants.ONDEVICE_RESPONSE_PATH);
        } catch (IOException e) {
            String errorMessage = "Failed to push the response in a file.";
            Log.e(TAG, errorMessage, e);
        }
    }
}
