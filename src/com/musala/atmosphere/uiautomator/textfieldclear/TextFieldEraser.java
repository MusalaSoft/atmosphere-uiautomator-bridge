package com.musala.atmosphere.uiautomator.textfieldclear;

import android.os.Bundle;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.ChildProcessAction;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.ChildProcessStarter;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that is responsible for clearing an editable UI element by it's descriptor.
 * 
 * @author georgi.gaydarov
 * 
 */
public class TextFieldEraser implements Dispatchable {

    public static final String PARAM_DESCRIPTOR = "uiDescr";

    private static final int ACTION_TIMEOUT_SECONDS = 10;

    private static final int ACTION_TIMEOUT_STEP = 1000;

    public static boolean clearField(UiElementDescriptor descriptor) {
        String representation = UiSelectorParser.getStringRepresentation(descriptor);
        if (representation == null) {
            return false;
        }

        ChildProcessStarter starter = new ChildProcessStarter(ChildProcessAction.CLEAR_FIELD);
        starter.addParameter(PARAM_DESCRIPTOR, representation);
        starter.start();

        return true;
    }

    @Override
    public void handle(Bundle params) {
        if (!params.containsKey(PARAM_DESCRIPTOR)) {
            System.out.println("UI descriptor parameter is missing!");
            return;
        }

        String descriptor = params.getString(PARAM_DESCRIPTOR);
        int length = descriptor.length();
        // base64 coded data length is always n*4
        if (length < 4 || length % 4 != 0) {
            System.out.println("UI descriptor parameter does not have valid length!");
            return;
        }

        UiSelector selector = UiSelectorParser.getSelectorFromRepresentation(descriptor);

        UiObject field = new UiObject(selector);
        try {
            field.clearTextField();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            for (int i = 0; i < ACTION_TIMEOUT_SECONDS / (ACTION_TIMEOUT_STEP / 1000); i++) {
                String text = field.getText();
                if (!text.isEmpty()) {
                    Thread.sleep(ACTION_TIMEOUT_STEP);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
