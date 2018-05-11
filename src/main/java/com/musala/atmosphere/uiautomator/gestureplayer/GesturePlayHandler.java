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
