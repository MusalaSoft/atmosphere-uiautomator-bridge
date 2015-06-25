package com.musala.atmosphere.uiautomator.uidump;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcher;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.UiElementSelectorMatcherFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperFactory;
import com.musala.atmosphere.uiautomator.util.AccessibilityNodeTraverser;

/**
 * Class used to get all {@link AccessibilityElement UI elements} present on the screen and matching a given selector.
 * 
 * @author denis.bialev
 *
 */
public class UiElementRetriever implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        UiElementSelector selector = (UiElementSelector) args[0];
        Boolean visibleOnly = (Boolean) args[1];

        AccessibilityHelper accessibilityHelper = AccessibilityHelperFactory.getHelper();
        AccessibilityNodeInfo accessibilityRootNode = accessibilityHelper.getRootInActiveWindow();

        AccessibilityNodeTraverser accessibilityNodeTraverser = new AccessibilityNodeTraverser(accessibilityRootNode,
                                                                                               "");

        UiElementSelectorMatcher selectorMatcher = UiElementSelectorMatcherFactory.getSelectorMatcher();

        return accessibilityNodeTraverser.find(selectorMatcher, selector, visibleOnly);
    }
}
