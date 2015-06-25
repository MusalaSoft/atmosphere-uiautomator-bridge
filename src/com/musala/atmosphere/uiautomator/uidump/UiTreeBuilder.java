package com.musala.atmosphere.uiautomator.uidump;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.util.structure.tree.Tree;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelperFactory;
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

        AccessibilityHelperFactory accessibilityHelperFactory = new AccessibilityHelperFactory();
        AccessibilityHelper helper = AccessibilityHelperFactory.getHelper();
        AccessibilityNodeInfo root = helper.getRootInActiveWindow();

        return TreeBuilder.buildTree(root, visibleOnly);
    }
}
