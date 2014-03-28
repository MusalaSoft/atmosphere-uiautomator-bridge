package com.musala.atmosphere.uiautomator.swipe;

import android.os.Bundle;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.beans.SwipeDirection;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.ChildProcessAction;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.ChildProcessStarter;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

public class ElementSwiper implements Dispatchable {
    private static final int SWIPE_STEPS_COUNT = 10;

    public static final String PARAM_DESCRIPTOR = "uiDescr";

    public static final String PARAM_DIRECTION = "direction";

    public static boolean swipe(UiElementDescriptor descriptor, SwipeDirection direction) {
        String representation = UiSelectorParser.getStringRepresentation(descriptor);
        if (representation == null) {
            return false;
        }

        ChildProcessStarter starter = new ChildProcessStarter(ChildProcessAction.SWIPE_ELEMENT);
        Integer directionId = direction.getDirectionId();
        starter.addParameter(PARAM_DIRECTION, directionId.toString());
        starter.addParameter(PARAM_DESCRIPTOR, representation);
        starter.start();

        return true;
    }

    @Override
    public void handle(Bundle params) {
        // FIXME some code should be extracted somewhere when the API19 compatibility activation mechanism is
        // implemented.
        if (!params.containsKey(PARAM_DESCRIPTOR)) {
            System.out.println("UI descriptor parameter is missing!");
            return;
        }

        if (!params.containsKey(PARAM_DIRECTION)) {
            System.out.println("Swipe direction parameter is missing!");
            return;
        }

        String descriptor = params.getString(PARAM_DESCRIPTOR);
        int length = descriptor.length();
        // base64 coded data length is always n*4
        if (length < 4 || length % 4 != 0) {
            System.out.println("UI descriptor parameter does not have valid length!");
            return;
        }

        UiSelector selector = UiSelectorParser.getSelectorFromRepresentation(descriptor);
        String directionParameter = params.getString(PARAM_DIRECTION);
        SwipeDirection direction = SwipeDirection.getDirectionById(Integer.parseInt(directionParameter));

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
