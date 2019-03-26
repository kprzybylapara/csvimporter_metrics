/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer
 * Class: CSVAnalyzerTest.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.parasoft.xtest.analyzers.common.AnalyzerException;
import com.parasoft.xtest.analyzers.common.AnalyzerUtils;
import com.parasoft.xtest.analyzers.common.ContextImpl;
import com.parasoft.xtest.analyzers.common.ICommonConsts;
import com.parasoft.xtest.analyzers.common.testconfiguration.AnalyzerTestConfiguration;
import com.parasoft.xtest.analyzers.csvanalyzer.metrics.MetricsReportProcess;
import com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAReportProcess;
import com.parasoft.xtest.common.api.ITestableInput;
import com.parasoft.xtest.common.api.console.IConsole;
import com.parasoft.xtest.common.api.progress.IProgressMonitor;
import com.parasoft.xtest.common.dtp.DtpUtil;
import com.parasoft.xtest.results.api.IResult;
import com.parasoft.xtest.results.api.IResultsServiceContext;
import com.parasoft.xtest.services.api.IParasoftServiceContext;

/**
 * CSV Analyzer Testing.
 * @author sang
 * @req 13122
 * @req 13233
 *
 */
public class CSVAnalyzerTest {

    private IResultsServiceContext mockContext = mock(IResultsServiceContext.class);
    private IParasoftServiceContext dtpContext = null;

