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

package com.musala.atmosphere.uiautomator.xpath.query;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.musala.atmosphere.uiautomator.xpath.query.QueryFormatter;

/**
 * Tests that cover the {@link QueryFormatter#format(String)} logic. It should format the queries so that only nodes
 * with '@node' attribute are selected on each step.
 *
 * @author yordan.petrov
 *
 */
public class QueryFormatterTest {
    private static final String FORMAT_MISSMATCH_MESSAGE = "The formatted query did not match the expected result.";

    @Test
    public void testFormatSimpleQuery() {
        String query = "//*";
        String expectedQuery = "//*[@node]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, false));
    }

    @Test
    public void testFormatNestedQuery() {
        String query = "//*/*";
        String expectedQuery = "//*[@node]/*[@node]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, false));
    }

    @Test
    public void testFormatOmmitsEscapedSeparatorOnSimpleQuery() {
        String query = "//*[className='as/d']";
        String expectedQuery = "//*[className='as/d'][@node]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, false));
    }

    @Test
    public void testFormatOmmitsQuottedSeparatorOnNestedQuery() {
        String query = "//*[className='as/d']/*[className='asafa/ad']";
        String expectedQuery = "//*[className='as/d'][@node]/*[className='asafa/ad'][@node]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, false));
    }

    @Test
    public void testFormatOmmitsSeparatorinBracketsOnNestedQuery() {
        String query = "//*[className/='asd']/*[className/='asafaad']";
        String expectedQuery = "//*[className/='asd'][@node]/*[className/='asafaad'][@node]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, false));
    }

    @Test
    public void testFormatLocalContextQuery() {
        String query = "./*";
        String expectedQuery = ".[@node]/*[@node]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, false));
    }

    @Test
    public void testFormatSimpleQueryWithVisibleOnly() {
        String query = "//*";
        String expectedQuery = "//*[@node][@visible=true()]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, true));
    }

    @Test
    public void testFormatComplexQueryWithVisibleOnly() {
        String query = "//*[className='as/d']/*[className='asafa/ad']";
        String expectedQuery = "//*[className='as/d'][@node][@visible=true()]/*[className='asafa/ad'][@node][@visible=true()]";
        assertEquals(FORMAT_MISSMATCH_MESSAGE, expectedQuery, QueryFormatter.format(query, true));
    }

}
