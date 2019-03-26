/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer
 * Class: CSVReportTypeTest.java
 *
 * Comments:
 *
 * (C) Copyright ParaSoft Corporation 1998 - 2016.  All rights reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF ParaSoft
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 *
 */
package com.parasoft.xtest.analyzers.csvanalyzer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author sang
 * @req 13122
 * @req 13233
 *
 */
public class CSVReportTypeTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFineType() {
        CSVReportType type = CSVReportType.findType(CSVReportType.METRICS.getValue());
        assertEquals(CSVReportType.METRICS, type);
        type = CSVReportType.findType(CSVReportType.STATIC_ANALYSIS.getValue());
        assertEquals(CSVReportType.STATIC_ANALYSIS, type);
        type = CSVReportType.findType("foo");
        assertNull(type);
    }
}
