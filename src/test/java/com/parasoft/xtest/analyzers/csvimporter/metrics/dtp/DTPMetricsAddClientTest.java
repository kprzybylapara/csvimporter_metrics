/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp
 * Class: DTPMetricsAddClientTest.java
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
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.parasoft.xtest.analyzers.common.ContextImpl;
import com.parasoft.xtest.analyzers.common.testconfiguration.AnalyzerTestConfiguration;
import com.parasoft.xtest.common.dtp.DtpException;
import com.parasoft.xtest.common.dtp.DtpPreferences;
import com.parasoft.xtest.common.dtp.DtpPreferencesFactory;
import com.parasoft.xtest.common.dtp.DtpUtil;
import com.parasoft.xtest.common.dtp.IDtpPreferences;
import com.parasoft.xtest.common.preferences.IPreferences;
import com.parasoft.xtest.services.api.IParasoftServiceContext;

/**
 * @author sang
 *
 */
public class DTPMetricsAddClientTest {

    private MetricBean bean = null;
    private IDtpPreferences dtpPref = null;
    
    /**
     * 
     * @throws Throwable
     */
    @Before
    public void setUp() throws Throwable {
        bean = new MetricBean("METRIC.ZZZZZ", "METRIC", "", null, "method", null, "AVG", null);
        bean.setAggregates("AVG;SUM;MIN;MAX");
        bean.setLevels("method;type;resource");
        bean.setNames("en=Metric unit test - analyzer;jp=XYZ");

        Properties dtpSettings = DtpUtil.createConnectionSettings("skunk-dev2.parasoft.com", "8443", "admin", "626079fd67", "PIE");
        IParasoftServiceContext context = new ContextImpl(dtpSettings, new AnalyzerTestConfiguration());
        DtpPreferencesFactory factory = new DtpPreferencesFactory();
        IPreferences prefService =  factory.getService(context);
        if (prefService instanceof DtpPreferences) {
            dtpPref = (DtpPreferences)prefService;
        }

        assertNotNull(dtpPref);

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        DTPMetricsDeleteClient deleteClient = new DTPMetricsDeleteClient(dtpPref);
        deleteClient.deleteMetric(bean.getId());
        //testing corner case of DTPMetricDeleteClient
        deleteClient.deleteMetric(null);
        deleteClient.deleteMetric("");
        
    }

    /**
     * Test both adding/deleting a Metric in DTP Server.
     * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.DTPMetricsAddClient#addMetric(com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp.MetricBean)}.
     * @throws DtpException
     */
    @Test
    public void testAddMetric() throws DtpException {
        try {
            DTPMetricsAddClient addClient = new DTPMetricsAddClient(dtpPref);
            addClient.addMetric(bean);
            
            DTPMetricsListClient listClient = new DTPMetricsListClient(dtpPref);
            List<MetricBean> metricList = listClient.getMetrics();
            assertNotNull(metricList);
            int index = metricList.indexOf(bean);
            assertTrue(index >= 0);
            MetricBean dtpMetric = metricList.get(index);
            assertEquals(bean.getId(), dtpMetric.getId());
            assertEquals(bean.getDefaultAggregate(), dtpMetric.getDefaultAggregate());
            assertTrue(bean.isEqual(dtpMetric));
        } finally {
        }
        
    }
}
