package com.musala.atmosphere.uiautomator.wait;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that is responsible for waiting for an UI element to disappear on the screen.
 * 
 * @author simeon.ivanov
 * 
 */

public class WaitUntilUIElementGoneListener implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws UiObjectNotFoundException {
        UiElementDescriptor descriptor = (UiElementDescriptor) args[0];

        Integer timeout = (Integer) args[1];

        UiSelector selector = UiSelectorParser.convertSelector(descriptor);
        UiObject object = new UiObject(selector);
        return object.waitUntilGone(timeout);
    }
}
