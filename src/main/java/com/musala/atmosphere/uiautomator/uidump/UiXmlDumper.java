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

import java.io.File;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ad.uiautomator.UIAutomatorRequest;
import com.musala.atmosphere.uiautomator.Dispatchable;
import com.musala.atmosphere.uiautomator.accessibility.AccessibilityFactory;
import com.musala.atmosphere.uiautomator.helper.AccessibilityHelper;

/**
 * Class that is responsible for dumping the current screen of the device to a XML file.
 * 
 * @author yordan.petrov
 * 
 */
public class UiXmlDumper implements Dispatchable {

    @Override
    public Object handle(Object[] args) throws Exception {
        AccessibilityHelper helper = AccessibilityFactory.getAccessibilityHelper();
        AccessibilityNodeInfo root = helper.getRootInActiveWindow();

        if (root != null) {
            String filename = (String) args[0];
            helper.dumpWindowToFile(root, new File(filename));
        }

        return UIAutomatorRequest.VOID_RESPONSE;
    }
}
