package com.musala.atmosphere.uiautomator.xpath.query;

import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.tree.UiRoot;

/**
 * A class that formats XPath queries to be suitable for execution over an {@link UiRoot} or an {@link UiNode} object.
 *
 * @author yordan.petrov
 *
 */
class QueryFormatter {
    private static final String CHILDREN_ATTRIBUTE = "[@node]";

    private static final String VISIBLE_ATTRIBUTE = "[@visible=true()]";

    /**
     * Formats a given XPath query to be suitable for execution over an {@link UiRoot} or an {@link UiNode} object.
     *
     * @param query
     *        - the query to be formatted
     * @return the formated query
     */
    static String format(String query, boolean visibleOnly) {
        String suffix = null;
        if (visibleOnly) {
            StringBuilder suffixBuilder = new StringBuilder();
            suffixBuilder.append(CHILDREN_ATTRIBUTE);
            suffixBuilder.append(VISIBLE_ATTRIBUTE);
            suffix = suffixBuilder.toString();
        } else {
            suffix = CHILDREN_ATTRIBUTE;
        }

        boolean isInBrackets = false;
        boolean isInQuotes = false;

        /**
         * Basically we append the suffix to the end of each step of the XPath query. That way we make sure the result
         * contains only attributes that themselves have children (and are visible) - the node elements. Each step of
         * the query is separated by a '/' symbol. However, we must omit such symbols in quotes or '[]' blocks. To
         * achieve that we use a state machine.
         */
        StringBuilder formatedQueryBuilder = new StringBuilder();
        formatedQueryBuilder.append(query.charAt(0));
        // No need to check the beginning
        for (int index = 1; index < query.length(); index++) {
            char currentChar = query.charAt(index);
            switch (currentChar) {
                case '\'':
                    isInQuotes = !isInQuotes;
                    break;
                case '[':
                    isInBrackets = isInQuotes ? isInBrackets : true;
                    break;
                case ']':
                    isInBrackets = isInQuotes ? isInBrackets : false;
                    break;
                case '/':
                    // In case of '//' or when we are in quotes or brackets
                    if (!(isInQuotes || isInBrackets) && query.charAt(index - 1) != '/') {
                        formatedQueryBuilder.append(suffix);
                    }
                    break;

                default:
                    break;
            }

            formatedQueryBuilder.append(currentChar);
        }

        // The end itself is an end of a XPath query step
        formatedQueryBuilder.append(suffix);
        return formatedQueryBuilder.toString();
    }
}
