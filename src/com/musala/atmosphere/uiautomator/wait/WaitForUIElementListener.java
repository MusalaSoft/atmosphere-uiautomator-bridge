package com.musala.atmosphere.uiautomator.wait;

import java.io.IOException;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ad.FileTransferConstants;
import com.musala.atmosphere.commons.ad.util.FileObjectTransferManager;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that is responsible for waiting for an UI element to show on the screen.
 * 
 * @author yavor.stankov
 * 
 */

public class WaitForUIElementListener implements Dispatchable {
    private static final String TAG = WaitForUIElementListener.class.toString();

    private FileObjectTransferManager fileObjectTransferManager = new FileObjectTransferManager();

    @Override
    public void handle(Object[] args) throws UiObjectNotFoundException {
        UiElementDescriptor descriptor = (UiElementDescriptor) args[0];

        Integer timeout = (Integer) args[1];

        UiSelector selector = UiSelectorParser.convertSelector(descriptor);
        UiObject object = new UiObject(selector);
        Boolean response = object.waitForExists(timeout);
        try {
            fileObjectTransferManager.writeObjectToFile(response, FileTransferConstants.ONDEVICE_RESPONSE_PATH);
        } catch (IOException e) {
            String errorMessage = "Failed to push the response in a file";
            Log.e(TAG, errorMessage, e);
        }
    }
}
