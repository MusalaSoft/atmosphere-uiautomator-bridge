package com.musala.atmosphere.uiautomator.textfieldclear;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that is responsible for clearing an editable UI element by it's descriptor.
 * 
 * @author georgi.gaydarov
 * 
 */
public class TextFieldEraser implements Dispatchable {
    private static final int ACTION_TIMEOUT_SECONDS = 10;

    private static final int ACTION_TIMEOUT_STEP = 1000;

    @Override
    public Object handle(Object[] args) throws UiObjectNotFoundException {
        UiElementDescriptor descriptor = (UiElementDescriptor) args[0];

        UiSelector selector = UiSelectorParser.convertSelector(descriptor);

        UiObject field = new UiObject(selector);

        field.clearTextField();

        for (int i = 0; i < ACTION_TIMEOUT_SECONDS / (ACTION_TIMEOUT_STEP / 1000); i++) {
            String text = field.getText();
            if (!text.isEmpty()) {
                try {
                    Thread.sleep(ACTION_TIMEOUT_STEP);
                } catch (InterruptedException e) {
                    // Nothing to do here
                }
            } else {
                break;
            }
        }

        return UIAutomatorRequest.VOID_RESPONSE;

    }
}
