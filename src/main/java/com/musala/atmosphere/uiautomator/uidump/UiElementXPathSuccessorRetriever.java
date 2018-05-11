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
 * Class used to get {@link AccessibilityElement UI element}'s children present on the screen and matching a given xpath
 * query.
 * 
 * @author denis.bialev
 *
 */
public class UiElementXPathSuccessorRetriever implements Dispatchable {
    @Override
    public Object handle(Object[] args) throws Exception {
        String xPathQuery = (String) args[0];
        Boolean visibleOnly = (Boolean) args[1];
        AccessibilityElement localRootElement = (AccessibilityElement) args[2];

        AccessibilityHelper accessibilityHelper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo accessibilityRootNode = accessibilityHelper.getRootInActiveWindow();

        UiRoot uiRoot = AccessibilityFactory.getUiRoot(accessibilityRootNode);

        JXPathContext context = JXPathContext.newContext(uiRoot);
        QueryExecutor executor = new QueryExecutor(context);

        return executor.executeOnLocalRoot(localRootElement, xPathQuery, visibleOnly);
    }
}
