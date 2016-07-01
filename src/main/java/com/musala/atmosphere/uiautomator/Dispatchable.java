package com.musala.atmosphere.uiautomator;

/**
 * Interface, defining the general overlay of dispatachable child process action handler classes.
 * 
 * @author georgi.gaydarov
 * 
 */
public interface Dispatchable {
    Object handle(Object[] args) throws Exception;
}
