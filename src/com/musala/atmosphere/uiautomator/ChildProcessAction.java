package com.musala.atmosphere.uiautomator;

import com.musala.atmosphere.uiautomator.socket.ConnectionInitializer;
import com.musala.atmosphere.uiautomator.swipe.ElementSwiper;
import com.musala.atmosphere.uiautomator.textfieldclear.TextFieldEraser;

/**
 * Enumerates the actions that a UIAutomator process should do.
 * 
 * @author georgi.gaydarov
 * 
 */
public enum ChildProcessAction {
    /**
     * Indicates that socket server accepting requests should be started by this process.
     */
    REQUEST_SERVER(0, ConnectionInitializer.class),
    /**
     * Indicates that a field should be cleared by this process.
     */
    CLEAR_FIELD(1, TextFieldEraser.class),

    /**
     * Indicates that an element should be swiped.
     */
    SWIPE_ELEMENT(2, ElementSwiper.class);

    private static final ChildProcessAction DEFAULT_ACTION = REQUEST_SERVER;

    private int actionId;

    private Class<? extends Dispatchable> handler;

    private ChildProcessAction(int id, Class<? extends Dispatchable> handler) {
        actionId = id;
        this.handler = handler;
    }

    /**
     * @return the action identifier.
     */
    public int getId() {
        return actionId;
    }

    /**
     * 
     * @return the dispatchable class associated with this action.
     */
    public Class<? extends Dispatchable> getHandler() {
        return handler;
    }

    /**
     * Returns a {@link ChildProcessAction} object by a given identifier.
     * 
     * @param id
     *        - the action identifier.
     * @return a {@link ChildProcessAction} object for the given identifier.
     */
    public static ChildProcessAction getById(int id) {
        ChildProcessAction actionById = DEFAULT_ACTION;
        for (ChildProcessAction currentAction : ChildProcessAction.values()) {
            if (currentAction.getId() == id) {
                actionById = currentAction;
                break;
            }
        }

        return actionById;
    }
}
