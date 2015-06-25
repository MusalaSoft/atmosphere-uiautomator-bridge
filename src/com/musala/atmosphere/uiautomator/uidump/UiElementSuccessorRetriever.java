package com.musala.atmosphere.uiautomator.uidump;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.exceptions.UiElementFetchingException;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementPropertiesContainerMatcher;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcher;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.UiElementPropertiesContainerMatcherFactory;
import com.musala.atmosphere.uiautomator.accessibility.UiElementSelectorMatcherFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperFactory;
import com.musala.atmosphere.uiautomator.util.AccessibilityNodeTraverser;

/**
 * Class that retrieves {@link AccessibilityElement child UI elements} for a given {@link AccessibilityElement
 * accessibility element}. Found children are present on the current screen and matching the given
 * {@link UiElementSelector selector}.
 * 
 * @author filareta.yordanova
 *
 */
public class UiElementSuccessorRetriever implements Dispatchable {
    @Override
    public Object handle(Object[] args) throws Exception {
        AccessibilityElement parentElement = (AccessibilityElement) args[0];
        UiElementSelector successorSelector = (UiElementSelector) args[1];
        Boolean directChildrenOnly = (Boolean) args[2];
        Boolean visibleOnly = (Boolean) args[3];

        AccessibilityHelper accessibilityHelper = AccessibilityHelperFactory.getHelper();
        AccessibilityNodeInfo accessibilityRootNode = accessibilityHelper.getRootInActiveWindow();

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(accessibilityRootNode, "");
        UiElementPropertiesContainerMatcher propertiesMatcher = UiElementPropertiesContainerMatcherFactory.getPropertiesContainerMatcher();

        AccessibilityNodeInfo parentAccessibilityNode = traverser.getCorrespondingAccessibilityNodeInfo(parentElement,
                                                                                                        propertiesMatcher,
                                                                                                        visibleOnly);

        if (parentAccessibilityNode == null) {
            throw new UiElementFetchingException("Element, for which retrieving children is requested, is not present on the screen.");
        }

        AccessibilityNodeTraverser successorsTraverser = new AccessibilityNodeTraverser(parentAccessibilityNode,
                                                                                        parentElement.getPath());

        UiElementSelectorMatcher successorSelectorMatcher = UiElementSelectorMatcherFactory.getSelectorMatcher();

        return successorsTraverser.getChildren(successorSelectorMatcher,
                                               successorSelector,
                                               directChildrenOnly,
                                               visibleOnly);
    }
}
