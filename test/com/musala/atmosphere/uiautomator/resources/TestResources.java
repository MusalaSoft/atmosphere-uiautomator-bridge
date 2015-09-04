package com.musala.atmosphere.uiautomator.resources;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Common resources intended for test usage.
 *
 * @author yordan.petrov
 *
 */
public class TestResources {
    private static final String IMAGE_VIEW_CLASS_NAME = "ImageView";

    private static final String PACKAGE_NAME = "com.example.coolstory2";

    /**
     * Returns an array of containing all elements in a prebuilt {@link AccessibilityNodeInfo} hierarchy. The first
     * element is the root.
     *
     * @return an array of containing all elements in a prebuilt {@link AccessibilityNodeInfo} hierarchy
     */
    public static AccessibilityNodeInfo[] getMockedHierarchy() {
        AccessibilityNodeInfo[] mockedAccessibilityNodes = new AccessibilityNodeInfo[7];

        mockedAccessibilityNodes[0] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[0].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[0].isVisibleToUser()).thenReturn(true);
        when(mockedAccessibilityNodes[0].getViewIdResourceName()).thenReturn("example/:root");
        when(mockedAccessibilityNodes[0].isScrollable()).thenReturn(true);

        mockedAccessibilityNodes[1] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[1].getClassName()).thenReturn(IMAGE_VIEW_CLASS_NAME);
        when(mockedAccessibilityNodes[1].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[1].isVisibleToUser()).thenReturn(true);
        when(mockedAccessibilityNodes[1].isFocused()).thenReturn(true);

        mockedAccessibilityNodes[2] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[2].getClassName()).thenReturn(IMAGE_VIEW_CLASS_NAME);
        when(mockedAccessibilityNodes[2].isClickable()).thenReturn(true);
        when(mockedAccessibilityNodes[2].isVisibleToUser()).thenReturn(true);
        when(mockedAccessibilityNodes[2].getContentDescription()).thenReturn("Really cool pic");
        when(mockedAccessibilityNodes[2].getPackageName()).thenReturn(PACKAGE_NAME);

        mockedAccessibilityNodes[3] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[3].getClassName()).thenReturn("Button");
        when(mockedAccessibilityNodes[3].isClickable()).thenReturn(true);
        when(mockedAccessibilityNodes[3].isVisibleToUser()).thenReturn(true);
        when(mockedAccessibilityNodes[3].getText()).thenReturn("Click me!");
        when(mockedAccessibilityNodes[3].getPackageName()).thenReturn(PACKAGE_NAME);
        when(mockedAccessibilityNodes[3].getViewIdResourceName()).thenReturn("someId");

        mockedAccessibilityNodes[4] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[4].getClassName()).thenReturn(IMAGE_VIEW_CLASS_NAME);
        when(mockedAccessibilityNodes[4].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[4].isVisibleToUser()).thenReturn(false);

        mockedAccessibilityNodes[5] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[5].getClassName()).thenReturn("ViewGroup");
        when(mockedAccessibilityNodes[5].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[5].isVisibleToUser()).thenReturn(true);
        when(mockedAccessibilityNodes[5].isLongClickable()).thenReturn(true);
        when(mockedAccessibilityNodes[5].isSelected()).thenReturn(true);
        when(mockedAccessibilityNodes[5].isPassword()).thenReturn(true);

        mockedAccessibilityNodes[6] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[6].getClassName()).thenReturn(IMAGE_VIEW_CLASS_NAME);
        when(mockedAccessibilityNodes[6].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[6].isVisibleToUser()).thenReturn(true);

        when(mockedAccessibilityNodes[0].getChildCount()).thenReturn(3);
        when(mockedAccessibilityNodes[0].getChild(0)).thenReturn(mockedAccessibilityNodes[1]);
        when(mockedAccessibilityNodes[0].getChild(1)).thenReturn(mockedAccessibilityNodes[2]);
        when(mockedAccessibilityNodes[0].getChild(2)).thenReturn(mockedAccessibilityNodes[3]);

        when(mockedAccessibilityNodes[1].getChildCount()).thenReturn(2);
        when(mockedAccessibilityNodes[1].getChild(0)).thenReturn(mockedAccessibilityNodes[4]);
        when(mockedAccessibilityNodes[1].getChild(1)).thenReturn(mockedAccessibilityNodes[5]);

        when(mockedAccessibilityNodes[3].getChildCount()).thenReturn(2);
        when(mockedAccessibilityNodes[3].getChild(1)).thenReturn(mockedAccessibilityNodes[6]);

        when(mockedAccessibilityNodes[2].getChildCount()).thenReturn(0);
        when(mockedAccessibilityNodes[4].getChildCount()).thenReturn(0);
        when(mockedAccessibilityNodes[5].getChildCount()).thenReturn(0);
        when(mockedAccessibilityNodes[6].getChildCount()).thenReturn(0);

        when(mockedAccessibilityNodes[1].getParent()).thenReturn(mockedAccessibilityNodes[0]);
        when(mockedAccessibilityNodes[2].getParent()).thenReturn(mockedAccessibilityNodes[0]);
        when(mockedAccessibilityNodes[3].getParent()).thenReturn(mockedAccessibilityNodes[0]);

        when(mockedAccessibilityNodes[4].getParent()).thenReturn(mockedAccessibilityNodes[1]);
        when(mockedAccessibilityNodes[5].getParent()).thenReturn(mockedAccessibilityNodes[1]);
        when(mockedAccessibilityNodes[6].getParent()).thenReturn(mockedAccessibilityNodes[3]);

        return mockedAccessibilityNodes;
    }
}
