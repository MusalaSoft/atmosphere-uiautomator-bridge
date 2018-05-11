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

package com.musala.atmosphere.uiautomator.uiscrollable;

import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ScrollDirection;
import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that handles the requested scrolling action in one of the different directions.
 * 
 * @author filareta.yordanova
 * 
 */
public class ScrollableViewDirectionScroller implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        ScrollDirection scrollDirection = (ScrollDirection) args[0];
        UiElementPropertiesContainer propertiesContainer = (UiElementPropertiesContainer) args[1];
        UiSelector selector = UiSelectorParser.convertSelector(propertiesContainer);

        Integer maxSwipes = (Integer) args[2];
        Integer maxSteps = (Integer) args[3];
        Boolean isVertical = (Boolean) args[4];

        UiScrollable scrollableView = new UiScrollable(selector);

        if (!isVertical) {
            scrollableView.setAsHorizontalList();
        }

        Boolean response = false;

        if (maxSteps != 0) {
            switch (scrollDirection) {
                case SCROLL_TO_BEGINNING:
                    response = scrollableView.scrollToBeginning(maxSwipes, maxSteps);
                    break;
                case SCROLL_TO_END:
                    response = scrollableView.scrollToEnd(maxSwipes, maxSteps);
                    break;
                case SCROLL_BACKWARD:
                    response = scrollableView.scrollBackward(maxSteps);
                    break;
                case SCROLL_FORWARD:
                    response = scrollableView.scrollForward(maxSteps);
                    break;
                default:
                    break;
            }
        } else {
            switch (scrollDirection) {
                case SCROLL_TO_BEGINNING:
                    response = scrollableView.scrollToBeginning(maxSwipes);
                    break;
                case SCROLL_TO_END:
                    response = scrollableView.scrollToEnd(maxSwipes);
                    break;
                case SCROLL_BACKWARD:
                    response = scrollableView.scrollBackward();
                    break;
                case SCROLL_FORWARD:
                    response = scrollableView.scrollForward();
                    break;
                default:
                    break;
            }
        }

        return response;
    }
}
