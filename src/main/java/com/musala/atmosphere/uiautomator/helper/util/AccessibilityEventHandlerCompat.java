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
