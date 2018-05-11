// This file is part of the ATMOSPHERE mobile testing framework.
// Copyright (C) 2016 MusalaSoft
//
// ATMOSPHERE is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// ATMOSPHERE is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with ATMOSPHERE.  If not, see <http://www.gnu.org/licenses/>.

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
