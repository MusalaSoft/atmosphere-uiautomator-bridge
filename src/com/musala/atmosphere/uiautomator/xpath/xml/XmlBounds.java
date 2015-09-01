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
