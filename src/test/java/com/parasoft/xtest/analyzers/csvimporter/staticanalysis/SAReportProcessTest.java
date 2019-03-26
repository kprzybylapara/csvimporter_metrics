/**
 * 
 */
package com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.parasoft.xtest.analyzers.common.ICommonConsts;
import com.parasoft.xtest.analyzers.common.ProjectFileTestableInput;
import com.parasoft.xtest.common.api.ITestableInput;
import com.parasoft.xtest.configuration.api.ITestConfiguration;
import com.parasoft.xtest.configuration.api.ITestConfigurationServiceContext;
import com.parasoft.xtest.configuration.api.rules.IRuleDescription;
import com.parasoft.xtest.results.api.IResult;

/**
 * @author mwest
 * @req 13122  
 *
 */
public class SAReportProcessTest {
	private static String RULESMAP_CSV_FILE = "/rulesmap.csv";	
	private File rulesMapCsv = null;
	
	private static String VIOLATIONS_CSV_FILE = "/violations.csv";
	private File violationsCsv = null;
	
	private static String PROJECT_DIR = "/";
	private File projectDir = null;
	
	private static String TEST_FILE = "/warship.ts";
	private File testFile = null;
	
	private List<ITestableInput> testableList = null;
	
	private SAReportProcess saRptProcess = null;
	
	private static ITestConfigurationServiceContext mockContext = mock(ITestConfigurationServiceContext.class);
	private static ITestConfiguration mockConfig = mock(ITestConfiguration.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testableList = new ArrayList<ITestableInput>();
		rulesMapCsv = new File(this.getClass().getResource(RULESMAP_CSV_FILE).getFile());
		violationsCsv = new File(this.getClass().getResource(VIOLATIONS_CSV_FILE).getFile());
		projectDir = new File(this.getClass().getResource(PROJECT_DIR).getFile());
		saRptProcess = new SAReportProcess(mockContext);	
		testFile = new File(this.getClass().getResource(TEST_FILE).getFile());
		
		ITestableInput testInput = new ProjectFileTestableInput(projectDir, testFile);
		testableList.add(testInput);
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAReportProcess#processRuleMap(java.io.File)}.
	 * Test that processRulesMap can produce a list of rule descriptions from a rules map csv file 
	 * @req 13122
	 */
	@Test
	public void testProcessRuleMap() {
		List<IRuleDescription> ruleDesc = saRptProcess.processRuleMap(rulesMapCsv);
		assertFalse(ruleDesc.isEmpty());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAReportProcess#processViolations(java.io.File, java.util.List)}.
	 * Tests that processViolations can produce a list of violations from a violations csv file. processRuleMap must return a 
	 * List<IRuleDescription> object.
	 * @req 13122
	 */
	@Test
	public void testProcessViolations() {
		try {
			MockSAReportProcess mockSAProcess = new MockSAReportProcess(mockContext);
			when(mockContext.getTestConfiguration()).thenReturn(mockConfig);
			when(mockConfig.getProperties()).thenReturn(new Properties());
			System.setProperty(ICommonConsts.DATA_DIR_KEY, projectDir.getAbsolutePath());
			List<IRuleDescription> ruleDesc = mockSAProcess.processRuleMap(rulesMapCsv);
			assertNotNull(ruleDesc);
			List<IResult> violations = mockSAProcess.processViolations(violationsCsv, ruleDesc, projectDir, testableList);
			assertFalse(violations.isEmpty());
		} catch (RuntimeException e) {
			throw new RuntimeException("Failure in testProcessViolations", e);
		} finally {
			System.clearProperty(ICommonConsts.DATA_DIR_KEY);
		}
	}
	
	class MockSAReportProcess extends SAReportProcess {
		
		MockSAReportProcess(ITestConfigurationServiceContext mockContext) {
			super(mockContext);
		}
	}
}

