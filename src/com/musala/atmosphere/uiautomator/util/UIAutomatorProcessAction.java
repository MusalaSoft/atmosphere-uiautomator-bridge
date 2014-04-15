package com.musala.atmosphere.uiautomator.util;

import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.gestureplayer.GesturePlayHandler;
import com.musala.atmosphere.uiautomator.swipe.ElementSwiper;
import com.musala.atmosphere.uiautomator.textfieldclear.TextFieldEraser;

/**
 * Enumerates the actions that a UIAutomator process should do.
 * 
 * @author georgi.gaydarov
 * 
 */
public enum UIAutomatorProcessAction {
    // TODO implement uiautomator component info handler
    PRINT_INFO(null, null),
    /**
     * Indicates that a gesture execution should be done.
     */
    PLAY_GESTURE(UIAutomatorRequest.PLAY_GESTURE, GesturePlayHandler.class),
    /**
     * Indicates that a field should be cleared by this process.
     */
    CLEAR_FIELD(UIAutomatorRequest.CLEAR_FIELD, TextFieldEraser.class),

    /**
     * Indicates that an element should be swiped.
     */
    SWIPE_ELEMENT(UIAutomatorRequest.ELEMENT_SWIPE, ElementSwiper.class);

    private static final UIAutomatorProcessAction DEFAULT_ACTION = PRINT_INFO;

    private UIAutomatorRequest request;

    private Class<? extends Dispatchable> handler;

    private UIAutomatorProcessAction(UIAutomatorRequest request, Class<? extends Dispatchable> handler) {
        this.request = request;
        this.handler = handler;
    }

    /**
     * @return the {@link UIAutomatorRequest} instance behind this process action.
     */
    public UIAutomatorRequest getRequest() {
        return request;
    }

    /**
     * 
     * @return the dispatchable class associated with this action.
     */
    public Class<? extends Dispatchable> getHandler() {
        return handler;
    }

    /**
     * Returns a {@link UIAutomatorProcessAction} object by a given {@link UIAutomatorRequest} instance.
     * 
     * @param request
     *        - the request instance.
     * @return a {@link UIAutomatorProcessAction} object for the given request.
     */
    public static UIAutomatorProcessAction getByRequest(UIAutomatorRequest request) {
        UIAutomatorProcessAction actionById = DEFAULT_ACTION;
        for (UIAutomatorProcessAction currentAction : UIAutomatorProcessAction.values()) {
            if (currentAction.getRequest() == request) {
                actionById = currentAction;
                break;
            }
        }

        return actionById;
    }
}
