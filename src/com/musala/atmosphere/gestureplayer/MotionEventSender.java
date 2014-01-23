package com.musala.atmosphere.gestureplayer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.hardware.input.InputManager;
import android.os.SystemClock;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;

public class MotionEventSender
{
	private final static int FAILURE_EXIT_CODE = -666;

	private final static float DEFAULT_FINGER_PRESSURE = 1.0f;

	private final static float DEFAULT_FINGER_SIZE = 1.0f;

	private final static int DEFAULT_META_STATE = 0;

	private final static float DEFAULT_PRECISION_X = 1.0f;

	private final static float DEFAULT_PRECISION_Y = 1.0f;

	// NOTE : http://developer.android.com/reference/android/view/MotionEvent.html#getDeviceId%28%29
	private static int DEVICE_ID = 0;

	private final static int DEFAULT_EDGE_FLAGS = 0;

	// All events come from the touchscreen
	private final static int DEFAULT_INPUT_SOURCE = InputDevice.SOURCE_TOUCHSCREEN;

	/**
	 * used for unique pointer ID allocation.
	 */
	private int pointerIdInc = 0;

	/**
	 * maps a pointer ID to it's {@link PointerProperties PointerProperties} instance.
	 */
	private Map<Integer, MotionEvent.PointerProperties> pointerPropertiesMap = new HashMap<Integer, MotionEvent.PointerProperties>();

	/**
	 * maps a pointer ID to it's {@link PointerCoords PointerCoords} instance.
	 */
	private Map<Integer, PointerCoords> pointerPositionsMap = new HashMap<Integer, PointerCoords>();

	/**
	 * Holds a pointer ID list for all pointers that will be a part of the {@link MotionEvent MotionEvent} to be sent
	 * next.
	 */
	private List<Integer> pointerIds = new LinkedList<Integer>();

	private InputManager inputManager;

	private Method injectInputEventMethod;

