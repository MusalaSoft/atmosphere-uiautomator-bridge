package com.musala.atmosphere.uiautomator.ping;

import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.Dispatchable;

/**
 * Class that handles ping or validation requests.
 * 
 * @author yordan.petrov
 * 
 */
public class PingHandler implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        return UIAutomatorRequest.VALIDATION;
    }

}
