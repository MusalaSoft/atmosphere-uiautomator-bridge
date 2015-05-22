package com.musala.atmosphere.uiautomator.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import android.view.accessibility.AccessibilityNodeInfo;

import com.musala.atmosphere.commons.ui.selector.CssAttribute;
import com.musala.atmosphere.commons.ui.selector.UiElementSelector;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementSelectorMatcher;

/**
 * 
 * @author filareta.yordanova
 *
 */
public class AccessibilityElementExistenceTest {
    private static final String WRONG_ELEMENT_FOUND_MESSAGE = "The accessibility node that was found does not match the expected one.";

    private static final String UNEXPECTED_RESULT_MESSAGE = "Unexpected match for the given accessibility element was found.";

    private static AccessibilityNodeInfo mockedNodeInfoRoot;

    private static AccessibilityNodeInfo[] mockedAccessibilityNodes;

    private static UiElementSelectorMatcher mockedMatcher;

    private UiElementSelector selector;

    @BeforeClass
    public static void setUp() {
        mockedAccessibilityNodes = new AccessibilityNodeInfo[6];
        mockedMatcher = mock(UiElementSelectorMatcher.class);

        mockedNodeInfoRoot = mock(AccessibilityNodeInfo.class);

        when(mockedNodeInfoRoot.getClassName()).thenReturn("RelativeLayout");
        when(mockedNodeInfoRoot.isFocusable()).thenReturn(true);
        when(mockedNodeInfoRoot.isVisibleToUser()).thenReturn(true);

        mockedAccessibilityNodes[0] = mock(AccessibilityNodeInfo.class);

        when(mockedAccessibilityNodes[0].getClassName()).thenReturn("ImageView");
        when(mockedAccessibilityNodes[0].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[0].isVisibleToUser()).thenReturn(true);

        mockedAccessibilityNodes[1] = mock(AccessibilityNodeInfo.class);

        when(mockedAccessibilityNodes[1].getClassName()).thenReturn("ImageView");
        when(mockedAccessibilityNodes[1].isClickable()).thenReturn(true);
        when(mockedAccessibilityNodes[1].isVisibleToUser()).thenReturn(true);

        mockedAccessibilityNodes[2] = mock(AccessibilityNodeInfo.class);

        when(mockedAccessibilityNodes[2].getClassName()).thenReturn("Button");
        when(mockedAccessibilityNodes[2].isClickable()).thenReturn(true);
        when(mockedAccessibilityNodes[2].isVisibleToUser()).thenReturn(true);

        mockedAccessibilityNodes[3] = mock(AccessibilityNodeInfo.class);

        when(mockedAccessibilityNodes[3].getClassName()).thenReturn("ImageView");
        when(mockedAccessibilityNodes[3].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[3].isVisibleToUser()).thenReturn(false);

        mockedAccessibilityNodes[4] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[4].getClassName()).thenReturn("ViewGroup");
        when(mockedAccessibilityNodes[4].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[4].isVisibleToUser()).thenReturn(true);

        mockedAccessibilityNodes[5] = mock(AccessibilityNodeInfo.class);
        when(mockedAccessibilityNodes[5].getClassName()).thenReturn("ImageView");
        when(mockedAccessibilityNodes[5].isFocusable()).thenReturn(true);
        when(mockedAccessibilityNodes[5].isVisibleToUser()).thenReturn(true);

        when(mockedNodeInfoRoot.getChildCount()).thenReturn(3);
        when(mockedNodeInfoRoot.getChild(0)).thenReturn(mockedAccessibilityNodes[0]);
        when(mockedNodeInfoRoot.getChild(1)).thenReturn(mockedAccessibilityNodes[1]);
        when(mockedNodeInfoRoot.getChild(2)).thenReturn(mockedAccessibilityNodes[2]);

        when(mockedAccessibilityNodes[0].getChildCount()).thenReturn(2);
        when(mockedAccessibilityNodes[0].getChild(0)).thenReturn(mockedAccessibilityNodes[3]);
        when(mockedAccessibilityNodes[0].getChild(1)).thenReturn(mockedAccessibilityNodes[4]);

        when(mockedAccessibilityNodes[2].getChildCount()).thenReturn(2);
        when(mockedAccessibilityNodes[2].getChild(1)).thenReturn(mockedAccessibilityNodes[5]);

        when(mockedAccessibilityNodes[1].getChildCount()).thenReturn(0);
        when(mockedAccessibilityNodes[3].getChildCount()).thenReturn(0);
        when(mockedAccessibilityNodes[4].getChildCount()).thenReturn(0);
        when(mockedAccessibilityNodes[5].getChildCount()).thenReturn(0);

        when(mockedAccessibilityNodes[0].getParent()).thenReturn(mockedNodeInfoRoot);
        when(mockedAccessibilityNodes[1].getParent()).thenReturn(mockedNodeInfoRoot);
        when(mockedAccessibilityNodes[2].getParent()).thenReturn(mockedNodeInfoRoot);

