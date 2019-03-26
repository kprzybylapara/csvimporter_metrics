/**
 * 
 */
package com.parasoft.xtest.analyzers.csvanalyzer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescriptionBody.CSVElement;
import com.parasoft.xtest.configuration.api.rules.IRuleDescriptionBody.Element;

/**
 * @author mwest
 *
 */
public class TestCSVRuleDescriptionBody {

	private CSVRuleDescriptionBody rdBody;
	private CSVElement csvElement;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		rdBody = new CSVRuleDescriptionBody();
		csvElement = rdBody.newCsvElement();
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescriptionBody#getElements()}.
	 */
	@Test
	public void testGetElements() {
		assertTrue(rdBody.getElements().isEmpty());
		
		rdBody.addElement(csvElement);
		assertNotNull(rdBody.getElements());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescriptionBody#getElements(java.lang.String)}.
	 */
	@Test
	public void testGetElementsString() {
		csvElement.setName("elem_name");
		rdBody.addElement(csvElement);
		List<Element> elemList = rdBody.getElements("elem_name");
		assertNotNull(elemList);
		assertFalse(elemList.isEmpty());
		assertEquals("elem_name", csvElement.getName());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescriptionBody#addElement(com.parasoft.xtest.configuration.api.rules.IRuleDescriptionBody.Element)}.
	 */
	@Test
	public void testAddElement() {
		csvElement.setName("elem_name");
		rdBody.addElement(csvElement);
		List<Element> elemList = rdBody.getElements("elem_name");
		assertNotNull(elemList);
		assertFalse(elemList.isEmpty());
		assertEquals("elem_name", csvElement.getName());
	}

	/**
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.CSVRuleDescriptionBody#newCsvElement()}.
	 */
	@Test
	public void testNewCsvElement() {
		assertNotNull(rdBody.newCsvElement());
	}

}
