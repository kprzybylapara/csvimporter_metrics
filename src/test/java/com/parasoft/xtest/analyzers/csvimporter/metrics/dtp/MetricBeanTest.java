/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp
 * Class: MetricBeanTest.java
 *
 * Comments:
 *
 * (C) Copyright ParaSoft Corporation 1998 - 2016.  All rights reserved.
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF ParaSoft
 * The copyright notice above does not evidence any
 * actual or intended publication of such source code.
 *
 */
package com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.parasoft.xtest.analyzers.csvanalyzer.metrics.InvalidMetricException;

/**
 * @req 13233
 * @author sang
 *
 */
public class MetricBeanTest {

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

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#setLevels(java.lang.String)}.
     */
    @Test
    public void testSetLevelsString() throws InvalidMetricException {
        String testString = "resource;module;namespace;type;method";
        MetricBean bean = new MetricBean();
        bean.setLevels(testString);
        List<String> levelList = bean.getLevels();
        assertNotNull(levelList);
        assertEquals(5, levelList.size());
        assertTrue(levelList.contains("type"));
        
        testString = "resource; ; namespace; ;method";
        bean = new MetricBean();
        bean.setLevels(testString);
        levelList = bean.getLevels();
        assertNotNull(levelList);
        assertEquals(3, levelList.size());
        assertTrue(levelList.contains("namespace"));
        

        testString = "resource";
        bean = new MetricBean();
        bean.setLevels(testString);
        levelList = bean.getLevels();
        assertNotNull(levelList);
        assertEquals(1, levelList.size());
        assertTrue(levelList.contains("resource"));
        assertFalse(levelList.contains("namespace"));

    }

    @Test(expected = InvalidMetricException.class)
    public void testSetLevelIsStringException () throws InvalidMetricException {
        String testString = "master;namespace";
        MetricBean bean = new MetricBean();
        bean.setLevels(testString);
    }

    @Test(expected = InvalidMetricException.class)
    public void testSetLevelsStringException2() throws InvalidMetricException {
        String testString = "resource:module:namespace:type:method";
        MetricBean bean = new MetricBean();
        bean.setLevels(testString);
    }
    
    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#setLevels(java.lang.String)}.
     * negative test that user uses colon as separator
     * @throws InvalidMetricException
     */
    @Test
    public void testSetLevelsStringBad() throws InvalidMetricException {

        String testString = null;
        MetricBean bean = new MetricBean();
        assertNull(bean.getLevels());
        bean.setLevels(testString);
        assertNotNull(bean.getLevels());
        assertEquals(0, bean.getLevels().size());
        
        testString = " ";
        bean = new MetricBean();
        assertNull(bean.getLevels());
        bean.setLevels(testString);
        assertNotNull(bean.getLevels());
        assertEquals(0, bean.getLevels().size());
    }

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#setLevels(java.lang.String)}.
     */
    @Test()
    public void testSetAggregatesString() throws InvalidMetricException {
        String testString = "MIN;MAX;AVG;SUM";
        MetricBean bean = new MetricBean();
        bean.setAggregates(testString);
    }
        
    @Test()
    public void testSetAggregatesString2() throws InvalidMetricException {
        String testString = "MIN; ; MAX; ;SUM";
        MetricBean bean = new MetricBean();
        bean.setAggregates(testString);
        List<String> aggList = bean.getAggregates();
        assertEquals(3, aggList.size());
    }
    
    @Test()
    public void testSetAggregatesString3() throws InvalidMetricException {

        String testString = "MAX";
        MetricBean bean = new MetricBean();
        bean.setAggregates(testString);
        
        List<String> levelList = bean.getAggregates();
        assertNotNull(levelList);
        assertEquals(1, levelList.size());
        assertTrue(levelList.contains("MAX"));
        assertFalse(levelList.contains("SUM"));

    }

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#setLevels(java.lang.String)}.
     * negative test that user uses colon as separator
     */
    @Test(expected=InvalidMetricException.class)
    public void testSetAggregatesStringBad() throws InvalidMetricException {
        String testString = "SUM:MIN:AVG";
        MetricBean bean = new MetricBean();
        bean.setAggregates(testString);
    }
    
    @Test(expected=InvalidMetricException.class)
    public void testSetAggregatesStringBad2() throws InvalidMetricException {
        String testString = null;
        MetricBean bean = new MetricBean();
        assertNull(bean.getAggregates());
        bean.setAggregates(testString);
        assertNotNull(bean.getAggregates());
        assertEquals(0, bean.getAggregates().size());
    }        
    
    @Test()
    public void testSetAggregatesStringBad3() throws InvalidMetricException {
        String testString = " ";
        MetricBean bean = new MetricBean();
        assertNull(bean.getAggregates());
        
        bean.setAggregates(testString);
        assertNotNull(bean.getAggregates());
        assertEquals(0, bean.getAggregates().size());
    }

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#AddALevelToLevels(java.lang.String)}.
     */
    @Test(expected=InvalidMetricException.class)
    public void testAddALevelToLevels() throws InvalidMetricException{
        MetricBean bean = new MetricBean();
        assertNull(bean.getLevels());
        bean.AddALevelToLevels("LEVEL");
        assertNotNull(bean.getLevels());
        assertFalse(bean.getLevels().isEmpty());
        assertTrue(bean.getLevels().contains("LEVEL"));
    }

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#AddAnAggregateToAggregates(java.lang.String)}.
     */
    @Test
    public void testAddAnAggregateToAggregates() throws InvalidMetricException {
        MetricBean bean = new MetricBean();
        assertNull(bean.getAggregates());
        
        bean.AddAnAggregateToAggregates("SUM");
        assertNotNull(bean.getAggregates());
        assertFalse(bean.getAggregates().isEmpty());
        assertTrue(bean.getAggregates().contains("SUM"));
    }

    /**
     * @req 13233
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#setNames(java.lang.String)}.
     */
    @Test
    public void testSetNamesString() {
        String testString = "en=hello;ja=foo;kr=bar;cn=boo";
        int expectedSize= 4;
        String enExpectedValue = "hello";
        String krExpectedValue = "bar";
        
        MetricBean bean = new MetricBean();
        assertNull(bean.getNames());
        bean.setNames(testString);
        Map<String, String> namesMap = bean.getNames();
        assertNotNull(namesMap);
        assertEquals(expectedSize, namesMap.size());
        assertEquals(enExpectedValue, namesMap.get("en"));
        assertEquals(krExpectedValue, namesMap.get("kr"));
    }
    /**
     * @req 13233
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#setNames(java.lang.String)}.
     * Negative test - test if user use colon separator instead of semi colon
     */
    @Test
    public void testSetNamesStringBad() {
        String testString = "en=hello:ja=foo:kr=bar:cn=boo";
        int expectedSize= 0;
        
        MetricBean bean = new MetricBean();
        assertNull(bean.getNames());
        bean.setNames(testString);
        Map<String, String> namesMap = bean.getNames();
        assertNotNull(namesMap);
        assertEquals(expectedSize, namesMap.size());
    }

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean#setNames(java.lang.String)}.
     * Negative test - test if user use colon instead of equal
     */
    @Test
    public void testSetNamesStringBad2() {
        String testString = "en:hello;ja:foo;kr:bar;cn:boo";
        int expectedSize= 0;
        
        MetricBean bean = new MetricBean();
        assertNull(bean.getNames());
        bean.setNames(testString);
        Map<String, String> namesMap = bean.getNames();
        assertNotNull(namesMap);
        assertEquals(expectedSize, namesMap.size());
    }
    
}
