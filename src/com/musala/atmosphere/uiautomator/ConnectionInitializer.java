package com.musala.atmosphere.uiautomator;

import java.io.IOException;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.musala.atmosphere.uiautomator.socket.UIAutomatorBridgeSocketServer;

/**
 * Class used to start the socket server for the ATMOSPHERE UIAutomator bridge.
 * 
 * @author yordan.petrov
 * 
 */
public class ConnectionInitializer extends UiAutomatorTestCase {
    /**
     * Starts the socket server.
     * 
     * @throws IOException
     */
    public void testRun() throws IOException {
        UIAutomatorBridgeSocketServer bridgeSocketServer = new UIAutomatorBridgeSocketServer();
        bridgeSocketServer.run();
    }
}
