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
