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

package com.musala.atmosphere.uiautomator.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.commons.ui.selector.CssAttribute;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementMatcher;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Class responsible for traversing {@link AccessibilityNodeInfo accessibility nodes} hierarchy by the given root node.
 *
 * @author filareta.yordanova
 *
 *
 */
public class AccessibilityNodeTraverser {
    private static final int ROOT_NODE_INDEX = 0;

    private AccessibilityNodeInfo localRootNodeInfo;

    private String pathToLocalRoot;

    /**
     * Creates a new {@link AccessibilityNodeTraverser} by a given local root and the absolute path to it.
     *
     * @param localRootNodeInfo
     *        - the node information of the local root
     * @param pathToLocalRoot
     *        - the absolute path to the local root
     */
    public AccessibilityNodeTraverser(AccessibilityNodeInfo localRootNodeInfo, String pathToLocalRoot) {
        this.localRootNodeInfo = localRootNodeInfo;
        this.pathToLocalRoot = pathToLocalRoot;
    }

    /**
     * Finds all elements in the hierarchy of {@link AccessibilityNodeInfo accessibility nodes} that are matching the
     * given {@link UiElementSelector selector} and {@link UiElementMatcher matcher}.
     *
     * @param matcher
     *        - defines the strategy for matching the given {@link UiElementSelector selector} and node from the
     *        hierarchy
     * @param selector
     *        - contains the properties which accessibility nodes should match
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return list of {@link AccessibilityElement} that matches the given element {@link UiElementSelector properties}
     */
    public List<AccessibilityElement> find(UiElementMatcher<UiElementSelector> matcher,
                                           UiElementSelector selector,
                                           boolean visibleOnly) {
        List<AccessibilityElement> matchingElements = new ArrayList<AccessibilityElement>();

        if (isMatchFound(localRootNodeInfo, selector, matcher, ROOT_NODE_INDEX)) {
            Stack<Integer> pathIndexes = new Stack<Integer>();
            AccessibilityElementBuilder accessibilityElementBuilder = AccessibilityFactory.getAccessibilityElementBuilder();
            AccessibilityElement rootAccessibilityElement = accessibilityElementBuilder.build(localRootNodeInfo,
                                                                                              pathIndexes,
                                                                                              pathToLocalRoot,
                                                                                              ROOT_NODE_INDEX);
            matchingElements.add(rootAccessibilityElement);
        }

        matchingElements.addAll(getChildren(matcher, selector, false, visibleOnly));

        return matchingElements;
    }

    /**
     * Finds all children of the root in the hierarchy of {@link AccessibilityNodeInfo accessibility nodes} that are
     * matching the given {@link UiElementSelector selector} and {@link UiElementMatcher matcher}.
     *
     * @param matcher
     *        - defines the strategy for matching the given {@link UiElementSelector selector} and node from the
     *        hierarchy
     * @param selector
     *        - contains the properties which accessibility nodes should match
     * @param directOnly
     *        - if <code>true</code> only direct children are traversed, otherwise all children are traversed
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return list of {@link AccessibilityElement} that are children of the root
     */
    public List<AccessibilityElement> getChildren(UiElementMatcher<UiElementSelector> matcher,
                                                  UiElementSelector selector,
                                                  boolean directOnly,
                                                  boolean visibleOnly) {
        List<AccessibilityElement> childrenList = new ArrayList<AccessibilityElement>();

        Queue<AccessibilityNodeInfo> nextNodes = new LinkedList<AccessibilityNodeInfo>();
        Map<AccessibilityNodeInfo, Integer> nodeToindex = new HashMap<AccessibilityNodeInfo, Integer>();

        processDirectChildren(localRootNodeInfo, nextNodes, nodeToindex, visibleOnly);

        while (!nextNodes.isEmpty()) {
            AccessibilityNodeInfo currentNodeInformation = nextNodes.poll();
            Integer currentNodeIndex = nodeToindex.get(currentNodeInformation);

            if (isMatchFound(currentNodeInformation, selector, matcher, currentNodeIndex)) {
                Stack<Integer> pathIndexes = extractPath(nodeToindex, currentNodeInformation);
                AccessibilityElementBuilder accessibilityElementBuilder = AccessibilityFactory.getAccessibilityElementBuilder();
                AccessibilityElement accessibilityElement = accessibilityElementBuilder.build(currentNodeInformation,
                                                                                              pathIndexes,
                                                                                              pathToLocalRoot,
                                                                                              currentNodeIndex);
                childrenList.add(accessibilityElement);
            }

            if (!directOnly) {
                processDirectChildren(currentNodeInformation, nextNodes, nodeToindex, visibleOnly);
            }
        }

        return childrenList;
    }

