package com.musala.atmosphere.uiautomator.helper.util;

import android.app.UiAutomation.OnAccessibilityEventListener;
import android.view.accessibility.AccessibilityEvent;

/**
 * An {@link AccessibilityEventHandler} implementation compatible with API level 18 and above.
 *
 * @author yordan.petrov
 *
 */
public class AccessibilityEvenntHandlerImpl extends AccessibilityEventHandler implements OnAccessibilityEventListener {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent receivedEvent) {
        handle(receivedEvent);
    }
}