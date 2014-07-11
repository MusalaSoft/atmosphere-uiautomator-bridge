package com.musala.atmosphere.uiautomator.util;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

/**
 * Gets the values of the widgets contained in the picker. Interacts with buttons in the picker, so that current date
 * and time could be automatically changed.
 * 
 * @author filareta.yordanova
 * 
 */

public class PickerHelper {
    private static final String NUMBER_PICKER_WIDGET = android.widget.NumberPicker.class.getName();

    private static final String TEXT_WIDGET = android.widget.EditText.class.getName();

    /**
     * Gets the text from an edit text field in a number picker widget selected by the requested instance.
     * 
     * @param instance
     *        - used for matching criterion when selecting number picker widget from the picker.
     * @return The text of the edit text filed in a number picker widget.
     * @throws UiObjectNotFoundException
     */
    public static String getNumberPickerFieldValue(int instance) throws UiObjectNotFoundException {
        UiObject numberPickerField = getNumberPickerField(instance);
        return numberPickerField.getText();
    }

    private static UiObject getNumberPickerField(int instance) throws UiObjectNotFoundException {
        UiObject numberPicker = getNumberPicker(instance);
        UiSelector textFieldSelector = new UiSelector();
        textFieldSelector = textFieldSelector.className(TEXT_WIDGET);
        return numberPicker.getChild(textFieldSelector);
    }

    private static UiObject getNumberPicker(int instance) {
        UiSelector numberPickerSelector = new UiSelector();
        numberPickerSelector = numberPickerSelector.className(NUMBER_PICKER_WIDGET);
        numberPickerSelector = numberPickerSelector.instance(instance);
        return new UiObject(numberPickerSelector);
    }

}
