package com.musala.atmosphere.uiautomator.xpath.accessibility;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.musala.atmosphere.commons.ui.tree.AccessibilityElement;

/**
 * Test the {@link AccessibilityElementXPathExtractor} functionalities.
 *
 * @author yordan.petrov
 *
 */
public class AccessibilityElementXPathExtractorTest {
    private static final String PATH_MISMATCH_MESSAGE = "The extracted path did not match the expected one.";

    @Test
    public void testExtractRootPath() {
        String initialPath = "";
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(initialPath);

        String expectedPath = "/node";
        assertEquals(PATH_MISMATCH_MESSAGE, expectedPath, AccessibilityElementXPathExtractor.extract(element));
    }

    @Test
    public void testExtractRootChildPath() {
        String initialPath = "0, ";
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(initialPath);

        String expectedPath = "/node/node[1]";
        assertEquals(PATH_MISMATCH_MESSAGE, expectedPath, AccessibilityElementXPathExtractor.extract(element));
    }

    @Test
    public void testExtractLongPath() {
        String initialPath = "0, 1, 0, 5";
        AccessibilityElement element = new AccessibilityElement();
        element.setPath(initialPath);

        String expectedPath = "/node/node[1]/node[2]/node[1]/node[6]";
        assertEquals(PATH_MISMATCH_MESSAGE, expectedPath, AccessibilityElementXPathExtractor.extract(element));
    }
}
