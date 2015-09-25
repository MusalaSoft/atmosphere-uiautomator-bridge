package com.musala.atmosphere.uiautomator.uidump;

import org.apache.commons.jxpath.JXPathContext;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;
import com.musala.atmosphere.uiautomator.xpath.query.QueryExecutor;
import com.musala.atmosphere.uiautomator.xpath.tree.UiRoot;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Class used to get all {@link AccessibilityElement UI elements} present on the screen and matching a given xpath
 * query.
 * 
 * @author denis.bialev
 *
 */
public class UiElementXPathRetriever implements Dispatchable {

    @Override
    public Object handle(Object[] args) {
        String xPathQuery = (String) args[0];
        Boolean visibleOnly = (Boolean) args[1];

        AccessibilityHelper accessibilityHelper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo accessibilityRootNode = accessibilityHelper.getRootInActiveWindow();

        UiRoot uiRoot = AccessibilityFactory.getUiRoot(accessibilityRootNode);

        JXPathContext context = JXPathContext.newContext(uiRoot);
        QueryExecutor executor = new QueryExecutor(context);

        return executor.execute(xPathQuery, visibleOnly);
    }
}
