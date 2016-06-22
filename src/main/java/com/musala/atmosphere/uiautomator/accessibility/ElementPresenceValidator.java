package com.musala.atmosphere.uiautomator.accessibility;

import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementMatcher;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.util.AccessibilityNodeTraverser;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Class that is responsible for validating that a given element is present on the screen.
 *
 * @author yordan.petrov
 *
 */
public class ElementPresenceValidator implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        AccessibilityElement element = (AccessibilityElement) args[0];
        boolean visibleOnly = (Boolean) args[1];

        AccessibilityHelper helper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo root = helper.getRootInActiveWindow();

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(root, "");
        UiElementMatcher<UiElementPropertiesContainer> propertiesMatcher = AccessibilityFactory.getUiElementPropertiesContainerMatcher();

        AccessibilityNodeInfo foundElement = traverser.getCorrespondingAccessibilityNodeInfo(element,
                                                                                             propertiesMatcher,
                                                                                             visibleOnly);
        return foundElement != null;
    }
}
