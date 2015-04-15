package com.michaelfitzmaurice.katas.leapyear;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class YearTest {
	
	@Test (expected = IllegalArgumentException.class)
	public void rejectsNegativeYears() {
		
		int negativeYear = -1980;
		try {
			new Year(negativeYear);
		} catch (IllegalArgumentException e) {
			String expectedMessage = 
				format(Year.ILLEGAL_YEAR_ERROR_TEMPLATE, negativeYear);
			assertEquals( expectedMessage, e.getMessage() );
			throw e;
		}
	}
	
	@Test
	public void recognisesTypicalCommonYears() {
		
		Year year = new Year(1985);
		String failMsg = format("%s is a typical common year;", year);
		assertFalse( failMsg, year.isLeapYear() );
	}
	
	@Test
	public void recognisesAtypicalCommonYears() {
		
		Year year = new Year(1900);
		String failMsg = format("%s is an atypical common year;", year);
		assertFalse( failMsg, year.isLeapYear() );
	}
	
	@Test
	public void recognisesTypicalLeapYears() {
		
		Year year = new Year(1996);
		String failMsg = format("%s is a typical leap year;", year);
		assertTrue( failMsg, year.isLeapYear() );
	}
	
	@Test
	public void recognisesAtypicalLeapYears() {
		
		Year year = new Year(2000);
		String failMsg = format("%s is an atypical leap year;", year);
		assertTrue( failMsg, year.isLeapYear() );
	}
}
