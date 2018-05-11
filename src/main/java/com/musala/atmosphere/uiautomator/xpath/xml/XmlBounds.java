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

/**
 * Interface representing object that can be interpreted as an XML bounds.
 *
 * @author yordan.petrov
 *
 */
public interface XmlBounds {
    /**
     * <b>This method should return the format used in the XML dump:
     * '[upperLef.X,upperLeft.Y][lowerRight.X,lowerRight.Y]'.</b> For example on a 1920x1080 display the whole display
     * is represented like this [0,0][1920,1080].
     *
     * @return string representation of the bounds following the described format
     */
    @Override
    String toString();

    /**
     * Gets a {@link Bounds bounds} object.
     *
     * @return a {@link Bounds bounds} object
     */
    Bounds getBounds();
}
