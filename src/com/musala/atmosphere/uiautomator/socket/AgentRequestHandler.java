package com.musala.atmosphere.uiautomator.socket;

import java.util.List;

import com.musala.atmosphere.commons.ad.Request;
import com.musala.atmosphere.commons.ad.RequestHandler;
import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorBridgeRequest;
import com.musala.atmosphere.commons.gesture.Timeline;
import com.musala.atmosphere.commons.ui.UiElementDescriptor;
import com.musala.atmosphere.uiautomator.gestureplayer.GesturePlayer;
import com.musala.atmosphere.uiautomator.textfieldclear.TextFieldEraser;

/**
 * Class that handles request from the agent and responds to them.
 * 
 * @author yordan.petrov
 * 
 */
public class AgentRequestHandler implements RequestHandler<UIAutomatorBridgeRequest> {

    public AgentRequestHandler() {
    }

    @Override
    public Object handle(Request<UIAutomatorBridgeRequest> bridgeRequest) {
        UIAutomatorBridgeRequest requestType = bridgeRequest.getType();
        Object[] arguments = bridgeRequest.getArguments();

        Object response;
        switch (requestType) {
            case VALIDATION:
                response = validate();
                break;

            case PLAY_GESTURE:
                List<Timeline> pointerTimelines = (List<Timeline>) arguments[0];
                response = playGesture(pointerTimelines);
                break;
            case CLEAR_FIELD:
                UiElementDescriptor descriptor = (UiElementDescriptor) arguments[0];
                response = clearField(descriptor);
                break;

            default:
                response = UIAutomatorBridgeRequest.ANY_RESPONSE;
                break;
        }

        return response;
    }

    /**
     * Returns response to a validation request.
     * 
     * @return validation response.
     */
    private Object validate() {
        return UIAutomatorBridgeRequest.VALIDATION;
    }

    /**
     * Plays the passed list of {@link Timeline} objects.
     * 
     * @return an empty response, since we are not requesting any information.
     */
    private Object playGesture(List<Timeline> pointerTimelines) {
        GesturePlayer gesturePlayer = new GesturePlayer();
        gesturePlayer.insertTimelineList(pointerTimelines);

        gesturePlayer.markTimelineStart();

        while (gesturePlayer.hasMoreToAct()) {
            gesturePlayer.act();
        }

        return UIAutomatorBridgeRequest.ANY_RESPONSE;
    }

    /**
     * Clears a UI element.
     * 
     * @param descriptor
     *        - the UI element descriptor.
     * @return boolean indicating whether the UI element was found or not.
     */
    private Object clearField(UiElementDescriptor descriptor) {
        return TextFieldEraser.clearField(descriptor);
    }
}
