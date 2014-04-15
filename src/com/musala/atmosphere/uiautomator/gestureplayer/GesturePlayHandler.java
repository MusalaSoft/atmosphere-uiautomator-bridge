package com.musala.atmosphere.uiautomator.gestureplayer;

import java.util.List;

import com.musala.atmosphere.commons.gesture.Timeline;
import com.musala.atmosphere.uiautomator.Dispatchable;

public class GesturePlayHandler implements Dispatchable {
    /**
     * Plays the passed list of {@link Timeline Timeline} objects.
     * 
     * @return an empty response, since we are not requesting any information.
     */
    @Override
    public void handle(Object[] args) throws Exception {
        List<Timeline> pointerTimelines = (List<Timeline>) args[0];
        GesturePlayer gesturePlayer = new GesturePlayer();
        gesturePlayer.insertTimelineList(pointerTimelines);

        gesturePlayer.markTimelineStart();

        while (gesturePlayer.hasMoreToAct()) {
            gesturePlayer.act();
        }
    }
}
