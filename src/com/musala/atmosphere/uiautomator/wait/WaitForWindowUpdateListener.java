package com.musala.atmosphere.uiautomator.wait;

import java.io.IOException;

import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.musala.atmosphere.commons.ad.FileTransferConstants;
import com.musala.atmosphere.commons.ad.util.FileObjectTransferManager;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * A class responsible for handling a wait for window update event operation.
 * 
 * @author delyan.dimitrov
 * 
 */
public class WaitForWindowUpdateListener implements Dispatchable {
    private static final String TAG = WaitForWindowUpdateListener.class.getName();

    FileObjectTransferManager fileObjectTransferManager = new FileObjectTransferManager();

    @Override
    public void handle(Object[] args) throws Exception {
        String packageName = (String) args[0];
        int timeout = (Integer) args[1];

        UiDevice device = UiDevice.getInstance();
        Boolean response = device.waitForWindowUpdate(packageName, timeout);

        try {
            fileObjectTransferManager.writeObjectToFile(response, FileTransferConstants.ONDEVICE_RESPONSE_PATH);
        } catch (IOException e) {
            final String ERROR_MESSAGE = "Writing operation response to file failed.";
            Log.e(TAG, ERROR_MESSAGE, e);
        }
    }
}
