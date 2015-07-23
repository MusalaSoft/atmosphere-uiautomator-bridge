package com.musala.atmosphere.uiautomator.helper.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import android.view.accessibility.AccessibilityEvent;

/**
 * An {@link AccessibilityEventHandler} implementation compatible <b>only</b> with API level 17.
 *
 * @author yordan.petrov
 *
 */
public class AccessibilityEventHandlerCompat extends AccessibilityEventHandler implements InvocationHandler {
    private static final String ACCESSIBILITY_EVENT_LISTENER_ON_ACCESSIBILITY_EVENT_METHOD_NAME = "onAccessibilityEvent";

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.equals(ACCESSIBILITY_EVENT_LISTENER_ON_ACCESSIBILITY_EVENT_METHOD_NAME)) {
            handle((AccessibilityEvent) args[0]);
        }

        return null;
    }
}
