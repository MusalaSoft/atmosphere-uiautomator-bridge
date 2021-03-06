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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.jxpath.JXPathContext;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.xpath.accessibility.AccessibilityElementXPathExtractor;
import com.musala.atmosphere.uiautomator.xpath.node.converter.UiNodeToAccessibilityElementConverter;
import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;

/**
 * Executes XPath queries on a given context.
 *
 * @author yordan.petrov
 *
 */
public class QueryExecutor {
    private JXPathContext context;

    public QueryExecutor(JXPathContext context) {
        this.context = context;
    }

    /**
     * Executes a given XPath query returning a list of {@link AccessibilityElement} matching the criteria.
     *
     * @param query
     *        - the query to be executed
     * @param visibleOnly
     *        - <code>true</code> if only the visible elements should be included, <code>false</code> otherwise
     * @return a list of {@link AccessibilityElement} matching the criteria
     */
    public List<AccessibilityElement> execute(String query, boolean visibleOnly) {
        String formattedQuery = QueryFormatter.format(query, visibleOnly);

        List<?> nodes = context.selectNodes(formattedQuery);
        List<AccessibilityElement> wrappedElements = new ArrayList<AccessibilityElement>();
        for (Object nodeObject : nodes) {
            if ((nodeObject != null) && !(nodeObject instanceof UiNode)) {
                continue;
            }

            UiNode node = (UiNode) nodeObject;
            AccessibilityElement element = UiNodeToAccessibilityElementConverter.convertNode(node);
            wrappedElements.add(element);
        }

        return wrappedElements;
    }

    /**
     * Executes a given XPath query on a given local root and returns a list of {@link AccessibilityElement} matching
     * the criteria.
     *
     * @param localRoot
     *        - the local root
     * @param query
     *        - the query to be executed
     * @param visibleOnly
     *        - <code>true</code> if only the visible elements should be included, <code>false</code> otherwise
     * @return a list of {@link AccessibilityElement} matching the criteria
     */
    public List<AccessibilityElement> executeOnLocalRoot(AccessibilityElement localRoot,
                                                         String query,
                                                         boolean visibleOnly) {
        String pathToRoot = AccessibilityElementXPathExtractor.extract(localRoot);
        StringBuilder completeQueryBuilder = new StringBuilder();
        completeQueryBuilder.append(pathToRoot);
        // Handling '/some/path' and './*' or '/*'
        if (!query.startsWith("/")) {
            completeQueryBuilder.append('/');
        }
        completeQueryBuilder.append(query);

        return execute(completeQueryBuilder.toString(), visibleOnly);
    }
}
