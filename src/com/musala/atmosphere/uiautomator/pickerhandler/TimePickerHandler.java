package com.musala.atmosphere.uiautomator.pickerhandler;

import java.io.IOException;

import android.util.Log;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.musala.atmosphere.commons.PickerAction;
import com.musala.atmosphere.commons.ad.FileTransferConstants;
import com.musala.atmosphere.commons.ad.util.FileObjectTransferManager;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.PickerHelper;

/**
 * Handles requests for the time picker widget.
 * 
 * @author filareta.yordanova
 * 
 */

public class TimePickerHandler implements Dispatchable {
    private static final int HOUR_INSTANCE_NUMBER = 0;

    private static final int MINUTE_INSTANCE_NUMBER = 1;

    private static final int MERIDIEM_INSANCE_NUMBER = 2;

    private static final String GENERAL_TIME_FORMATTER = "%s:%s";

    private static final String TIME_FORMATTER_WITH_MERIDIEM = "%s:%s %s";

    private static final String TAG = TimePickerHandler.class.getName();

    private final FileObjectTransferManager responseWriter = new FileObjectTransferManager();

    @Override
    public void handle(Object[] args) throws Exception {
        PickerAction action = (PickerAction) args[0];
        String pickerResult = null;

        switch (action) {
            case GET_TIME:
                pickerResult = getTimePicker();
                break;
        }

        try {
            responseWriter.writeObjectToFile(pickerResult, FileTransferConstants.ONDEVICE_RESPONSE_PATH);
        } catch (IOException e) {
            Log.e(TAG, "Failed to push response to file.", e);
        }
    }

    private String getTimePicker() throws UiObjectNotFoundException {
        String hour = PickerHelper.getNumberPickerFieldValue(HOUR_INSTANCE_NUMBER);
        String minute = PickerHelper.getNumberPickerFieldValue(MINUTE_INSTANCE_NUMBER);
        String time = String.format(GENERAL_TIME_FORMATTER, hour, minute);

        try {
            String meridiem = PickerHelper.getNumberPickerFieldValue(MERIDIEM_INSANCE_NUMBER);
            time = String.format(TIME_FORMATTER_WITH_MERIDIEM, hour, minute, meridiem);
        } catch (UiObjectNotFoundException e) {
            // Time picker is in 24 hours format view and there is no number picker widget for meridiem.
        }

        return time;
    }
}
