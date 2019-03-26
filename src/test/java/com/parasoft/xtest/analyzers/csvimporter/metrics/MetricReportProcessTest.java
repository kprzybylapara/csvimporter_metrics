/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer.metrics
 * Class: MetricReportProcessTest.java
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

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.parasoft.xtest.analyzers.common.ContextImpl;
import com.parasoft.xtest.analyzers.common.testconfiguration.AnalyzerTestConfiguration;
import com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.DTPMetricsDeleteClient;
import com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean;
import com.parasoft.xtest.common.dtp.DtpPreferences;
import com.parasoft.xtest.common.dtp.DtpPreferencesFactory;
import com.parasoft.xtest.common.dtp.DtpUtil;
import com.parasoft.xtest.common.preferences.IPreferences;
import com.parasoft.xtest.configuration.api.rules.IRuleDescription;
import com.parasoft.xtest.services.api.IParasoftServiceContext;

/**
 * @author sang
 * @req 13233
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MetricReportProcessTest {

    private static DtpPreferences dtpPreferences = null;
    private static List<MetricBean> metricList = null;
    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUp() throws Exception {
        
        DtpPreferencesFactory factory = new DtpPreferencesFactory();
        Properties dtpSettings = DtpUtil.createConnectionSettings("skunk-dev2.parasoft.com", "8443", "admin", "626079fd67", "PIE");
        IParasoftServiceContext dtpContext = new ContextImpl(dtpSettings, new AnalyzerTestConfiguration());
        IPreferences prefService =  factory.getService(dtpContext);
        if (prefService instanceof DtpPreferences) {
            dtpPreferences = (DtpPreferences)prefService;
        }

        assertNotNull(dtpPreferences);

    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDown() throws Exception {
        
        if (metricList != null && !metricList.isEmpty()) {
            DTPMetricsDeleteClient client = new DTPMetricsDeleteClient(dtpPreferences);
            client.deleteMetrics(metricList);
        }
    }

    /**
     * @req 13233
     * @task 96880
     */
    @Test
    public void aTestParseCSVRuleFile() {
        File ruleFile = new File(this.getClass().getResource("metric-rules.csv").getFile());
        assertNotNull(ruleFile);
        assertTrue(ruleFile.exists());
        metricList =  MetricsReportProcess.parseCSVRuleFile(ruleFile);
        assertEquals(4, metricList.size());
        
        assertTrue(metricList.contains(new MetricBean("METRIC.TEST.FOO", "METRIC.TEST", null, null,null, null, null, null)));
    }
    
    @Test
    public void bTestConvertMetricToRule() {
        assertNotNull(metricList);
        List<IRuleDescription> ruleList = MetricsReportProcess.convertMetricToRule(metricList);
        assertNotNull(ruleList);
        assertEquals(metricList.size(), ruleList.size());
    }

    @Test
    public void cTestProcessRuleMap() {
        MetricsReportProcess metricsReportProcess = new MetricsReportProcess();
        metricsReportProcess.setDtpPref(dtpPreferences);
        
        // add and update rule set already to live DTP server...
        List<IRuleDescription>ruleList = metricsReportProcess.processRuleMap(new File(this.getClass().getResource("metric-rules.csv").getFile()));
        assertNotNull(ruleList);
        assertEquals(metricList.size(), ruleList.size());
    }
}
