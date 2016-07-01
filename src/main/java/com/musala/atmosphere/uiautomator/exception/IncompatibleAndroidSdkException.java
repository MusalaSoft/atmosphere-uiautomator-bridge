package com.musala.atmosphere.uiautomator.exception;

/**
 * Exception thrown when some functionality from the Android SDK is attempted to be used on a
 * device that has different and incompatible version of the SDK.
 * 
 * @author vassil.angelov
 *
 */
public class IncompatibleAndroidSdkException extends RuntimeException {

    private static final long serialVersionUID = 8605822170852721985L;

    public IncompatibleAndroidSdkException() {
        super();
    }

    public IncompatibleAndroidSdkException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleAndroidSdkException(String message) {
        super(message);
    }

    public IncompatibleAndroidSdkException(Throwable cause) {
        super(cause);
    }

}
