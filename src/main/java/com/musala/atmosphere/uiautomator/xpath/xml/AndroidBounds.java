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

package com.musala.atmosphere.uiautomator.xpath.xml;

import com.musala.atmosphere.commons.geometry.Bounds;
import com.musala.atmosphere.commons.geometry.Point;

import android.graphics.Rect;

/**
 * {@link XmlBounds} implementation working with the Android {@link Rect} objects.
 *
 * @author yordan.petrov
 *
 */
public class AndroidBounds implements XmlBounds {
    Rect bounds;

    /**
     * Constructs an {@link AndroidBounds} object by a given {@link Rect} object.
     *
     * @param bounds
     *        - the {@link Rect} object that will be used
     */
    public AndroidBounds(Rect bounds) {
        this.bounds = bounds;
    }

    @Override
    public String toString() {
        // Using String builder for optimization
        return new StringBuilder().append("[")
                                  .append(bounds.left)
                                  .append(",")
                                  .append(bounds.top)
                                  .append("][")
                                  .append(bounds.right)
                                  .append(",")
                                  .append(bounds.bottom)
                                  .append("]")
                                  .toString();
    }

    @Override
    public Bounds getBounds() {
        Point upperLeft = new Point(bounds.left, bounds.top);
        // As Bounds are initially constructed with width and height
        int width = bounds.right - bounds.left;
        int height = bounds.bottom - bounds.top;
        return new Bounds(upperLeft, width, height);
    }
}
