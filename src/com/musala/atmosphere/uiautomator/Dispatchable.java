package com.musala.atmosphere.uiautomator;

import android.os.Bundle;

/**
 * Interface, defining the general overlay of dispatachable child process action handler classes.
 * 
 * @author georgi.gaydarov
 * 
 */
public interface Dispatchable {
    void handle(Bundle params) throws Exception;
}
