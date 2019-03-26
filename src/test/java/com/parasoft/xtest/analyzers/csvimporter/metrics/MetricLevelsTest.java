/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer.metrics
 * Class: MetricLevelsTest.java
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
 */
public class MetricLevelsTest {

    @Test
    public void testIsSubmitableLevel() {
        assertTrue(MetricLevels.isSubmitableLevel("method"));
        assertFalse(MetricLevels.isSubmitableLevel("namespace"));
        assertTrue(MetricLevels.isSubmitableLevel("type"));
        assertFalse(MetricLevels.isSubmitableLevel("module"));
        assertTrue(MetricLevels.isSubmitableLevel("resource"));

        assertTrue(MetricLevels.isSubmitableLevel("METHOD"));
        assertFalse(MetricLevels.isSubmitableLevel("NAMESPACE"));
        assertTrue(MetricLevels.isSubmitableLevel("TYPE"));
        assertFalse(MetricLevels.isSubmitableLevel("MODULE"));
        assertTrue(MetricLevels.isSubmitableLevel("RESOURCE"));
        
        assertFalse(MetricLevels.isSubmitableLevel("HELLO"));
        assertFalse(MetricLevels.isSubmitableLevel(""));
        assertFalse(MetricLevels.isSubmitableLevel(null));
    }

    @Test
    public void testIsValueValid() {
        assertTrue(MetricLevels.isValueValid("MODULE"));
        assertTrue(MetricLevels.isValueValid("NAMESPACE"));
        assertTrue(MetricLevels.isValueValid("MODULE"));
        assertTrue(MetricLevels.isValueValid("type"));
        assertTrue(MetricLevels.isValueValid("resource"));
        
        assertFalse(MetricLevels.isValueValid("hello"));
        assertFalse(MetricLevels.isValueValid(""));
        assertFalse(MetricLevels.isValueValid(null));
    }

    @Test
    public void testGetMetricLevel() {
        assertEquals(MetricLevels.METHOD, MetricLevels.getMetricLevel("method"));
        assertEquals(MetricLevels.METHOD, MetricLevels.getMetricLevel("METHOD"));

        assertNull(MetricLevels.getMetricLevel("hello"));
        assertNull(MetricLevels.getMetricLevel(""));
        assertNull(MetricLevels.getMetricLevel(null));
    }
}
