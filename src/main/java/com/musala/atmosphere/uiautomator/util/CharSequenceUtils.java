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

package com.musala.atmosphere.uiautomator.util;

/**
 * Utilities for {@link CharSequence} objects.
 *
 * @author yordan.petrov
 *
 */
public class CharSequenceUtils {
    /**
     * Converts a given {@link CharSequence char sequence} to string.
     *
     * @param sequence
     *        - the {@link CharSequence char sequence} to be converted
     * @return a String build from the given {@link CharSequence char sequence}
     */
    public static String getString(CharSequence sequence) {
        if (sequence == null) {
            return null;
        }

        // sequence.toString() may have been overridden
        return new StringBuilder(sequence.length()).append(sequence).toString();
    }
}
