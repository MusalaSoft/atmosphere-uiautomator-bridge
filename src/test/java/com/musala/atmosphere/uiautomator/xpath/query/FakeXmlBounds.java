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

package com.musala.atmosphere.uiautomator.xpath.query;

import com.musala.atmosphere.commons.geometry.Bounds;
import com.musala.atmosphere.commons.geometry.Point;
import com.musala.atmosphere.uiautomator.xpath.xml.XmlBounds;

/**
 * A fake {@link XmlBounds} implementation intended for test purposes.
 *
 * @author yordan.petrov
 *
 */
class FakeXmlBounds implements XmlBounds {
    private int top;

    private int left;

    private int bottom;

    private int right;

    /**
     * Constructs a {@link FakeXmlBounds} objects by given top, left, bottom and right border coordinates.
     *
     * @param top
     *        - the coordinate of the top border
     * @param left
     *        - the coordinate of the left border
     * @param bottom
     *        - the coordinate of the bottom border
     * @param right
     *        - the coordinate of the right border
     */
    public FakeXmlBounds(int top, int left, int bottom, int right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    @Override
    public Bounds getBounds() {
        return new Bounds(new Point(left, top), new Point(right, bottom));
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[")
                                  .append(left)
                                  .append(",")
                                  .append(top)
                                  .append("][")
                                  .append(right)
                                  .append(",")
                                  .append(bottom)
                                  .append("]")
                                  .toString();
    }
}
