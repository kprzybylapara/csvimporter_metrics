/**
 * @author mwest
 * @req 13122  
 *
 */
package com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;

import com.parasoft.xtest.configuration.api.rules.IRuleDescription;
import com.parasoft.xtest.configuration.api.rules.RuleScope;

/**
 * @author mwest
 *
 */
public class SARuleDescriptionRecordTest {
	private static String RULESMAP_CSV_FILE = "/rulesmap.csv";

	private SARuleDescriptionRecord ruleDesc = null;
	private File ruleFile = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {	
		ruleFile = new File(this.getClass().getResource(RULESMAP_CSV_FILE).getFile());
		
		try (FileReader rdr = new FileReader(ruleFile);
				CSVParser parser = new CSVParser(rdr, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			CSVRecord csvRec = parser.getRecords().get(0);
			ruleDesc = new SARuleDescriptionRecord(csvRec, RuleScope.line, false);
		} catch(Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Test the constructor
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#SARuleDescriptionRecord(org.apache.commons.csv.CSVRecord, com.parasoft.xtest.configuration.api.rules.RuleScope, boolean)}.
	 */
	@Test
	public void testSARuleDescriptionRecord() {

		try (FileReader rdr = new FileReader(ruleFile);
			CSVParser parser = new CSVParser(rdr, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			CSVRecord csvRec = parser.getRecords().get(0);
			ruleDesc = new SARuleDescriptionRecord(csvRec, RuleScope.line, false);
			assertNotNull(ruleDesc);
		} catch (Exception e) {
			fail("IO Error on test " + e.getMessage());
		} 
	}

	/**
	 * Test toCSVRuleDescription. 
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#toCsvRuleDescription()}.
	 * This method depends on the first row of the test script having TSLint.TypeScript as the category
	 */
	@Test
	public void testToCsvRuleDescription() {
		IRuleDescription rd = ruleDesc.toCsvRuleDescription();
		assertNotNull(rd);
		assertEquals("TSLint.TypeScript", rd.getCategoryId());
	}

	/**
	 * test getAnalyzerId()
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getAnalyzerId()}.
	 */
	@Test
	public void testGetAnalyzerId() {
		assertEquals("tslint", ruleDesc.getAnalyzerId());
	}

	/**
	 * test getCategory()
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getCategory()}.
	 */
	@Test
	public void testGetCategory() {
		assertEquals("TSLint.TypeScript", ruleDesc.getCategory());
	}

	/**
	 * test getProviderId() - provider is same as analyzer id
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getProviderId()}.
	 */
	@Test
	public void testGetProviderId() {
		assertEquals("tslint", ruleDesc.getProviderId());
	}

	/**
	 * test getRuleId() constructed from analyzer.category.rule_name
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getRuleId()}.
	 */
	@Test
	public void testGetRuleId() {
		assertEquals("TSLint.TypeScript.member-access", ruleDesc.getRuleId());
	}

	/**
	 * test getRuleName()
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getRuleName()}.
	 */
	@Test
	public void testGetRuleName() {
		assertEquals("member-access", ruleDesc.getRuleName());
	}

	/**
	 * test getHeader() same as rule name
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getHeader()}.
	 */
	@Test
	public void testGetHeader() {
		assertEquals("Requires explicit visibility declarations for class members", ruleDesc.getHeader());
	}

	/**
	 * test getRuleDocUrl
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getRuleDocURL()}.
	 */
	@Test
	public void testGetRuleDocURL() {
		assertEquals("http://palantir.github.io/tslint/rules/member-access/", ruleDesc.getRuleDocURL());
	}

	/**
	 * test getSeverity
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getSeverity()}.
	 */
	@Test
	public void testGetSeverity() {
		assertEquals("3", ruleDesc.getSeverity());
	}

	/**
	 * test getScope
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#getScope()}.
	 */
	@Test
	public void testGetScope() {
		assertEquals(RuleScope.line, ruleDesc.getScope());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SARuleDescriptionRecord#hasQuickFix()}.
	 */
	@Test
	public void testHasQuickFix() {
		assertFalse(ruleDesc.hasQuickFix());
	}

}
