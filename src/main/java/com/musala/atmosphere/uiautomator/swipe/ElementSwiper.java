// This file is part of the ATMOSPHERE mobile testing framework.
// Copyright (C) 2016 MusalaSoft
//
// ATMOSPHERE is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// ATMOSPHERE is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with ATMOSPHERE.  If not, see <http://www.gnu.org/licenses/>.

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
