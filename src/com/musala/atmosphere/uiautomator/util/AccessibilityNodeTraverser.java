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

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.selector.CssAttribute;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerMatcher;
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
     * given {@link UiElementSelector selector} and {@link UiElementSelectorMatcher matcher}.
     *
     * @param matcher
     *        - defines the strategy for matching the given {@link UiElementSelector selector} and node from the
     *        hierarchy
     * @param selector
     *        - contains the properties which accessibility nodes should match
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return list of {@link AccessibilityElements} that matches the given element {@link UiElementSelector properties}
     */
    public List<AccessibilityElement> find(UiElementSelectorMatcher matcher,
                                           UiElementSelector selector,
                                           boolean visibleOnly) {
        List<AccessibilityElement> matchingElements = new ArrayList<AccessibilityElement>();

        if (isMatchFound(localRootNodeInfo, selector, matcher, ROOT_NODE_INDEX)) {
            Stack<Integer> pathIndexes = new Stack<Integer>();
            AccessibilityElement rootAccessibilityElement = AccessibilityElementBuilder.build(localRootNodeInfo,
                                                                                              pathIndexes,
                                                                                              pathToLocalRoot,
                                                                                              ROOT_NODE_INDEX);
            matchingElements.add(rootAccessibilityElement);
        }

        matchingElements.addAll(getChildren(matcher, selector, visibleOnly));

        return matchingElements;
    }

    /**
     * Finds all direct children of the local root in the hierarchy of {@link AccessibilityNodeInfo accessibility nodes}
     * that are matching the given {@link UiElementSelector selector} and {@link UiElementSelectorMatcher matcher}.
     * 
     * @param matcher
     *        - defines the strategy for matching the given {@link UiElementSelector selector} and node from the
     *        hierarchy
     * @param selector
     *        - contains the properties which accessibility nodes should match
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return list of {@link AccessibilityElements} that are direct children of the local root
     * 
     */
    public List<AccessibilityElement> getDirectChildren(UiElementSelectorMatcher matcher,
                                                        UiElementSelector selector,
                                                        boolean visibleOnly) {
        List<AccessibilityElement> directChildrenList = new ArrayList<AccessibilityElement>();
        Map<AccessibilityNodeInfo, Integer> indexes = new HashMap<AccessibilityNodeInfo, Integer>();

        int numberOfChildren = localRootNodeInfo.getChildCount();

        for (int index = 0; index < numberOfChildren; index++) {
            AccessibilityNodeInfo currentNodeInfo = localRootNodeInfo.getChild(index);

            if (currentNodeInfo != null) {
                boolean traverseAll = visibleOnly ? currentNodeInfo.isVisibleToUser() : true;

                if (traverseAll && isMatchFound(currentNodeInfo, selector, matcher, index)) {
                    Stack<Integer> pathIndexes = extractPath(indexes, currentNodeInfo);
                    AccessibilityElement accessibilityElement = AccessibilityElementBuilder.build(currentNodeInfo,
                                                                                                  pathIndexes,
                                                                                                  pathToLocalRoot,
                                                                                                  index);
                    directChildrenList.add(accessibilityElement);
                }
            }
        }

        return directChildrenList;
    }

    /**
     * Finds all children of the root in the hierarchy of {@link AccessibilityNodeInfo accessibility nodes} that are
     * matching the given {@link UiElementSelector selector} and {@link UiElementSelectorMatcher matcher}.
     * 
     * @param matcher
     *        - defines the strategy for matching the given {@link UiElementSelector selector} and node from the
     *        hierarchy
     * @param selector
     *        - contains the properties which accessibility nodes should match
     * @param visibleOnly
     *        - if <code>true</code> only the visible nodes will be used; if <code>false</code> all nodes will be used
     * @return list of {@link AccessibilityElements} that are children of the root
     */
    public List<AccessibilityElement> getChildren(UiElementSelectorMatcher matcher,
                                                  UiElementSelector selector,
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
                AccessibilityElement accessibilityElement = AccessibilityElementBuilder.build(currentNodeInformation,
                                                                                              pathIndexes,
                                                                                              pathToLocalRoot,
                                                                                              currentNodeIndex);
                childrenList.add(accessibilityElement);
            }

            processDirectChildren(currentNodeInformation, nextNodes, nodeToindex, visibleOnly);
        }

        return childrenList;
    }

    /**
     * Checks if {@link AccessibilityNodeInfo accessibility node} matching the given {@link AccessibilityElement
     * element} exists in the hierarchy. Characteristics of the given {@link UiElementSelector selector} and the
     * strategy defined by the {@link UiElementSelectorMatcher matcher} are used when the check is performed.
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
    public AccessibilityNodeInfo isElementExisting(AccessibilityElement element,
                                                   UiElementPropertiesContainerMatcher propertiesMatcher,
                                                   boolean visibleOnly) {
        String path = element.getPath();
        List<String> pathIndexes = path.trim().isEmpty() ? new ArrayList<String>()
                : Arrays.asList(path.split(AccessibilityElementBuilder.PATH_SEPARATOR));
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
            boolean traverseAll = visibleOnly ? childNodeInformation.isVisibleToUser() : true;

            if (childNodeInformation != null && traverseAll) {
                nextNodes.add(childNodeInformation);
                nodeToindex.put(childNodeInformation, index);
            }
        }
    }

    private boolean isMatchFound(AccessibilityNodeInfo nodeInfo,
                                 UiElementSelector selector,
                                 UiElementSelectorMatcher matcher,
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
