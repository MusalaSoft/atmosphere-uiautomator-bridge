package com.musala.atmosphere.uiautomator;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.musala.atmosphere.commons.ad.Request;
import com.musala.atmosphere.commons.ad.RequestHandler;
import com.musala.atmosphere.commons.ad.service.ConnectionConstants;
import com.musala.atmosphere.commons.ad.socket.OnDeviceSocketServer;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.logger.Log4JConfigurator;
import com.musala.atmosphere.uiautomator.util.UIAutomatorProcessAction;

/**
 * UI automator main entry point. Initializes the socket server and request handlers.
 * 
 * @author georgi.gaydarov
 * 
 */
public class ActionDispatcher extends UiAutomatorTestCase implements RequestHandler<UIAutomatorRequest>, Dispatchable {

    private static final Logger LOGGER = Logger.getLogger(ActionDispatcher.class);

    private static final long SLEEP_TIEMOUT = 1000;

    private static volatile boolean isRunning;

    private static OnDeviceSocketServer<UIAutomatorRequest> socketServer;

    /**
     * Main entry point being called when the UI automator process is started.
     */
    public void testRun() {
        Log4JConfigurator.configure();

        try {
            socketServer = new OnDeviceSocketServer<UIAutomatorRequest>(this, ConnectionConstants.UI_AUTOMATOR_PORT);
            socketServer.start();

            isRunning = true;
            LOGGER.info("Automator socket server started successfully.");
        } catch (IOException e) {
            LOGGER.error("Could not start ATMOSPHERE socket server", e);
        }

        while (isRunning) {
            try {
                Thread.sleep(SLEEP_TIEMOUT);
            } catch (InterruptedException e) {
                // Nothing to do here
            }
        }
    }

    @Override
    public Object handle(Object[] args) {
        isRunning = false;

        if (socketServer != null) {
            synchronized (socketServer) {
                if (socketServer != null) {
                    socketServer.terminate();
                }
            }
        }

        return UIAutomatorRequest.VOID_RESPONSE;
    }

    @Override
    public Object handle(Request<UIAutomatorRequest> request) {
        UIAutomatorRequest requestType = request.getType();
        Object[] requestArguments = request.getArguments();

        UIAutomatorProcessAction action = UIAutomatorProcessAction.getByRequest(requestType);
        Class<? extends Dispatchable> handlerClass = action.getHandler();
        LOGGER.info("Passing job to " + handlerClass.toString());

        try {
            Dispatchable handler = handlerClass.newInstance();
            return handler.handle(requestArguments);
        } catch (Exception e) {
            LOGGER.error("Eror while handling request.", e);
        }

        return UIAutomatorRequest.VOID_RESPONSE;
    }
}
