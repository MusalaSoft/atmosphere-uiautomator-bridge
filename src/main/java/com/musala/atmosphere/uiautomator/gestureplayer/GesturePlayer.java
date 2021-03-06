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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.hardware.input.InputManager;
import android.os.SystemClock;
import android.view.MotionEvent;

import com.musala.atmosphere.commons.gesture.Anchor;
import com.musala.atmosphere.commons.gesture.Timeline;

/**
 * Main class behind on-device gesture simulation. Used to arrange a time line (consisting of anchor points to be
 * reached at specific moments in time). This class then can be used to calculate each finger's position at the current
 * moment and inject the appropriate high level events in the OS.
 * 
 * @author georgi.gaydarov
 * 
 */
public class GesturePlayer {
    private MotionEventSender eventSender = new MotionEventSender();

    /**
     * As map keys should be immutable, an Integer is assigned to each {@link Timeline Timeline} instance to be used as
     * it's unique ID.
     */
    private List<Integer> timelineIds = new LinkedList<Integer>();

    /**
     * Maps a {@link Timeline Timeline} ID to the actual {@link Timeline Timeline} instance.
     */
    private Map<Integer, Timeline> timelines = new HashMap<Integer, Timeline>();

    /**
     * Maps a {@link Timeline Timeline} ID to the last {@link Anchor Anchor} point that was sent from that Timeline
     * queue.
     */
    private Map<Integer, Anchor> previousLinePoint = new HashMap<Integer, Anchor>();

    /**
     * Maps a {@link Timeline Timeline} ID to the unique pointer ID that was assigned to that timeline queue.
     */
    private Map<Integer, Integer> timelinePointerId = new HashMap<Integer, Integer>();

    private long timelineStart = -1;

    /**
     * Adds a {@link Timeline Timeline} instance that will be used for touch event generation when {@link #act() act()}
     * is invoked.
     * 
     * @param timeline
     *        - the timeline to be added.
     */
    public void insertTimeline(Timeline timeline) {
        Integer newId = timelineIds.size();
        timelineIds.add(newId);
        timelines.put(newId, timeline);
    }

    /**
     * Adds a list of {@link Timeline Timeline} instances that will be used for touch event generation when
     * {@link #act() act()} is invoked.
     * 
     * @param timelines
     *        - the list of timelines to be added.
     */
    public void insertTimelineList(List<Timeline> timelines) {
        for (Timeline timeline : timelines) {
            insertTimeline(timeline);
        }
    }

    /**
     * Marks this moment as the moment when the {@link #insertTimeline(Timeline) timelines} execution starts.
     */
    public void markTimelineStart() {
        timelineStart = SystemClock.uptimeMillis();
    }

    /**
     * Calculates the pointer position on which each {@link #insertTimeline(Timeline) inserted timeline} should be at
     * the current moment and then sends adequate {@link MotionEvent MotionEvent} to the device's {@link InputManager
     * InputManager} using the underlying {@link MotionEventSender MotionEventSender} instance.
     * 
     * <b>MAKE SURE YOU HAVE INVOKED {@link #markTimelineStart() markTimelineStart()} BEFORE INVOKING THIS METHOD.</b>
     */
    public void act() {
        List<Integer> timelineIdsCopy = new LinkedList<Integer>(timelineIds);
        for (Integer timelineId : timelineIdsCopy) {
            long elapsedTime = SystemClock.uptimeMillis() - timelineStart;
            Timeline timeline = timelines.get(timelineId);

            if (timelinePointerId.containsKey(timelineId)) {
                int pointerId = timelinePointerId.get(timelineId);
                if (timeline.size() == 0) {
                    eventSender.unregisterPointer(pointerId);
                    timelines.remove(timelineId);
                    previousLinePoint.remove(timelineId);
                    timelinePointerId.remove(timelineId);
                    timelineIds.remove(timelineId);
                    continue;
                }

                Anchor nextPoint = timeline.get(0);
                if (elapsedTime >= nextPoint.getTimeAfterStart()) {
                    eventSender.setMovement(pointerId, nextPoint.getX(), nextPoint.getY());
                    previousLinePoint.put(timelineId, nextPoint);
                    timeline.remove();
                } else {
                    Anchor previousPoint = previousLinePoint.get(timelineId);

                    long timeBetweenPoints = nextPoint.getTimeAfterStart() - previousPoint.getTimeAfterStart();
                    long elapsedTimeAfterPreviousPoint = elapsedTime - previousPoint.getTimeAfterStart();

                    float alpha = (float) elapsedTimeAfterPreviousPoint / (float) timeBetweenPoints;
                    float lerpX = lerp(previousPoint.getX(), nextPoint.getX(), alpha);
                    float lerpY = lerp(previousPoint.getY(), nextPoint.getY(), alpha);

                    eventSender.setMovement(pointerId, lerpX, lerpY);
                }
                eventSender.applyMovements();
            } else {
                Anchor nextPoint = timeline.get(0);
                if (elapsedTime >= nextPoint.getTimeAfterStart()) {
                    int pointerId = eventSender.registerPointer(nextPoint.getX(), nextPoint.getY());
                    timelinePointerId.put(timelineId, pointerId);
                    previousLinePoint.put(timelineId, nextPoint);
                    timeline.remove();
                }
            }
        }
    }

    private static final float lerp(float a, float b, float alpha) {
        return (b - a) * alpha + a;
    }

    /**
     * @return true if there are still events in the future that must be sent, false if all
     *         {@link #insertTimeline(Timeline) inserted timelines} have reached their ends.
     */
    public boolean hasMoreToAct() {
        return timelineIds.size() > 0;
    }
}
