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
