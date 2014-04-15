package com.musala.atmosphere.uiautomator;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import android.os.Bundle;
import android.util.Log;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.musala.atmosphere.commons.ad.Request;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorConstants;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.util.UIAutomatorProcessAction;

/**
 * Class responsible for running the appropriate handler. Main entry point.
 * 
 * @author georgi.gaydarov
 * 
 */
public class ActionDispatcher extends UiAutomatorTestCase {
    public void testRun() throws ClassNotFoundException, IOException {

        Log.i("UIAutomator Dispatcher", "Starting...");
        Bundle params = getParams();

        if (!params.containsKey(UIAutomatorConstants.PARAM_REQUEST)) {
            // TODO some error here. To be implemented when response handling is implemented in the Agent component
            // communicator class
        }

        String requestFile = params.getString(UIAutomatorConstants.PARAM_REQUEST);
        InputStream buffer = new BufferedInputStream(new FileInputStream(requestFile));
        ObjectInput input = new ObjectInputStream(buffer);
        Request<UIAutomatorRequest> request = (Request<UIAutomatorRequest>) input.readObject();
        input.close();

        UIAutomatorRequest requestType = request.getType();
        Object[] requestArguments = request.getArguments();

        UIAutomatorProcessAction action = UIAutomatorProcessAction.getByRequest(requestType);
        Class<? extends Dispatchable> handlerClass = action.getHandler();
        Log.i("UIAutomator Dispatcher", "Passing job to " + handlerClass.toString());
        try {
            Dispatchable handler = handlerClass.newInstance();
            handler.handle(requestArguments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
