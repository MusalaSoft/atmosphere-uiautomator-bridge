package com.musala.atmosphere.uiautomator.textfieldclear;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;

/**
 * Class that is responsible for clearing an editable UI element by it's descriptor.
 * 
 * @author georgi.gaydarov
 * 
 */
public class TextFieldEraser {

    public static boolean clearField(UiElementDescriptor descriptor) {
        // workAround();
        try {
            UiSelector selector = convertSelector(descriptor);
            UiObject field = new UiObject(selector);
            field.clickTopLeft();
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return false;
    }

    public static void workAround() {
        UiDevice deviceInstance = UiDevice.getInstance();
        Object testingBridge = null;
        try {
            Method automatorBridgeGetter = UiDevice.class.getMethod("getAutomatorBridge");
            automatorBridgeGetter.setAccessible(true);
            // Field automatorBridge = UiDevice.class.getField("mUiAutomationBridge");
            // automatorBridge.setAccessible(true);
            Object bridge = automatorBridgeGetter.invoke(deviceInstance); // device.getAutomatorBridge()

            Class<?> bridgeClass = Class.forName("com.android.uiautomator.core.UiAutomatorBridge");
            Method queryControllerGetter = bridgeClass.getMethod("getQueryController");
            queryControllerGetter.setAccessible(true);
            Object queryController = queryControllerGetter.invoke(bridge); // bridge.getQueryController()

            Class<?> queryControllerClass = Class.forName("com.android.uiautomator.core.QueryController");
            Field testingBridgeField = queryControllerClass.getField("mUiAutomatorBridge");
            testingBridgeField.setAccessible(true);

            testingBridge = testingBridgeField.get(queryController);
        } catch (Throwable t) {
            System.out.println("workaround reflection resulted in exception.");
            t.printStackTrace();
            return;
        }

        try {
            Class<?> testingBridgeClass = Class.forName("android.accessibilityservice.UiTestAutomationBridge");
            Method connectBridgeMethod = testingBridgeClass.getMethod("connect");
            connectBridgeMethod.setAccessible(true);
            connectBridgeMethod.invoke(testingBridge);
        } catch (Throwable e) {
            System.out.println("Could not found class named 'android.accessibilityservice.UiTestAutomationBridge', you are probably using android 4.3 or later.");
            e.printStackTrace();
            return;
        }

        System.out.println("workaround succeeded.");
    }

    private static UiSelector convertSelector(UiElementDescriptor descriptor) {
        UiSelector selector = new UiSelector();
        Boolean checked = descriptor.isChecked();
        String className = descriptor.getClassName();
        Boolean clickable = descriptor.isClickable();
        String description = descriptor.getContentDescription();
        Boolean enabled = descriptor.isEnabled();
        Boolean focusable = descriptor.isFocusable();
        Boolean focused = descriptor.isFocused();
        Integer index = descriptor.getIndex();
        Boolean longClickable = descriptor.isLongClickable();
        String packageName = descriptor.getPackageName();
        Boolean scrollable = descriptor.isScrollable();
        Boolean selected = descriptor.isSelected();
        String text = descriptor.getText();

        if (checked != null) {
            selector.checked(checked);
        }
        if (className != null) {
            selector.className(className);
        }
        if (clickable != null) {
            selector.clickable(clickable);
        }
        if (description != null) {
            selector.description(description);
        }
        if (enabled != null) {
            selector.enabled(enabled);
        }
        if (focusable != null) {
            selector.focused(focusable);
        }
        if (focused != null) {
            selector.focused(focused);
        }
        if (index != null) {
            selector.index(index);
        }
        if (longClickable != null) {
            selector.longClickable(longClickable);
        }
        if (packageName != null) {
            selector.packageName(packageName);
        }
        if (scrollable != null) {
            selector.scrollable(scrollable);
        }
        if (selected != null) {
            selector.selected(selected);
        }
        if (text != null) {
            selector.text(text);
        }
        return selector;
    }
}
