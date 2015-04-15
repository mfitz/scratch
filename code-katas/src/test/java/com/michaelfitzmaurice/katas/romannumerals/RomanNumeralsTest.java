package com.michaelfitzmaurice.katas.romannumerals;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RomanNumeralsTest {
	
	private RomanNumerals romanNumerals;
	
	@Before
	public void setup() {
		romanNumerals = new RomanNumerals2();
	}

	@Test
	public void oneTwoThree() {
		
		assertEquals( "I", romanNumerals.toRomanNumerals(1) );
		assertEquals( "II", romanNumerals.toRomanNumerals(2) );
		assertEquals( "III", romanNumerals.toRomanNumerals(3) );
	}
	
	@Test
	public void four() {
		
		assertEquals( "IV", romanNumerals.toRomanNumerals(4) );
	}
	
	@Test
	public void five() {
		
		assertEquals( "V", romanNumerals.toRomanNumerals(5) );
	}
	
	@Test
	public void sixSevenEight() {
		
		assertEquals( "VI", romanNumerals.toRomanNumerals(6) );
		assertEquals( "VII", romanNumerals.toRomanNumerals(7) );
		assertEquals( "VIII", romanNumerals.toRomanNumerals(8) );
	}
	
	@Test
	public void nine() {
		
		assertEquals( "IX", romanNumerals.toRomanNumerals(9) );
	}
	
	@Test
	public void ten() {
		
		assertEquals( "X", romanNumerals.toRomanNumerals(10) );
	}
	
	@Test
	public void elevenTwelveThirteen() {
		
		assertEquals( "XI", romanNumerals.toRomanNumerals(11) );
		assertEquals( "XII", romanNumerals.toRomanNumerals(12) );
		assertEquals( "XIII", romanNumerals.toRomanNumerals(13) );
	}
	
	@Test
	public void thirtyFour() {
		
		assertEquals( "XXXIV", romanNumerals.toRomanNumerals(34) );
	}
	
	@Test
	public void ninety() {
		
		assertEquals( "XC", romanNumerals.toRomanNumerals(90) );
	}
	
	@Test
	public void fourHundred() {
		
		assertEquals( "CD", romanNumerals.toRomanNumerals(400) );
	}
	
	@Test
	public void twoThousandAndFifteen() {
		
		assertEquals( "MMXV", romanNumerals.toRomanNumerals(2015) );
	}
}