    private IConsole mockConsole = mock(IConsole.class);
    private IProgressMonitor mockMonitor = mock(IProgressMonitor.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Properties dtpSettings = DtpUtil.createConnectionSettings("skunk-dev2.parasoft.com", "8443", "admin", "626079fd67", "PIE");
        dtpContext = new ContextImpl(dtpSettings, new AnalyzerTestConfiguration());
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVAnalyzer#isEnabled(com.parasoft.xtest.configuration.api.ITestConfigurationServiceContext, com.parasoft.xtest.common.api.console.IConsole)}.
     */
    @Test
    public void testIsEnabled() {
        CSVAnalyzer analyzer = new CSVAnalyzer();
        Properties props = new Properties();
        props.setProperty(CSVAnalyzer.ENABLED_KEY, Boolean.TRUE.toString());
        props.setProperty(CSVAnalyzer.REPORT_TYPE_KEY, CSVReportType.METRICS.getValue());
        when(mockContext.getPreferences()).thenReturn(props);
        assertTrue(analyzer.isEnabled(mockContext, mockConsole));        
        // run once again to check cached value (maximize coverage)
        assertTrue(analyzer.isEnabled(mockContext, mockConsole));        
    }
    
    /**
     * Analyzer is not enabled
     */
    @Test
    public void testIsEnabledBad1() {
        CSVAnalyzer analyzer = new CSVAnalyzer();
        Properties props = new Properties();
        props.setProperty(CSVAnalyzer.ENABLED_KEY, Boolean.FALSE.toString());
        props.setProperty(CSVAnalyzer.REPORT_TYPE_KEY, CSVReportType.METRICS.getValue());
        when(mockContext.getPreferences()).thenReturn(props);
        assertFalse(analyzer.isEnabled(mockContext, mockConsole));
        assertFalse(analyzer.isEnabled(null, null));
    }

    /**
     * analyzer is enabled but CSVAnalyzer.REPORT_TYPE_KEY is missing
     */
    @Test
    public void testIsEnabledBad2() {
        CSVAnalyzer analyzer = new CSVAnalyzer();
        Properties props = new Properties();
        props.setProperty(CSVAnalyzer.ENABLED_KEY, Boolean.TRUE.toString());
        //props.setProperty(CSVAnalyzer.REPORT_TYPE_KEY, CSVReportType.METRICS.getValue());
        when(mockContext.getPreferences()).thenReturn(props);
        assertFalse(analyzer.isEnabled(mockContext, mockConsole));
    }

    /**
     * analyzer is enabled but CSVAnalyzer.REPORT_TYPE_KEY is incorrect
     */
    @Test
    public void testIsEnabledBad3() {
        CSVAnalyzer analyzer = new CSVAnalyzer();
        Properties props = new Properties();
        props.setProperty(CSVAnalyzer.ENABLED_KEY, Boolean.TRUE.toString());
        props.setProperty(CSVAnalyzer.REPORT_TYPE_KEY, "foo");
        when(mockContext.getPreferences()).thenReturn(props);
        assertFalse(analyzer.isEnabled(mockContext, mockConsole));
    }

    /**
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVAnalyzer#getReportProcessor(com.parasoft.xtest.analyzers.csvanalyzer.CSVReportType)}.
     */
    @Test
    public void testGetReportProcessor() {
        when(mockContext.getPreferences()).thenReturn(dtpContext.getPreferences());
        IReportProcess processor = CSVAnalyzer.getReportProcessor(CSVReportType.METRICS, mockContext);
        assertNotNull(processor);
        assertTrue(processor instanceof MetricsReportProcess);
        
        processor = CSVAnalyzer.getReportProcessor(CSVReportType.STATIC_ANALYSIS, mockContext);
        assertNotNull(processor);
        assertTrue(processor instanceof SAReportProcess);
        
        processor = CSVAnalyzer.getReportProcessor(null, null);
        assertNull(processor);
    }

    /**
     * @pr 120551 
     * @throws Exception
     */
    @Test
    public void testGetReportType() throws Exception
    {
        try {
            // Given
            CSVAnalyzer underTest = new CSVAnalyzer();
            Properties properties = new Properties();
            assertNull(underTest.getReportType(properties));
        
            
            // When
            properties.setProperty(ICSVAnalyzerConsts.REPORT_TYPE_KEY, CSVReportType.METRICS.getValue());
            assertEquals(CSVReportType.METRICS, underTest.getReportType(properties));
            
            properties = new Properties();
            System.setProperty(ICSVAnalyzerConsts.REPORT_TYPE_KEY, CSVReportType.METRICS.getValue());
            CSVReportType result = underTest.getReportType(properties);
            assertEquals(CSVReportType.METRICS, result);
            assertNotNull(result);
        } finally {
            System.clearProperty(ICSVAnalyzerConsts.REPORT_TYPE_KEY);
        }
    }

    /**
     * Test all corner cases of process() method to validate if settings are handled correctly and test for Metrics analyzer working all.
     * @req 13233
     * @throws IOException
     */
    @Test
    public void testGetProcess() throws IOException {
        // Given

        try {
            CSVAnalyzer underTest = new CSVAnalyzer();
            Properties properties = new Properties();
            properties.putAll(dtpContext.getPreferences());
            properties.setProperty(ICSVAnalyzerConsts.ENABLED_KEY, Boolean.TRUE.toString());
            when(mockContext.getPreferences()).thenReturn(properties);
            assertNull(underTest.getReportType(properties));
    
            // expect to test null processor
            assertTrue(underTest.process(null, mockContext, mockConsole, mockMonitor).isEmpty());
            
            properties.setProperty(ICSVAnalyzerConsts.REPORT_TYPE_KEY, CSVReportType.METRICS.getValue());
            
            underTest = new CSVAnalyzer();
            assertTrue(underTest.isEnabled(mockContext, mockConsole));
            
            //expect to test null project root
            assertTrue(underTest.process(null, mockContext, mockConsole, mockMonitor).isEmpty());
            
            File dir = new File(".").getCanonicalFile();
            System.setProperty(ICommonConsts.DATA_DIR_KEY, dir.getAbsolutePath());
            
            // expect to test null rule file key
            assertTrue(underTest.process(null, mockContext, mockConsole, mockMonitor).isEmpty());
            
            String fileName = this.getClass().getResource("metrics/metric-rules.csv").getFile();
            System.setProperty(ICommonConsts.RULE_FILE_KEY, fileName);
            
            // expect to test null report file key
            assertTrue(underTest.process(null, mockContext, mockConsole, mockMonitor).isEmpty());
    
            System.setProperty(ICommonConsts.RESULTS_FILE_KEY, this.getClass().getResource("metrics/MetricsReport.csv").getFile());
            System.setProperty(ICommonConsts.RULE_FILE_KEY, this.getClass().getResource("metrics/empty-metric-rules.csv").getFile());
    
            // expect to test null empty rule map
            assertTrue(underTest.process(null, mockContext, mockConsole, mockMonitor).isEmpty());
    
            System.setProperty(ICommonConsts.RULE_FILE_KEY, this.getClass().getResource("metrics/metric-rules.csv").getFile());
    
            assertEquals(4, underTest.getTestableList().size());
            List<IResult> results = underTest.process(new ArrayList<ITestableInput>(), mockContext, mockConsole, mockMonitor);
            assertNotNull(results);
            assertEquals(11, results.size());
        } finally {
            System.clearProperty(ICSVAnalyzerConsts.REPORT_TYPE_KEY);
            System.clearProperty(ICSVAnalyzerConsts.ENABLED_KEY);
            System.clearProperty(ICommonConsts.DATA_DIR_KEY);
            System.clearProperty(ICommonConsts.RESULTS_FILE_KEY);
            System.clearProperty(ICommonConsts.RULE_FILE_KEY);
        }
    }
    /**
     * Test all corner cases of process() method to validate if settings are handled correctly and test for Metrics analyzer working all.
     * @req 13233
     * @throws IOException
     * include a filename not exists and bad rule name
     */
    @Test
    public void testGetProcess2() throws IOException {
        // Given

        try {
            CSVAnalyzer underTest = new CSVAnalyzer();
            Properties properties = new Properties();
            properties.putAll(dtpContext.getPreferences());
            properties.setProperty(ICSVAnalyzerConsts.ENABLED_KEY, Boolean.TRUE.toString());
            when(mockContext.getPreferences()).thenReturn(properties);
            assertNull(underTest.getReportType(properties));
    
            // expect to test null processor
            assertTrue(underTest.process(null, mockContext, mockConsole, mockMonitor).isEmpty());
            
            properties.setProperty(ICSVAnalyzerConsts.REPORT_TYPE_KEY, CSVReportType.METRICS.getValue());
            
            underTest = new CSVAnalyzer();
            assertTrue(underTest.isEnabled(mockContext, mockConsole));
            File dir = new File(".").getCanonicalFile();
            System.setProperty(ICommonConsts.DATA_DIR_KEY, dir.getAbsolutePath());
            String fileName = this.getClass().getResource("metrics/metric-rules.csv").getFile();
            System.setProperty(ICommonConsts.RULE_FILE_KEY, fileName);
            System.setProperty(ICommonConsts.RESULTS_FILE_KEY, this.getClass().getResource("metrics/MetricsReport2.csv").getFile());
            System.setProperty(ICommonConsts.RULE_FILE_KEY, this.getClass().getResource("metrics/metric-rules.csv").getFile());

            assertEquals(4, underTest.getTestableList().size());
            List<IResult> results = underTest.process(new ArrayList<ITestableInput>(), mockContext, mockConsole, mockMonitor);
            assertNotNull(results);
            assertEquals(9, results.size());
            assertEquals(4, underTest.getTestableList().size());
        } finally {
            System.clearProperty(ICSVAnalyzerConsts.REPORT_TYPE_KEY);
            System.clearProperty(ICSVAnalyzerConsts.ENABLED_KEY);
            System.clearProperty(ICommonConsts.DATA_DIR_KEY);
            System.clearProperty(ICommonConsts.RESULTS_FILE_KEY);
            System.clearProperty(ICommonConsts.RULE_FILE_KEY);
        }
    }
    /**
     * test checkFile method for file exist and file not exist
     * @throws IOException
     */
    @Test
    public void testCheckFile() throws IOException, AnalyzerException  {
    	File checkFile = null;
        File dir = new File(".").getCanonicalFile();
        System.setProperty(ICommonConsts.DATA_DIR_KEY, dir.getAbsolutePath());
        try {
            String fileName = "pom.xml";
            System.setProperty(ICommonConsts.RULE_FILE_KEY, fileName);
            checkFile = AnalyzerUtils.checkFileProperty(ICommonConsts.RULE_FILE_KEY, null, System.getProperties());
            assertNotNull(checkFile);
            assertTrue(checkFile.exists());
            
            // file doesn't exist
            fileName = "test/pom.xml";
            System.setProperty(ICommonConsts.RULE_FILE_KEY, fileName);
            try {
                checkFile = AnalyzerUtils.checkFileProperty(ICommonConsts.RULE_FILE_KEY, null, System.getProperties());
            } catch (AnalyzerException e) {
                System.out.println(e.getMessage());
                assertNotNull(e);
                assertEquals("Key rules.file refers to a file or directory (test/pom.xml) that is invalid or not found", e.getMessage());
            }

            // test file exists in other project root than current working directory
            dir = new File("src/test/resources").getCanonicalFile();
            System.setProperty(ICommonConsts.DATA_DIR_KEY, dir.getAbsolutePath());
            
            fileName = "violations.csv";
            System.setProperty(ICommonConsts.RULE_FILE_KEY, fileName);
            
            checkFile = AnalyzerUtils.checkFileProperty(ICommonConsts.RULE_FILE_KEY, dir, System.getProperties());
            assertNotNull(checkFile);

            // file dones't exist
            fileName = "py/violations.csv";
            System.setProperty(ICommonConsts.RULE_FILE_KEY, fileName);
            try {
            checkFile = AnalyzerUtils.checkFileProperty(ICommonConsts.RULE_FILE_KEY, dir, System.getProperties());
            } catch (AnalyzerException e) {
                System.out.println(e.getMessage());
                assertNotNull(e);
                assertEquals("Key rules.file refers to a file or directory (py/violations.csv) that is invalid or not found", e.getMessage());                
            }
        } finally {
            System.clearProperty(ICommonConsts.RULE_FILE_KEY);
            System.clearProperty(ICommonConsts.DATA_DIR_KEY);
            
        }
    }
    
    /**
     * test corner case not covered with other tests
     */
    @Test
    public void testGetTestableList() {
        CSVAnalyzer analyzer = new CSVAnalyzer();
        assertEquals(0, analyzer.getTestableList().size());
    }

}
