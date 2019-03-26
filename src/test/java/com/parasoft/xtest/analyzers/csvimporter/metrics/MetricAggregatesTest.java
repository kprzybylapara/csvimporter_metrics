/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer.metrics
 * Class: MetricAggregatesTest.java
 *
 * Comments:
 *
 * (C) Copyright ParaSoft Corporation 1998 - 2016.  All rights reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF ParaSoft
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 *
 */
package com.parasoft.xtest.analyzers.csvanalyzer.metrics;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author sang
 * @req 13233
 * @pr 120588
 *
 */
public class MetricAggregatesTest {

     @Test
    public void testIsValueValid() {
        assertTrue(MetricAggregates.isValueValid("SUM"));
        assertTrue(MetricAggregates.isValueValid("AVG"));
        assertTrue(MetricAggregates.isValueValid("MIN"));
        assertTrue(MetricAggregates.isValueValid("MAX"));
        assertTrue(MetricAggregates.isValueValid("avg"));
        
        assertFalse(MetricAggregates.isValueValid("hello"));
        assertFalse(MetricAggregates.isValueValid(""));
        assertFalse(MetricAggregates.isValueValid(null));
    }

    @Test
    public void testGetMetricLevel() {
        assertEquals(MetricAggregates.SUM, MetricAggregates.getMetricLevel("sum"));
        assertEquals(MetricAggregates.SUM, MetricAggregates.getMetricLevel("SUM"));

        assertNull(MetricAggregates.getMetricLevel("hello"));
        assertNull(MetricAggregates.getMetricLevel(""));
        assertNull(MetricAggregates.getMetricLevel(null));
    }
}
