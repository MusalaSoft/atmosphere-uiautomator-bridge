package com.musala.atmosphere.uiautomator.xpath.accessibility;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.xpath.tree.UiRoot;

/**
 * Extracts the XPath path to an {@link AccessibilityElement} for an {@link UiRoot} structure.
 *
 * @author yordan.petrov
 *
 */
public class AccessibilityElementXPathExtractor {
    private static final String PREFIX = "/node";

    /**
     * Extracts the XPath from the root to a given {@link AccessibilityElement} for an {@link UiRoot} structure.
     *
     * @param element
     *        - the element whose path will be extracted
     * @return the absolute XPath path to the element
     */
    public static String extract(AccessibilityElement element) {
        String elementPath = element.getPath();
        String[] pathIndexes = elementPath.split(AccessibilityElement.PATH_SEPARATOR);

        if (pathIndexes[0].length() == 0) {
            return PREFIX;
        }

        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(PREFIX);
        for (String indexString : pathIndexes) {
            Integer index = Integer.valueOf(indexString) + 1;
            pathBuilder.append(PREFIX).append("[").append(index).append("]");
        }

        return pathBuilder.toString();
    }
}
