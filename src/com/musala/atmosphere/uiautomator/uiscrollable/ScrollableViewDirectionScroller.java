package com.musala.atmosphere.uiautomator.uiscrollable;

import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ScrollDirection;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
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

        UiElementDescriptor descriptor = (UiElementDescriptor) args[1];

        UiSelector selector = UiSelectorParser.convertSelector(descriptor);

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
