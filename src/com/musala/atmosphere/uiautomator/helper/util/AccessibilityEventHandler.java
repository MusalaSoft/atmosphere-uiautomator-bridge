package com.musala.atmosphere.uiautomator.helper.util;

import android.app.Notification;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityEvent;

/**
 * An abstract class handling accessibility events.
 *
 * @author yordan.petrov
 *
 */
public abstract class AccessibilityEventHandler {
    protected volatile String lastToastMessage = null;

    /**
     * Gets the text of the last detected toast message.
     *
     * @return the text of the last detected toast message
     */
    public String getLastToast() {
        return lastToastMessage;
    }

    /**
     * Handles an {@link AccessibilityEvent} extracting its information.
     *
     * @param receivedEvent
     *        - the event to be handled
     */
    public void handle(AccessibilityEvent receivedEvent) {
        if (receivedEvent.getEventType() != AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            return; // event is not a notification
        }

        Parcelable parcelable = receivedEvent.getParcelableData();
        if (parcelable instanceof Notification) {
            // Statusbar Notification
        } else {
            // something else, e.g. a Toast message
            lastToastMessage = receivedEvent.getText().get(0).toString();
        }
    }
}
