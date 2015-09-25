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

import com.musala.atmosphere.commons.ui.UiElementPropertiesContainer;
import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.commons.ui.tree.matcher.UiElementMatcher;
import com.musala.atmosphere.uiautomator.resources.TestResources;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 *
 * @author filareta.yordanova
 *
 */
public class GetCorrespondingAccessibilityNodeInfoTest {
    private static final String WRONG_ELEMENT_FOUND_MESSAGE = "The accessibility node that was found does not match the expected one.";

    private static final String UNEXPECTED_RESULT_MESSAGE = "Unexpected match for the given accessibility element was found.";

    private static AccessibilityNodeInfo mockedNodeInfoRoot;

    private static AccessibilityNodeInfo[] mockedAccessibilityNodes;

    private static UiElementMatcher<UiElementPropertiesContainer> mockedMatcher;

    @BeforeClass
    public static void setUp() {
        mockedMatcher = mock(UiElementMatcher.class);

        mockedAccessibilityNodes = TestResources.getMockedHierarchy();
        mockedNodeInfoRoot = mockedAccessibilityNodes[0];
    }

    @Test
    public void testWhenPropertiesAndPathMatch() {
        Integer[] path = {2, 1};
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(joinPathIndexes(Arrays.asList(path)));
        when(mockedMatcher.match(eq(element), eq(mockedAccessibilityNodes[6]))).thenReturn(true);

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertEquals(WRONG_ELEMENT_FOUND_MESSAGE,
                     mockedAccessibilityNodes[6],
                     traverser.getCorrespondingAccessibilityNodeInfo(element, mockedMatcher, visibleOnly));
    }

    @Test
    public void testWhenPropertiesMissmatch() {
        Integer[] path = {2, 1};
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(joinPathIndexes(Arrays.asList(path)));
        when(mockedMatcher.match(eq(element), eq(mockedAccessibilityNodes[6]))).thenReturn(false);

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertNull(UNEXPECTED_RESULT_MESSAGE,
                   traverser.getCorrespondingAccessibilityNodeInfo(element, mockedMatcher, visibleOnly));
    }

    @Test
    public void testWhenPathDoesNotExist() {
        Integer[] path = {2, 1, 2};
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(joinPathIndexes(Arrays.asList(path)));
        when(mockedMatcher.match(eq(element), eq(mockedAccessibilityNodes[6]))).thenReturn(true);

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertNull(UNEXPECTED_RESULT_MESSAGE,
                   traverser.getCorrespondingAccessibilityNodeInfo(element, mockedMatcher, visibleOnly));
    }

    @Test
    public void testWhenRootMatches() {
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(joinPathIndexes(new ArrayList<Integer>()));
        when(mockedMatcher.match(eq(element), eq(mockedNodeInfoRoot))).thenReturn(true);

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertEquals(WRONG_ELEMENT_FOUND_MESSAGE,
                     mockedNodeInfoRoot,
                     traverser.getCorrespondingAccessibilityNodeInfo(element, mockedMatcher, visibleOnly));
    }

    @Test
    public void testWhenNotVisbleElementMatches() {
        Integer[] path = {0, 0};
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(joinPathIndexes(Arrays.asList(path)));
        when(mockedMatcher.match(eq(element), eq(mockedAccessibilityNodes[4]))).thenReturn(true);

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = true;
        assertNull(UNEXPECTED_RESULT_MESSAGE,
                   traverser.getCorrespondingAccessibilityNodeInfo(element, mockedMatcher, visibleOnly));
    }

    @Test
    public void testWhenNonVisibleElementAreTraversed() {
        Integer[] path = {0, 0};
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(joinPathIndexes(Arrays.asList(path)));
        when(mockedMatcher.match(eq(element), eq(mockedAccessibilityNodes[4]))).thenReturn(true);

        AccessibilityNodeTraverser traverser = new AccessibilityNodeTraverser(mockedNodeInfoRoot, "");
        boolean visibleOnly = false;
        assertEquals(WRONG_ELEMENT_FOUND_MESSAGE,
                     mockedAccessibilityNodes[4],
                     traverser.getCorrespondingAccessibilityNodeInfo(element, mockedMatcher, visibleOnly));
    }

    private String joinPathIndexes(List<Integer> pathIndexes) {
        Iterator<Integer> pathIterator = pathIndexes.iterator();

        if (!pathIterator.hasNext()) {
            return "";
        }

        StringBuilder path = new StringBuilder(pathIterator.next().toString());

        while (pathIterator.hasNext()) {
            path.append(AccessibilityElement.PATH_SEPARATOR).append(pathIterator.next());
        }

        return path.toString();
    }
}