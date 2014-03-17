package com.musala.atmosphere.uiautomator;

import android.os.Bundle;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * Class responsible for running the appropriate handler. Main entry point.
 * 
 * @author georgi.gaydarov
 * 
 */
public class ActionDispatcher extends UiAutomatorTestCase {
    public static final String PARAM_ACTION = "action";

    public void testRun() {
        Bundle params = getParams();

        ChildProcessAction action = ChildProcessAction.REQUEST_SERVER;
        if (params.containsKey(PARAM_ACTION)) {
            int actionId = Integer.parseInt(params.getString(PARAM_ACTION));
            action = ChildProcessAction.getById(actionId);
        }

        Class<? extends Dispatchable> handlerClass = action.getHandler();
        try {
            Dispatchable handler = handlerClass.newInstance();
            handler.handle(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
