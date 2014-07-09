package com.musala.atmosphere.uiautomator.notificationbar;

import java.io.IOException;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.commons.ad.FileTransferConstants;
import com.musala.atmosphere.commons.ad.util.FileObjectTransferManager;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class that is responsible for opening the quick settings on the device.
 * 
 * @author simeon.ivanov
 * 
 */

public class QuickSettingsOpener implements Dispatchable {
    private static final String TAG = QuickSettingsOpener.class.toString();

    private FileObjectTransferManager fileObjectTransferManager = new FileObjectTransferManager();

    @Override
    public void handle(Object[] args) {
        UiDevice device = UiDevice.getInstance();

        Boolean response = device.openQuickSettings();
        try {
            fileObjectTransferManager.writeObjectToFile(response, FileTransferConstants.ONDEVICE_RESPONSE_PATH);
        } catch (IOException e) {
            String errorMessage = "Failed to push the response in a file.";
            Log.e(TAG, errorMessage, e);
        }
    }
}
