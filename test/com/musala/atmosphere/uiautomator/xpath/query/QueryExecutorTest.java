package com.musala.atmosphere.uiautomator.xpath.query;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.jxpath.JXPathContext;
import org.junit.BeforeClass;
import org.junit.Test;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;
import com.musala.atmosphere.uiautomator.resources.TestResources;
import com.musala.atmosphere.uiautomator.xpath.tree.UiNode;
import com.musala.atmosphere.uiautomator.xpath.tree.UiRoot;

import android.view.accessibility.AccessibilityNodeInfo;

/**
 * Tests the {@link QueryExecutor} functionalities.
 *
 * @author yordan.petrov
 *
 */
public class QueryExecutorTest {
    private static final String COUNT_ERROR_MESSAGE = "The number of resulting elements did not match the expected number.";

    private static final String CLASS_ERROR_MESSAGE = "The class of the first found element did not match the expected result.";

    private static final String XPATH_QUERY_RESOURCE_ID = "//*[@resourceId='example/:root']";

    private static final String XPATH_QUERY_ALL_ELEMENTS = "//*";

    private static final String XPATH_QUERY_INDEX = "//*[@index=2]";

    private static AccessibilityNodeInfo[] mockedNodes;

    private static QueryExecutor executor;

    @BeforeClass
    public static void setUp() {
        mockedNodes = TestResources.getMockedHierarchy();

        UiNode firstNode = new MockedAccessibilityNodeInfoWrapper(mockedNodes[0]);
        UiRoot uiRoot = new MockedAccessibilityNodeInfoRoot(firstNode);

        JXPathContext context = JXPathContext.newContext(uiRoot);
        executor = new QueryExecutor(context);
    }

    @Test
    public void testSuccessfullyGetAllElements() {
        List<AccessibilityElement> obtainedElements = executor.execute(XPATH_QUERY_ALL_ELEMENTS, false);
        assertEquals(COUNT_ERROR_MESSAGE, 7, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetAllVisibleElements() {
        List<AccessibilityElement> obtainedElements = executor.execute(XPATH_QUERY_ALL_ELEMENTS, true);
        assertEquals(COUNT_ERROR_MESSAGE, 6, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByClass() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@className='Button']", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[3].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByText() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@text='Click me!']", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[3].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByIndex() {
        List<AccessibilityElement> obtainedElements = executor.execute(XPATH_QUERY_INDEX, true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[3].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByPackage() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@package='com.example.coolstory2']", true);
        assertEquals(COUNT_ERROR_MESSAGE, 2, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[2].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByContentDescriptor() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@contentDesc='Really cool pic']", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[2].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByCheckableAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@checkable=false()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 6, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByCheckedAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@checked=false()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 6, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByClickableAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@clickable=true()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 2, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[1].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByEnabledAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@enabled=false()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 6, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByFocusableAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@focusable=true()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 4, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByFocusedAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@focused=true()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[1].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByScrollableAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@scrollable=true()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByLongClickableAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@longClickable=true()]", false);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[5].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByPasswordAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@password=true()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[5].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsBySelectedAttribute() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@selected=true()]", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[5].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByBounds() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@bounds='[0,0][100,100]']", true);
        assertEquals(COUNT_ERROR_MESSAGE, 6, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetElementsByResourceId() {
        List<AccessibilityElement> obtainedElements = executor.execute("//*[@resourceId='someId']", true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, obtainedElements.size());

        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[3].getClassName(), firstResult.getClassName());
    }

    @Test
    public void testSuccessfullyGetAllChildElements() {
        List<AccessibilityElement> obtainedElements = executor.execute(XPATH_QUERY_RESOURCE_ID, true);
        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());

        List<AccessibilityElement> children = executor.executeOnLocalRoot(firstResult, XPATH_QUERY_ALL_ELEMENTS, true);
        assertEquals(COUNT_ERROR_MESSAGE, 5, children.size());

        AccessibilityElement firstChild = children.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[1].getClassName(), firstChild.getClassName());
    }

    @Test
    public void testSuccessfullyGetSpecificChildElements() {
        List<AccessibilityElement> obtainedElements = executor.execute(XPATH_QUERY_RESOURCE_ID, true);
        AccessibilityElement firstResult = obtainedElements.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[0].getClassName(), firstResult.getClassName());

        List<AccessibilityElement> children = executor.executeOnLocalRoot(firstResult, XPATH_QUERY_INDEX, true);
        assertEquals(COUNT_ERROR_MESSAGE, 1, children.size());

        AccessibilityElement firstChild = children.get(0);
        assertEquals(CLASS_ERROR_MESSAGE, mockedNodes[3].getClassName(), firstChild.getClassName());
    }
}
