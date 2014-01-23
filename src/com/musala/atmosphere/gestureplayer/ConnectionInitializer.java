package com.musala.atmosphere.gestureplayer;

import java.io.IOException;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.musala.atmosphere.gestureplayer.socket.PlayerSocketServer;

/**
 * Class used to start the socket server for the ATMOSPHERE gesture player.
 * 
 * @author yordan.petrov
 * 
 */
public class ConnectionInitializer extends UiAutomatorTestCase
{
	/**
	 * Starts the socket server.
	 * 
	 * @throws IOException
	 */
	public void testRun() throws IOException
	{
		PlayerSocketServer playerSocketServer = new PlayerSocketServer();
		playerSocketServer.run();
	}
}
