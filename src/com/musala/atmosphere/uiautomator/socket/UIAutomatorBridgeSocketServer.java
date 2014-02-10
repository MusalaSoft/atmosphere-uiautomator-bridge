package com.musala.atmosphere.uiautomator.socket;

import java.io.IOException;

import android.util.Log;

import com.musala.atmosphere.commons.ad.DeviceSocketServer;
import com.musala.atmosphere.commons.ad.Request;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorBridgeConstants;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorBridgeRequest;

/**
 * A socket server that listens for socket requests by the Agent, passes them to the correct request handler and sends
 * it's response back to the Agent.
 * 
 * @author yordan.petrov
 * 
 */
public class UIAutomatorBridgeSocketServer
{
	public static final String ATMOSPHERE_UIAUTOMATOR_BRIDGE_TAG = "AtmosphereUIAutomatorBridge";

	private DeviceSocketServer<UIAutomatorBridgeRequest> uiAutomatorBridgeRequestServer;

	public UIAutomatorBridgeSocketServer() throws IOException
	{
		int port = UIAutomatorBridgeConstants.BRIDGE_PORT;
		AgentRequestHandler agentRequestHandler = new AgentRequestHandler();
		uiAutomatorBridgeRequestServer = new DeviceSocketServer<UIAutomatorBridgeRequest>(agentRequestHandler, port);

		Log.i(ATMOSPHERE_UIAUTOMATOR_BRIDGE_TAG, "Server started.");
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
				Log.i(ATMOSPHERE_UIAUTOMATOR_BRIDGE_TAG, "Waiting for connection.");
				uiAutomatorBridgeRequestServer.acceptConnection();

				Log.i(ATMOSPHERE_UIAUTOMATOR_BRIDGE_TAG, "Connection accepted, receiving request.");
				Request<UIAutomatorBridgeRequest> request = uiAutomatorBridgeRequestServer.handle();

				Log.i(ATMOSPHERE_UIAUTOMATOR_BRIDGE_TAG, "Handled request '" + request.getType() + "'.");

				uiAutomatorBridgeRequestServer.endConnection();
				Log.i(ATMOSPHERE_UIAUTOMATOR_BRIDGE_TAG, "Connection closed.");
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
