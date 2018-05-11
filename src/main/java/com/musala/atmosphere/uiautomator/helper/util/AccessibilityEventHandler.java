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
