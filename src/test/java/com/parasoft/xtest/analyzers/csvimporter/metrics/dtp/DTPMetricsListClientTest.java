/*
 * Package: com.parasoft.xtest.analyzers.csvanalyzer.metrics.dtp
 * Class: DTPMetricsListClientTest.java
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.json.JSONObject;
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
 * @req 13233
 */
public class DTPMetricsListClientTest {

    /**
     * @req 13233
     * @task 96880
     * @throws DtpException
     */
    @Test
    public void testClient() throws DtpException  {
        DtpPreferencesFactory factory = new DtpPreferencesFactory();
        Properties dtpSettings = DtpUtil.createConnectionSettings("skunk-dev2.parasoft.com", "8443", "admin", "626079fd67", "PIE");
        IParasoftServiceContext dtpContext = new ContextImpl(dtpSettings, new AnalyzerTestConfiguration());
        IPreferences prefService =  factory.getService(dtpContext);
        IDtpPreferences dtpPref = null;
        if (prefService instanceof DtpPreferences) {
            dtpPref = (DtpPreferences)prefService;
        }

        assertNotNull(dtpPref);
        DTPMetricsListClient client = new DTPMetricsListClient(dtpPref);
        List<MetricBean> beanList = client.getMetrics();
        assertNotNull(beanList);
        boolean found  = false;
        for (MetricBean bean : beanList) {
            if ("METRIC.CC".equals(bean.getId())) {
                assertEquals("method", bean.getLevel());
                assertEquals(5,bean.getLevels().size());
                assertEquals("AVG", bean.getDefaultAggregate());
                assertEquals(4, bean.getAggregates().size());
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    /**
     * @task 96880
     * @throws DtpException
     * @throws IOException
     */
    @Test
    public void testparseMertricsListJson() throws DtpException, IOException {
        File jsonFile = new File(this.getClass().getResource("metricsList-skunk-dev2.json").getFile());
        assertNotNull(jsonFile);
        List<String> fileContent = Files.readAllLines(Paths.get(jsonFile.toURI()), StandardCharsets.UTF_8);
        String json = String.join(System.getProperty("line.separator"), fileContent);
        assertNotNull(json);
        JSONObject obj = new JSONObject(json);
        int count = obj.getInt(MetricsFields.COUNT.getValue());

        List<MetricBean> beanList = DTPMetricsListClient.parseMertricsListJson(json);
        assertEquals(count, beanList.size());
        
    }
}
