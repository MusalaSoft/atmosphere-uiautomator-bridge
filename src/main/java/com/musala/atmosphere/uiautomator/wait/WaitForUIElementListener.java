package com.musala.atmosphere.uiautomator.wait;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that is responsible for waiting for an UI element to show on the screen.
 * 
 * @author yavor.stankov
 * 
 */

public class WaitForUIElementListener implements Dispatchable {
    @Override
    public Object handle(Object[] args) throws UiObjectNotFoundException {
        UiElementPropertiesContainer propertiesContainer = (UiElementPropertiesContainer) args[0];

        Integer timeout = (Integer) args[1];

        UiSelector selector = UiSelectorParser.convertSelector(propertiesContainer);
        UiObject object = new UiObject(selector);
        return object.waitForExists(timeout);
    }
}
