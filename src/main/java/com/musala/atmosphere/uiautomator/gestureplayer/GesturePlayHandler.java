package com.musala.atmosphere.uiautomator.gestureplayer;

import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.commons.gesture.Gesture;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class that is responsible for executing the passed {@link Gesture gesture} on the device.
 * 
 * @author yordan.petrov
 * 
 */
public class GesturePlayHandler implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        Gesture pointerTimelines = (Gesture) args[0];
        GesturePlayer gesturePlayer = new GesturePlayer();
        gesturePlayer.insertTimelineList(pointerTimelines);

        gesturePlayer.markTimelineStart();

        while (gesturePlayer.hasMoreToAct()) {
            gesturePlayer.act();
        }

        return UIAutomatorRequest.VOID_RESPONSE;
    }
}