	public MotionEventSender()
	{
		try
		{
			Method imInstanceMethod = InputManager.class.getDeclaredMethod("getInstance");
			imInstanceMethod.setAccessible(true);
			inputManager = (InputManager) imInstanceMethod.invoke(null);

			injectInputEventMethod = InputManager.class.getDeclaredMethod(	"injectInputEvent",
																			android.view.InputEvent.class,
																			int.class);

			int[] deviceIds = InputDevice.getDeviceIds();
			for (int inputDeviceId : deviceIds)
			{
				InputDevice inputDevice = InputDevice.getDevice(inputDeviceId);
				int deviceSources = inputDevice.getSources();
				if ((deviceSources & InputDevice.SOURCE_TOUCHSCREEN) == InputDevice.SOURCE_TOUCHSCREEN)
				{
					DEVICE_ID = inputDeviceId;
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			exitFailure();
		}

	}

	/**
	 * Registers a new pointer at a specified initial position (in other words, a finger has pressed the touch screen).
	 * 
	 * @param initialX
	 *        - the initial X pointer position.
	 * @param initialY
	 *        - the initial Y pointer position.
	 * @return the ID of the newly registered pointer.
	 */
	public int registerPointer(float initialX, float initialY)
	{
		int newPointerId = getNextPointerId();

		MotionEvent.PointerProperties newPointerProperties = new PointerProperties();
		newPointerProperties.id = newPointerId;
		newPointerProperties.toolType = MotionEvent.TOOL_TYPE_FINGER;
		pointerPropertiesMap.put(newPointerId, newPointerProperties);

		PointerCoords newPointerCoords = new PointerCoords();
		newPointerCoords = new PointerCoords();
		newPointerCoords.x = initialX;
		newPointerCoords.y = initialY;
		newPointerCoords.pressure = DEFAULT_FINGER_PRESSURE;
		newPointerCoords.size = DEFAULT_FINGER_SIZE;
		pointerPositionsMap.put(newPointerId, newPointerCoords);

		pointerIds.add(newPointerId);

		injectFingerRegisterEvent(newPointerId);

		return newPointerId;
	}

	/**
	 * Sets the next position of a specified pointer.
	 * 
	 * @param pointerId
	 *        - the pointer ID which has changed it's position.
	 * @param toX
	 *        - the new X pointer position.
	 * @param toY
	 *        - the new Y pointer position.
	 */
	public void setMovement(int pointerId, float toX, float toY)
	{
		if (!pointerPositionsMap.containsKey(pointerId))
		{
			return;
		}

		PointerCoords pointerPosition = pointerPositionsMap.get(pointerId);
		pointerPosition.x = toX;
		pointerPosition.y = toY;

		// pointerPositionsMap.put(pointerId, pointerPosition);
	}

	/**
	 * Applies all changes that were set via {@link #setMovement(int, float, float) the setMovement(...)} method.
	 */
	public void applyMovements()
	{
		int pointerCount = pointerIds.size();

		PointerCoords[] coords = new PointerCoords[pointerCount];
		PointerProperties[] properties = new PointerProperties[pointerCount];

		int i = 0;
		for (Integer pointerId : pointerIds)
		{
			coords[i] = pointerPositionsMap.get(pointerId);
			properties[i] = pointerPropertiesMap.get(pointerId);
			i++;
		}

		injectMultipointMotionEvent(coords, properties);
	}

	/**
	 * Unregisters a specified pointer (in other words, a finger has been lifted from the touch screen).
	 * 
	 * @param pointerId
	 *        - the ID of the pointer to be unregistered.
	 */
	public void unregisterPointer(int pointerId)
	{
		injectFingerUnregisterEvent(pointerId);

		pointerIds.remove(new Integer(pointerId));
		pointerPropertiesMap.remove(pointerId);
		pointerPositionsMap.remove(pointerId);
	}

	private void injectFingerRegisterEvent(int newPointerId)
	{
		int pointerCount = pointerIds.size();
		int action = pointerCount == 1 ? MotionEvent.ACTION_DOWN : MotionEvent.ACTION_POINTER_DOWN;

		PointerCoords[] coords = new PointerCoords[pointerCount];
		PointerProperties[] properties = new PointerProperties[pointerCount];

		int i = 0;
		for (Integer pointerId : pointerIds)
		{
			coords[i] = pointerPositionsMap.get(pointerId);
			properties[i] = pointerPropertiesMap.get(pointerId);
			i++;
		}

		long now = SystemClock.uptimeMillis();
		MotionEvent event = MotionEvent.obtain(	now,
												now,
												action,
												pointerCount,
												properties,
												coords,
												DEFAULT_META_STATE,
												0,
												DEFAULT_PRECISION_X,
												DEFAULT_PRECISION_Y,
												DEVICE_ID,
												DEFAULT_EDGE_FLAGS,
												DEFAULT_INPUT_SOURCE,
												0);

		if (action == MotionEvent.ACTION_POINTER_DOWN)
		{
			int newPointerIndex = event.findPointerIndex(newPointerId);
			event.setAction(action | (newPointerIndex << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
		}

		injectMethodFacade(event);
	}

	private void injectFingerUnregisterEvent(int upPointerId)
	{
		int pointerCount = pointerIds.size();
		int action = pointerCount == 1 ? MotionEvent.ACTION_UP : MotionEvent.ACTION_POINTER_UP;

		PointerCoords[] coords = new PointerCoords[pointerCount];
		PointerProperties[] properties = new PointerProperties[pointerCount];

		int i = 0;
		for (Integer pointerId : pointerIds)
		{
			coords[i] = pointerPositionsMap.get(pointerId);
			properties[i] = pointerPropertiesMap.get(pointerId);
			if (upPointerId == pointerId)
			{
				coords[i].pressure = 0.0f;
			}
			i++;
		}

		long now = SystemClock.uptimeMillis();
		MotionEvent event = MotionEvent.obtain(	now,
												now,
												action,
												pointerCount,
												properties,
												coords,
												DEFAULT_META_STATE,
												0,
												DEFAULT_PRECISION_X,
												DEFAULT_PRECISION_Y,
												DEVICE_ID,
												DEFAULT_EDGE_FLAGS,
												DEFAULT_INPUT_SOURCE,
												0);

		if (action == MotionEvent.ACTION_POINTER_UP)
		{
			int newPointerIndex = event.findPointerIndex(upPointerId);
			event.setAction(action | (newPointerIndex << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
		}

		injectMethodFacade(event);
	}

	private void injectMultipointMotionEvent(PointerCoords[] coords, PointerProperties[] properties)
	{
		int pointerCount = coords.length;
		long now = SystemClock.uptimeMillis();

		MotionEvent event = MotionEvent.obtain(now, now, // when the event happened
												MotionEvent.ACTION_MOVE, // event type
												pointerCount, // number of pointers
												properties, // pointer properties
												coords, // pointer coordinate descriptors
												DEFAULT_META_STATE,
												0,
												DEFAULT_PRECISION_X,
												DEFAULT_PRECISION_Y,
												DEVICE_ID,
												DEFAULT_EDGE_FLAGS,
												DEFAULT_INPUT_SOURCE,
												0 // no flags
		);

		injectMethodFacade(event);
	}

	private void injectMethodFacade(InputEvent event)
	{
		try
		{
			injectInputEventMethod.invoke(inputManager, event, 2 /* InputManager.INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH */);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			exitFailure();
		}
	}

	private static void exitFailure()
	{
		System.exit(FAILURE_EXIT_CODE);
	}

	private int getNextPointerId()
	{
		return pointerIdInc++;
	}

	@Override
	public void finalize()
	{
		for (Integer id : pointerIds)
		{
			unregisterPointer(id);
		}
	}

}
