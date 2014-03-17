package com.musala.atmosphere.uiautomator.util;

import java.util.HashMap;
import java.util.Map;

import com.musala.atmosphere.uiautomator.ActionDispatcher;
import com.musala.atmosphere.uiautomator.ChildProcessAction;

/**
 * Utility class responsible for starting child worker processes.
 * 
 * @author georgi.gaydarov
 * 
 */
public class ChildProcessStarter {

    private static final String jarName = "AtmosphereUIAutomatorBridge.jar";

    private static final String[] libNames = {"AtmosphereUIAutomatorBridgeLibs.jar"};

    private Map<String, String> params;

    public ChildProcessStarter(ChildProcessAction action) {
        params = new HashMap<String, String>();
        String actionIdString = Integer.toString(action.getId());
        params.put(ActionDispatcher.PARAM_ACTION, actionIdString);
    }

    public void addParameter(String key, String value) {
        params.put(key, value);
    }

    public void removeParameter(String key) {
        params.remove(key);
    }

    public void start() {
        StringBuilder builder = new StringBuilder();
        builder.append("uiautomator runtest ");
        builder.append(jarName);
        for (String lib : libNames) {
            builder.append(" ");
            builder.append(lib);
        }
        builder.append(" -c ");
        builder.append(ActionDispatcher.class.getCanonicalName());

        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(" -e ");
            builder.append(entry.getKey());
            builder.append(" ");
            builder.append(entry.getValue());
        }

        String cmdLine = builder.toString();
        Runtime r = Runtime.getRuntime();
        // System.out.println("DEBUG: exec line: " + cmdLine);
        try {
            Process child = r.exec(cmdLine);
            child.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}