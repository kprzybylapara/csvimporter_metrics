/**
 * @author mwest
 * @req 13122  
 *
 */
package com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mwest
 *
 */
public class SAViolationRecordTest {

	private static String VIOLATION_CSV_FILE = "/violations.csv";
	private static String OPTIONAL_FIELDS_VIOLATIONS_FILE = "/optional_field_violations.csv";
	
	private SAViolationRecord vioRec = null;
	private File vioFile = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vioFile = new File(this.getClass().getResource(VIOLATION_CSV_FILE).getFile());
		
		try (FileReader rdr = new FileReader(vioFile); 
				CSVParser parser = new CSVParser(rdr, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			CSVRecord csvRec = parser.getRecords().get(0);
			vioRec = new SAViolationRecord(csvRec);
		} catch(Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * Test the constructor
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#SAViolationRecord(org.apache.commons.csv.CSVRecord)}.
	 */
	@Test
	public void testSAViolationRecord() {
		try (FileReader rdr = new FileReader(vioFile); 
				CSVParser parser = new CSVParser(rdr, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			CSVRecord csvRec = parser.getRecords().get(0);
			vioRec = new SAViolationRecord(csvRec);
			assertNotNull(vioRec);
		} catch(Exception e) {
			fail("IO Error on test " + e.getMessage());
		}
	}

	/**
	 * Test getRuleId
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getRuleId()}.
	 */
	@Test
	public void testGetRuleId() {
		assertEquals("no-var-keyword", vioRec.getRuleId());
	}

	/**
	 * test getFileName
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getFileName()}.
	 */
	@Test
	public void testGetFileName() {
		assertEquals("warship.ts", vioRec.getFileName());
	}

	/**
	 * test getStartLine
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getStartLine()}.
	 */
	@Test
	public void testGetStartLine() {
		assertEquals("14", vioRec.getStartLine());
	}

	/**
	 * Test getStartCol
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getStartCol()}.
	 */
	@Test
	public void testGetStartCol() {
		assertEquals("8", vioRec.getStartCol());
	}

	/**
	 * test getMessage
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getMessage()}.
	 */
	@Test
	public void testGetMessage() {
		assertEquals("forbidden var keyword", vioRec.getMessage());
	}

	/**
	 * test getEndLine
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getEndLine()}.
	 */
	@Test
	public void testGetEndLine() {
		assertEquals("14", vioRec.getEndLine());
	}

	/**
	 * test getEndCol
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getEndCol()}.
	 */
	@Test
	public void testGetEndCol() {
		assertEquals("11", vioRec.getEndCol());
	}

	/**
	 * test getLanguage
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getLanguage()}.
	 */
	@Test
	public void testGetLanguage() {
		assertEquals("typescript", vioRec.getLanguage());
	}

	/**
	 * test getPackage
	 * Test method for {@link com.parasoft.xtest.analyzers.csvanalyzer.staticanalysis.SAViolationRecord#getPackage()}.
	 */
	@Test
	public void testGetPackage() {
		assertEquals("test.package", vioRec.getPackage());
	}

	/**
	 * endLine, endCol and package are optional and may not have columns on the spreadsheet. This
	 * test ensure that we return nulls for these fields and do not throw an IllegalArgumentException
	 * somewhere along the way
	 */
	@Test
	public void testOptionalFields() {
		vioFile = new File(this.getClass().getResource(OPTIONAL_FIELDS_VIOLATIONS_FILE).getFile());
		
		try (FileReader rdr = new FileReader(vioFile); 
				CSVParser parser = new CSVParser(rdr, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			CSVRecord csvRec = parser.getRecords().get(0);
			vioRec = new SAViolationRecord(csvRec);
			assertNotNull(vioRec);
			
			assertEquals(null, vioRec.getEndLine());
			assertEquals(null, vioRec.getEndCol());
			assertEquals(null, vioRec.getPackage());
		} catch(Exception e) {
			fail("IO Error on test " + e.getMessage());
		}
	}
}
