package com.musala.atmosphere.gestureplayer.socket;

import com.musala.atmosphere.commons.ad.Request;
import com.musala.atmosphere.commons.ad.RequestHandler;
import com.musala.atmosphere.commons.ad.gestureplayer.GesturePlayerRequest;
import com.musala.atmosphere.commons.ad.gestureplayer.Timeline;
import com.musala.atmosphere.gestureplayer.GesturePlayer;

/**
 * Class that handles request from the agent and responds to them.
 * 
 * @author yordan.petrov
 * 
 */
public class AgentRequestHandler implements RequestHandler<GesturePlayerRequest>
{
	private GesturePlayer gesturePlayer;

	public AgentRequestHandler()
	{
		gesturePlayer = new GesturePlayer();
	}

	@Override
	public Object handle(Request<GesturePlayerRequest> gesturePlayerRequest)
	{
		GesturePlayerRequest requestType = gesturePlayerRequest.getType();
		Object[] arguments = gesturePlayerRequest.getArguments();

		Object response;
		switch (requestType)
		{
			case VALIDATION:
				response = validate();
				break;

			case INSERT_TIMELINE:
				response = insertTimeline(arguments);
				break;

			case MARK_TIMELINE_START:
				response = markTimelineStart();
				break;

			case ACT:
				response = act();
				break;

			case PLAY_GESTURE:
				response = playGesture();
				break;

			default:
				response = GesturePlayerRequest.ANY_RESPONSE;
				break;
		}

		return response;
	}

	/**
	 * Returns response to a validation request.
	 * 
	 * @return validation response.
	 */
	private Object validate()
	{
		return GesturePlayerRequest.VALIDATION;
	}

	/**
	 * Adds a {@link Timeline} instance that will be used for touch event generation.
	 * 
	 * @param arguments
	 *        - {@link Timeline} instance.
	 * @return a fake response, since we are not requesting any information.
	 */
	private Object insertTimeline(Object[] arguments)
	{
		Timeline timeline = (Timeline) arguments[0];
		gesturePlayer.insertTimeline(timeline);

		return GesturePlayerRequest.ANY_RESPONSE;
	}

	/**
	 * Marks this moment as the moment when the {@link #insertTimeline(Timeline) timelines} execution starts.
	 * 
	 * @return a fake response, since we are not requesting any information.
	 */
	private Object markTimelineStart()
	{
		gesturePlayer.markTimelineStart();

		return GesturePlayerRequest.ANY_RESPONSE;
	}

	/**
	 * Calculates the pointer position on which each {@link Timeline} should be at the current moment and then sends
	 * adequate motion event to the device's input manager.
	 * 
	 * @return a fake response, since we are not requesting any information.
	 */
	private Object act()
	{
		gesturePlayer.act();

		return GesturePlayerRequest.ANY_RESPONSE;
	}

	/**
	 * Plays the inserted {@link Timeline} objects.
	 * 
	 * @return a fake response, since we are not requesting any information.
	 */
	private Object playGesture()
	{
		gesturePlayer.markTimelineStart();

		while (gesturePlayer.hasMoreToAct())
		{
			gesturePlayer.act();
		}

		return GesturePlayerRequest.ANY_RESPONSE;
	}
}
