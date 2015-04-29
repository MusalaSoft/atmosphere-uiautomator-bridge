package com.musala.atmosphere.uiautomator.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.selector.CssAttribute;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcher;

/**
 * Class responsible for traversing {@link AccessibilityNodeInfo accessibility nodes} hierarchy by the given root node.
 * 
 * @author filareta.yordanova
 * 
 * 
 */
public class AccessibilityNodeTraverser {
    private static final int ROOT_NODE_INDEX = 0;

    /**
     * Finds all elements in the hierarchy of {@link AccessibilityNodeInfo accessibility nodes} that are matching the
     * given {@link UiElementSelector selector} and {@link UiElementSelectorMatcher matcher}.
     * 
     * @param nodeInformation
     *        - the root of the {@link AccessibilityNodeInfo accessibility nodes} hierarchy to search in
     * @param matcher
     *        - defines the strategy for matching the given {@link UiElementSelector selector} and node from the
     *        hierarchy
     * @param selector
     *        - contains the properties which accessibility nodes should match
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return list of {@link AccessibilityElements} that matches the given element {@link UiElementSelector properties}
     */
    public List<AccessibilityElement> find(AccessibilityNodeInfo nodeInformation,
                                           UiElementSelectorMatcher matcher,
                                           UiElementSelector selector,
                                           boolean visibleOnly) {
        List<AccessibilityElement> matchingElements = new ArrayList<AccessibilityElement>();

        Queue<AccessibilityNodeInfo> nodes = new LinkedList<AccessibilityNodeInfo>();
        Map<AccessibilityNodeInfo, Integer> indexes = new HashMap<AccessibilityNodeInfo, Integer>();

        nodes.add(nodeInformation);
        indexes.put(nodeInformation, ROOT_NODE_INDEX);

        while (!nodes.isEmpty()) {
            AccessibilityNodeInfo currentNodeInformation = nodes.poll();
            Integer currentNodeIndex = indexes.get(currentNodeInformation);

            if (isMatchFound(currentNodeInformation, selector, matcher, currentNodeIndex)) {
                Stack<Integer> pathIndexes = extractPath(indexes, currentNodeInformation);
                AccessibilityElement accessibilityElement = AccessibilityElementBuilder.build(nodeInformation,
                                                                                              pathIndexes,
                                                                                              currentNodeIndex);
                matchingElements.add(accessibilityElement);
            }

            int childCount = currentNodeInformation.getChildCount();
            for (int index = 0; index < childCount; index++) {
                AccessibilityNodeInfo childNodeInformation = currentNodeInformation.getChild(index);

                if (childNodeInformation != null) {
                    boolean traverseAll = visibleOnly ? childNodeInformation.isVisibleToUser() : true;

                    if (traverseAll) {
                        nodes.add(childNodeInformation);
                        indexes.put(childNodeInformation, index);
                    }
                }
            }
        }

        return matchingElements;
    }

    private boolean isMatchFound(AccessibilityNodeInfo nodeInformation,
                                 UiElementSelector selector,
                                 UiElementSelectorMatcher matcher,
                                 int index) {
        Integer selectorIndex = selector.getIntegerValue(CssAttribute.INDEX);

        if (selectorIndex == null) {
            return matcher.match(selector, nodeInformation);
        }

        return selectorIndex.intValue() == index && matcher.match(selector, nodeInformation);
    }

    private Stack<Integer> extractPath(Map<AccessibilityNodeInfo, Integer> indexes, AccessibilityNodeInfo target) {
        Stack<Integer> indexesPath = new Stack<Integer>();

        while (target != null) {
            indexesPath.add(indexes.get(target));
            target = target.getParent();
        }

        return indexesPath;
    }
}
