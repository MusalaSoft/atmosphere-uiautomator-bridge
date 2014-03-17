package com.musala.atmosphere.uiautomator.socket;

import java.io.IOException;

import android.os.Bundle;

import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class used to start the socket server for the ATMOSPHERE UIAutomator bridge.
 * 
 * @author yordan.petrov
 * 
 */
public class ConnectionInitializer implements Dispatchable {
    /**
     * Starts the socket server.
     * 
     * @throws IOException
     */
    @Override
    public void handle(Bundle notUsed) throws IOException {
        UIAutomatorBridgeSocketServer bridgeSocketServer = new UIAutomatorBridgeSocketServer();
        bridgeSocketServer.run();
    }
}
