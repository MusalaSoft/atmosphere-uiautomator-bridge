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
