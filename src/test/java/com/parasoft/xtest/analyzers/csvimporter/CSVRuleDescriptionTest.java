/**
 * 
 */
package com.parasoft.xtest.analyzers.csvanalyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.parasoft.xtest.configuration.api.rules.RuleScope;

/**
 * @author mwest
 * @req 13122
 */
public class CSVRuleDescriptionTest {

	private CSVRuleDescription rd = null;

	@Before
	public void setUp() throws Exception {
		rd = new CSVRuleDescription();
	}


	/**
	 * Test getRuleId(). 
	 */
	@Test
	public void testGetRuleId() {
		assertNull(rd.getRuleId());
		
		rd.setRuleId("ruleId");
		assertEquals("ruleId", rd.getRuleId());
	}

	/**
	 * Test getCategoryId
	 */
	@Test
	public void testGetCategoryId() {
		assertNull(rd.getCategoryId());
		
		rd.setCategoryId("catId");
		assertEquals("catId", rd.getCategoryId());
	}

	/**
	 * Test getProviderId
	 */
	@Test
	public void testGetProviderId() {
		assertNull(rd.getProviderId());
		
		rd.setProviderId("provId");
		assertEquals("provId", rd.getProviderId());
	}

	/**
	 * Test getHeader
	 */
	@Test
	public void testGetHeader() {
		assertNull(rd.getHeader());
		
		rd.setHeader("header");
		assertEquals("header", rd.getHeader());
	}

	/**
	 * Test getSeverity
	 */
	@Test
	public void testGetSeverity() {
		assertEquals(0, rd.getSeverity());
		
		rd.setSeverity(5);
		assertEquals(5, rd.getSeverity());
	}

	/**
	 * Test getScope
	 */
	@Test
	public void testGetScope() {
		assertNull(rd.getScope());
		
		rd.setScope(RuleScope.line);
		assertEquals(RuleScope.line, rd.getScope());
	}

	/**
	 * Test hasQuickfix
	 */
	@Test
	public void testHasQuickfix() {
		assertFalse(rd.hasQuickfix());
		
		rd.setQuickFix(true);
		assertTrue(rd.hasQuickfix());
	}

	/**
	 * Test getSeparator
	 */
	@Test
	public void testGetSeparator() {
		assertEquals('.', rd.getSeparator());
		
		rd.setSeparator('-');
		assertEquals('-', rd.getSeparator());
	}

	/**
	 * Test getAttributes
	 */
	@Test
	public void testGetAttributes() {
		assertNull(rd.getAttributes());
		
		rd.addAttribute("attr", "attr_value");
		assertEquals("attr_value", rd.getAttribute("attr"));
	}

	/**
	 * Test method for getAttribute
	 */
	@Test
	public void testGetAttribute() {
		assertNull(rd.getAttribute("no_attr"));
		
		rd.addAttribute("attr", "attr_value");
		assertEquals("attr_value", rd.getAttribute("attr"));
		// bad attribute should return null even after map is created
		assertNull(rd.getAttribute("no_attr"));
	}

	/**
	 * Test method for getBody
	 */
	@Test
	public void testGetBody() {
		assertNull(rd.getBody());
		
		CSVRuleDescriptionBody body = new CSVRuleDescriptionBody();
		rd.setBody(body);
		assertNotNull(rd.getBody());
	}
	

	/**
	 * Test getAnalyzerId
	 */
	@Test
	public void testGetAnalyzerId() {
		assertNull(rd.getAnalyzerId());
		
		rd.setAnalyzerId("analz_id");
		assertEquals("analz_id", rd.getAnalyzerId());
	}

	/**
	 * Test setRuleId
	 */
	@Test
	public void testSetRuleId() {
		rd.setRuleId("rule_id");
		assertEquals("rule_id", rd.getRuleId());
	}

	/**
	 * Test setCategoryId
	 */
	@Test
	public void testSetCategoryId() {
		rd.setCategoryId("cat_id");
		assertEquals("cat_id", rd.getCategoryId());
	}

	/**
	 * Test setProviderId.
	 */
	@Test
	public void testSetProviderId() {
		rd.setProviderId("prov_id");
		assertEquals("prov_id", rd.getProviderId());
	}

	/**
	 * Test setAnalyzerId
	 */
	@Test
	public void testSetAnalyzerId() {
		rd.setAnalyzerId("analz_id");
		assertEquals("analz_id", rd.getAnalyzerId());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescription#setHeader(java.lang.String)}.
	 */
	@Test
	public void testSetHeader() {
		rd.setHeader("header");
		assertEquals("header", rd.getHeader());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescription#setSeverity(int)}.
	 */
	@Test
	public void testSetSeverity() {
		rd.setSeverity(5);
		assertEquals(5, rd.getSeverity());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescription#setScope(com.parasoft.xtest.configuration.api.rules.RuleScope)}.
	 */
	@Test
	public void testSetScope() {
		rd.setScope(RuleScope.line);
		assertEquals(RuleScope.line, rd.getScope());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescription#setQuickFix(boolean)}.
	 */
	@Test
	public void testSetQuickFix() {
		rd.setQuickFix(true);
		assertTrue(rd.hasQuickfix());
		
		rd.setQuickFix(false);
		assertFalse(rd.hasQuickfix());
	}

	/**
	 * Test setSeparator
	 */
	@Test
	public void testSetSeparator() {
		rd.setSeparator('Z');
		assertEquals('Z', rd.getSeparator());
	}

	/**
	 * Test setAttributes
	 */
	@Test
	public void testSetAttributes() {
		HashMap<String, String> hmap = new HashMap<>();
		hmap.put("attr_name", "attr_value");
		rd.setAttributes(hmap);
		
		Map<String, String> returnHMap = rd.getAttributes();
		assertEquals("attr_value", returnHMap.get("attr_name"));
	}

	/**
	 * Test addAttribute
	 */
	@Test
	public void testAddAttribute() {
		rd.addAttribute("attr_name", "attr_value");
		Map<String, String> returnHMap = rd.getAttributes();
		assertEquals("attr_value", returnHMap.get("attr_name"));
	}

	/**
	 * Test setBody
	 */
	@Test
	public void testSetBody() {
		CSVRuleDescriptionBody body = new CSVRuleDescriptionBody();
		rd.setBody(body);
		assertNotNull(rd.getBody());
	}

}
