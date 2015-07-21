package com.musala.atmosphere.uiautomator.uidump;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.util.structure.tree.Tree;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.util.TreeBuilder;

/**
 * Class that is responsible for building a {@link Tree tree} representation of the current screen of the device.
 * 
 * @author yordan.petrov
 * 
 */
public class UiTreeBuilder implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        boolean visibleOnly = (Boolean) args[0];

        AccessibilityHelper helper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo root = helper.getRootInActiveWindow();
        Tree<AccessibilityElement> tree = TreeBuilder.buildTree(root, visibleOnly);

        return TreeBuilder.buildTree(root, visibleOnly);
    }
}
