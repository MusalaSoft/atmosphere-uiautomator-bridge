package com.musala.atmosphere.uiautomator.swipe;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.commons.beans.SwipeDirection;
import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that is responsible for swiping an UI element by it's properties container.
 * 
 * @author yordan.petrov
 * 
 */
public class ElementSwiper implements Dispatchable {
    private static final int SWIPE_STEPS_COUNT = 10;

    @Override
    public Object handle(Object[] args) throws UiObjectNotFoundException {
        UiElementPropertiesContainer propertiesContainer = (UiElementPropertiesContainer) args[0];
        SwipeDirection direction = (SwipeDirection) args[1];

        UiSelector selector = UiSelectorParser.convertSelector(propertiesContainer);

        UiObject element = new UiObject(selector);
        switch (direction) {
            case DOWN:
                element.swipeDown(SWIPE_STEPS_COUNT);
                break;
            case UP:
                element.swipeUp(SWIPE_STEPS_COUNT);
                break;
            case LEFT:
                element.swipeLeft(SWIPE_STEPS_COUNT);
                break;
            case RIGHT:
                element.swipeRight(SWIPE_STEPS_COUNT);
                break;
        }

        return UIAutomatorRequest.VOID_RESPONSE;
    }
}