    /**
     * Returns the corresponding {@link AccessibilityNodeInfo accessibility node} matching the given
     * {@link AccessibilityElement element} exists in the hierarchy. Characteristics of the given
     * {@link UiElementSelector selector} and the strategy defined by the {@link UiElementMatcher matcher} are used when
     * the check is performed.
     *
     * @param element
     *        - {@link AccessibilityElement accessibility element} for which a match will be searched in the
     *        {@link AccessibilityNodeInfo nodes} hierarchy
     * @param propertiesMatcher
     *        - defines the strategy for matching the given {@link AccessibilityElement element} and node from the
     *        accessibility nodes hierarchy
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return {@link AccessibilityNodeInfo accessibility node} that matches the characteristics of the
     *         {@link UiElementSelector selector} and the path contained in the {@link AccessibilityElement element}
     */
    public AccessibilityNodeInfo getCorrespondingAccessibilityNodeInfo(AccessibilityElement element,
                                                                       UiElementMatcher<UiElementPropertiesContainer> propertiesMatcher,
                                                                       boolean visibleOnly) {
        String path = element.getPath();
        List<String> pathIndexes = path.trim().isEmpty() ? new ArrayList<String>()
                : Arrays.asList(path.split(AccessibilityElement.PATH_SEPARATOR));
        Iterator<String> pathIterator = pathIndexes.iterator();

        AccessibilityNodeInfo accessibilityNodeInfo = findNodeByPath(localRootNodeInfo,
                                                                     pathIterator,
                                                                     ROOT_NODE_INDEX,
                                                                     visibleOnly);

        return propertiesMatcher.match(element, accessibilityNodeInfo) ? accessibilityNodeInfo : null;
    }

    /**
     * Finds an {@link AccessibilityNodeInfo accessibility node} corresponding to the given path in the accessibility
     * nodes hierarchy.
     *
     */
    private AccessibilityNodeInfo findNodeByPath(AccessibilityNodeInfo currentNode,
                                                 Iterator<String> path,
                                                 int index,
                                                 boolean visibleOnly) {
        if (!path.hasNext()) {
            return currentNode;
        }

        int currentPathIndex = Integer.parseInt(path.next());
        AccessibilityNodeInfo nextNode = currentNode.getChild(currentPathIndex);

        if (nextNode == null) {
            return null;
        }

        boolean checkExistence = visibleOnly ? nextNode.isVisibleToUser() : true;
        if (checkExistence) {
            return findNodeByPath(nextNode, path, currentPathIndex, visibleOnly);
        }

        return null;
    }

    private void processDirectChildren(AccessibilityNodeInfo nodeInfo,
                                       Queue<AccessibilityNodeInfo> nextNodes,
                                       Map<AccessibilityNodeInfo, Integer> nodeToindex,
                                       boolean visibleOnly) {
        int childCount = nodeInfo.getChildCount();

        for (int index = 0; index < childCount; index++) {
            AccessibilityNodeInfo childNodeInformation = nodeInfo.getChild(index);
            boolean traverseAll = visibleOnly ? childNodeInformation != null && childNodeInformation.isVisibleToUser()
                    : true;

            if (traverseAll) {
                nextNodes.add(childNodeInformation);
                nodeToindex.put(childNodeInformation, index);
            }
        }
    }

    private boolean isMatchFound(AccessibilityNodeInfo nodeInfo,
                                 UiElementSelector selector,
                                 UiElementMatcher<UiElementSelector> matcher,
                                 int index) {
        Integer selectorIndex = selector.getIntegerValue(CssAttribute.INDEX);

        if (selectorIndex == null) {
            return matcher.match(selector, nodeInfo);
        }

        return selectorIndex.intValue() == index && matcher.match(selector, nodeInfo);
    }

    private Stack<Integer> extractPath(Map<AccessibilityNodeInfo, Integer> indexes, AccessibilityNodeInfo target) {
        Stack<Integer> indexesPath = new Stack<Integer>();

        while (!localRootNodeInfo.equals(target)) {
            indexesPath.add(indexes.get(target));
            target = target.getParent();
        }

        return indexesPath;
    }
}