        when(mockedAccessibilityNodes[3].getParent()).thenReturn(mockedAccessibilityNodes[0]);
        when(mockedAccessibilityNodes[4].getParent()).thenReturn(mockedAccessibilityNodes[0]);
        when(mockedAccessibilityNodes[5].getParent()).thenReturn(mockedAccessibilityNodes[2]);
    }

    @Test
    public void testWhenSelectorAndPathMatch() {
        String className = "ImageView";
        boolean isFocusable = true;
        selector = new UiElementSelector();
        selector.addSelectionAttribute(CssAttribute.CLASS_NAME, className);
        selector.addSelectionAttribute(CssAttribute.FOCUSABLE, isFocusable);
        when(mockedMatcher.match(eq(selector), eq(mockedAccessibilityNodes[5]))).thenReturn(true);

        Integer[] path = {2, 1};
        AccessibilityElement element = new AccessibilityElement();
        element.setClassName(className);
        element.setFocusable(isFocusable);
        element.setPath(joinPathIndexes(Arrays.asList(path)));

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = isFocusable;
        assertEquals(WRONG_ELEMENT_FOUND_MESSAGE,
                     mockedAccessibilityNodes[5],
                     traverser.isElementExisting(element, mockedMatcher, selector, visibleOnly));
    }

    @Test
    public void testWhenSelectorMissmatches() {
        String className = "ImageView";
        boolean isFocusable = false;
        selector = new UiElementSelector();
        selector.addSelectionAttribute(CssAttribute.CLASS_NAME, className);
        selector.addSelectionAttribute(CssAttribute.FOCUSABLE, isFocusable);
        when(mockedMatcher.match(eq(selector), eq(mockedAccessibilityNodes[5]))).thenReturn(false);

        Integer[] path = {2, 1};
        AccessibilityElement element = new AccessibilityElement();
        element.setClassName(className);
        element.setFocusable(!isFocusable);
        element.setPath(joinPathIndexes(Arrays.asList(path)));

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertNull(UNEXPECTED_RESULT_MESSAGE,
                   traverser.isElementExisting(element, mockedMatcher, selector, visibleOnly));
    }

    @Test
    public void testWhenPathDoesNotExist() {
        String className = "ImageView";
        boolean isFocusable = true;
        selector = new UiElementSelector();
        selector.addSelectionAttribute(CssAttribute.CLASS_NAME, className);
        selector.addSelectionAttribute(CssAttribute.FOCUSABLE, isFocusable);
        when(mockedMatcher.match(eq(selector), eq(mockedAccessibilityNodes[5]))).thenReturn(true);

        Integer[] path = {2, 1, 2};
        AccessibilityElement element = new AccessibilityElement();
        element.setClassName(className);
        element.setFocusable(isFocusable);
        element.setPath(joinPathIndexes(Arrays.asList(path)));

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertNull(UNEXPECTED_RESULT_MESSAGE,
                   traverser.isElementExisting(element, mockedMatcher, selector, visibleOnly));
    }

    @Test
    public void testWhenRootMatches() {
        String className = "RelativeLayout";
        boolean isFocusable = true;
        selector = new UiElementSelector();
        selector.addSelectionAttribute(CssAttribute.CLASS_NAME, className);
        selector.addSelectionAttribute(CssAttribute.FOCUSABLE, isFocusable);
        when(mockedMatcher.match(eq(selector), eq(mockedNodeInfoRoot))).thenReturn(true);

        AccessibilityElement element = new AccessibilityElement();
        element.setClassName(className);
        element.setFocusable(isFocusable);
        element.setPath(joinPathIndexes(new ArrayList<Integer>()));

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertEquals(WRONG_ELEMENT_FOUND_MESSAGE,
                     mockedNodeInfoRoot,
                     traverser.isElementExisting(element, mockedMatcher, selector, visibleOnly));
    }

    @Test
    public void testWhenNotVisbleElementMatches() {
        String className = "ImageView";
        boolean isFocusable = true;
        selector = new UiElementSelector();
        selector.addSelectionAttribute(CssAttribute.CLASS_NAME, className);
        selector.addSelectionAttribute(CssAttribute.FOCUSABLE, isFocusable);
        when(mockedMatcher.match(eq(selector), eq(mockedAccessibilityNodes[3]))).thenReturn(true);

        Integer[] path = {0, 0};
        AccessibilityElement element = new AccessibilityElement();
        element.setClassName(className);
        element.setFocusable(isFocusable);
        element.setPath(joinPathIndexes(Arrays.asList(path)));

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertNull(UNEXPECTED_RESULT_MESSAGE,
                   traverser.isElementExisting(element, mockedMatcher, selector, visibleOnly));
    }

    @Test
    public void testWhenNonVisibleElementAreTraversed() {
        String className = "ImageView";
        boolean isFocusable = true;
        selector = new UiElementSelector();
        selector.addSelectionAttribute(CssAttribute.CLASS_NAME, className);
        selector.addSelectionAttribute(CssAttribute.FOCUSABLE, isFocusable);
        when(mockedMatcher.match(eq(selector), eq(mockedAccessibilityNodes[3]))).thenReturn(true);

        Integer[] path = {0, 0};
        AccessibilityElement element = new AccessibilityElement();
        element.setClassName(className);
        element.setFocusable(isFocusable);
        element.setPath(joinPathIndexes(Arrays.asList(path)));

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = false;
        assertEquals(WRONG_ELEMENT_FOUND_MESSAGE,
                     mockedAccessibilityNodes[3],
                     traverser.isElementExisting(element, mockedMatcher, selector, visibleOnly));
    }

    private String joinPathIndexes(List<Integer> pathIndexes) {
        Iterator<Integer> pathIterator = pathIndexes.iterator();

        if (!pathIterator.hasNext()) {
            return "";
        }

        StringBuilder path = new StringBuilder(pathIterator.next().toString());

        while (pathIterator.hasNext()) {
            path.append(AccessibilityElementBuilder.PATH_SEPARATOR).append(pathIterator.next());
        }

        return path.toString();
    }
}
