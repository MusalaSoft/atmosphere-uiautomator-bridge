package com.musala.atmosphere.uiautomator.util;

import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.gestureplayer.GesturePlayHandler;
import com.musala.atmosphere.uiautomator.notificationbar.NotificationBarOpener;
import com.musala.atmosphere.uiautomator.notificationbar.QuickSettingsOpener;
import com.musala.atmosphere.uiautomator.swipe.ElementSwiper;
import com.musala.atmosphere.uiautomator.textfieldclear.TextFieldEraser;
import com.musala.atmosphere.uiautomator.uiscrollable.ScrollableViewDirectionScroller;
import com.musala.atmosphere.uiautomator.wait.WaitForUIElementListener;
import com.musala.atmosphere.uiautomator.wait.WaitForWindowUpdateListener;
import com.musala.atmosphere.uiautomator.wait.WaitUntilUIElementGoneListener;

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
     * Indicates that the device should wait for an element to appear on the screen.
     */
    WAIT_FOR_EXISTS(UIAutomatorRequest.WAIT_FOR_EXISTS, WaitForUIElementListener.class),
    /**
     * Indicates that the device should wait for an element to disappear on the screen.
     */
    WAIT_UNTIL_GONE(UIAutomatorRequest.WAIT_UNTIL_GONE, WaitUntilUIElementGoneListener.class),
    /**
     * Indicates that the device should respond when a window update has occurred.
     */
    WAIT_FOR_WINDOW_UPDATE(UIAutomatorRequest.WAIT_FOR_WINDOW_UPDATE, WaitForWindowUpdateListener.class),
    /**
     * Indicates that an element should be swiped.
     */
    SWIPE_ELEMENT(UIAutomatorRequest.ELEMENT_SWIPE, ElementSwiper.class),

    /**
     * Indicates that and an element should be scrolled in some direction.
     */
    SCROLL_TO_DIRECTION(UIAutomatorRequest.SCROLL_TO_DIRECTION, ScrollableViewDirectionScroller.class),

    /**
     * Indicates that the notification bar should be opened on the device.
     */
    OPEN_NOTIFICATION_BAR(UIAutomatorRequest.OPEN_NOTIFICATION_BAR, NotificationBarOpener.class),

    /**
     * Indicates that the quick settings should be opened on the device.
     */
    OPEN_QUICK_SETTINGS(UIAutomatorRequest.OPEN_QUICK_SETTINGS, QuickSettingsOpener.class);

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
