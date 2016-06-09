package com.musala.atmosphere.uiautomator.toast;

import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;

/**
 * Class that is responsible for obtaining the text of the last detected toast message.
 *
 * @author yordan.petrov
 *
 */
public class ToastMessageObtainer implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        AccessibilityHelper helper = AccessibilityFactory.getAccessibilityHelper();
        return helper.getLastToast();
    }
}