package com.musala.atmosphere.uiautomator.swipe;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.beans.SwipeDirection;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

public class ElementSwiper implements Dispatchable {
    private static final int SWIPE_STEPS_COUNT = 10;

    @Override
    public void handle(Object[] args) {
        UiElementDescriptor descriptor = (UiElementDescriptor) args[0];
        SwipeDirection direction = (SwipeDirection) args[1];

        UiSelector selector = UiSelectorParser.convertSelector(descriptor);

        UiObject element = new UiObject(selector);
        try {
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
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }
}
