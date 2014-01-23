package com.musala.atmosphere.gestureplayer.socket;

import java.io.IOException;

import android.util.Log;

import com.musala.atmosphere.commons.ad.DeviceSocketServer;
import com.musala.atmosphere.commons.ad.Request;
import com.musala.atmosphere.commons.ad.gestureplayer.Anchor;
import com.musala.atmosphere.commons.ad.gestureplayer.GesturePlayerConstants;
import com.musala.atmosphere.commons.ad.gestureplayer.GesturePlayerRequest;
import com.musala.atmosphere.commons.ad.gestureplayer.Timeline;
import com.musala.atmosphere.commons.ad.service.ServiceRequest;
import com.musala.atmosphere.gestureplayer.GesturePlayer;

/**
 * A socket server that listens for sockets requests by the Agent, sends them to the {@link AgentRequestHandler} and
 * sends its response back to the Agent.
 * 
 * @author yordan.petrov
 * 
 */
public class PlayerSocketServer
{
	public static final String ATMOSPHERE_GESTURE_PLAYER_TAG = "AtmosphereGesturePlayer";

	private DeviceSocketServer<GesturePlayerRequest> gestureRequestServer;

	public PlayerSocketServer() throws IOException
	{
		int port = GesturePlayerConstants.PLAYER_PORT;
		AgentRequestHandler agentRequestHandler = new AgentRequestHandler();
		gestureRequestServer = new DeviceSocketServer<GesturePlayerRequest>(agentRequestHandler, port);

		Log.i(ATMOSPHERE_GESTURE_PLAYER_TAG, "Server started.");
	}

	/**
	 * Starts listening for requests.
	 */
	public void run()
	{
		try
		{
			while (true)
			{
				System.out.println("Waiting for connection.");
				Log.i(ATMOSPHERE_GESTURE_PLAYER_TAG, "Waiting for connection.");
				gestureRequestServer.acceptConnection();

				Log.i(ATMOSPHERE_GESTURE_PLAYER_TAG, "Connection accepted, receiving request.");
				Request<GesturePlayerRequest> request = gestureRequestServer.handle();

				Log.i(ATMOSPHERE_GESTURE_PLAYER_TAG, "Handled request '" + request.getType() + "'.");

				gestureRequestServer.endConnection();
				Log.i(ATMOSPHERE_GESTURE_PLAYER_TAG, "Connection closed.");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
