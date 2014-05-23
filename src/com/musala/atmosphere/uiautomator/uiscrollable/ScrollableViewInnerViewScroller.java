package com.musala.atmosphere.uiautomator.uiscrollable;

import java.io.File;
import java.io.IOException;

import android.util.Log;

import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.musala.atmosphere.commons.ad.FileObjectTransferManagerConstants;
import com.musala.atmosphere.commons.ad.util.FileObjectTransferManager;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.util.UiSelectorParser;

/**
 * Class that handles the requested scrolling action into some inner view from a scrollable container or scrolling into
 * text contained in a view.
 * 
 * @author filareta.yordanova
 * 
 */
public class ScrollableViewInnerViewScroller implements Dispatchable {
    private static final String PATH_TO_FILE = FileObjectTransferManagerConstants.DEVICE_TMP_PATH + File.separator
            + FileObjectTransferManagerConstants.RESPONSE_FILE_NAME;

    private FileObjectTransferManager fileObjectTransferManager = new FileObjectTransferManager();

    @Override
    public void handle(Object[] args) throws Exception {

        UiElementDescriptor descriptor = (UiElementDescriptor) args[0];

        UiElementDescriptor viewDescriptor = (UiElementDescriptor) args[1];

        Boolean isVertical = (Boolean) args[2];

        UiSelector selector = UiSelectorParser.convertSelector(descriptor);
        UiSelector viewSelector = UiSelectorParser.convertSelector(viewDescriptor);

        UiScrollable scrollableView = new UiScrollable(selector);

        if (!isVertical) {
            scrollableView.setAsHorizontalList();
        }

        // TODO think of a better solution
        Boolean response = scrollableView.scrollIntoView(viewSelector);

        try {
            fileObjectTransferManager.writeObjectToFile(response, PATH_TO_FILE);
        } catch (IOException e) {
            String tag = ScrollableViewDirectionScroller.class.toString();
            String message = "Failed to push the response in a file";
            Log.e(tag, message, e);
        }

    }

}
