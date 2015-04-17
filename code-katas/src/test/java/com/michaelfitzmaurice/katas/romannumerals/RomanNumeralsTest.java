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
	public void oneTwoThreeToRomanNumerals() {
		
		assertEquals( "I", romanNumerals.toRomanNumerals(1) );
		assertEquals( "II", romanNumerals.toRomanNumerals(2) );
		assertEquals( "III", romanNumerals.toRomanNumerals(3) );
	}
	
	@Test
	public void fourToRomanNumerals() {
		
		assertEquals( "IV", romanNumerals.toRomanNumerals(4) );
	}
	
	@Test
	public void fiveToRomanNumerals() {
		
		assertEquals( "V", romanNumerals.toRomanNumerals(5) );
	}
	
	@Test
	public void sixSevenEightToRomanNumerals() {
		
		assertEquals( "VI", romanNumerals.toRomanNumerals(6) );
		assertEquals( "VII", romanNumerals.toRomanNumerals(7) );
		assertEquals( "VIII", romanNumerals.toRomanNumerals(8) );
	}
	
	@Test
	public void nineToRomanNumerals() {
		
		assertEquals( "IX", romanNumerals.toRomanNumerals(9) );
	}
	
	@Test
	public void tenToRomanNumerals() {
		
		assertEquals( "X", romanNumerals.toRomanNumerals(10) );
	}
	
	@Test
	public void elevenTwelveThirteenToRomanNumerals() {
		
		assertEquals( "XI", romanNumerals.toRomanNumerals(11) );
		assertEquals( "XII", romanNumerals.toRomanNumerals(12) );
		assertEquals( "XIII", romanNumerals.toRomanNumerals(13) );
	}
	
	@Test
	public void thirtyFourToRomanNumerals() {
		
		assertEquals( "XXXIV", romanNumerals.toRomanNumerals(34) );
	}
	
	@Test
	public void ninetyToRomanNumerals() {
		
		assertEquals( "XC", romanNumerals.toRomanNumerals(90) );
	}
	
	@Test
	public void fourHundredToRomanNumerals() {
		
		assertEquals( "CD", romanNumerals.toRomanNumerals(400) );
	}
	
	@Test
	public void twoThousandAndFifteenToRomanNumerals() {
		
		assertEquals( "MMXV", romanNumerals.toRomanNumerals(2015) );
	}
	
	@Test
	public void oneTwoThreeFromRomanNumerals() {
		
		assertEquals(1, romanNumerals.fromRomanNumerals("I") );
		assertEquals(2, romanNumerals.fromRomanNumerals("II") );
		assertEquals(3, romanNumerals.fromRomanNumerals("III") );
	}
	
	@Test
	public void fourFromRomanNumerals() {
		
		assertEquals(4, romanNumerals.fromRomanNumerals("IV") );
	}
	
	@Test
	public void fiveFromRomanNumerals() {
		
		assertEquals(5, romanNumerals.fromRomanNumerals("V") );
	}
	
	@Test
	public void sixSevenEightFromRomanNumerals() {
		
		assertEquals(6, romanNumerals.fromRomanNumerals("VI") );
		assertEquals(7, romanNumerals.fromRomanNumerals("VII") );
		assertEquals(8, romanNumerals.fromRomanNumerals("VIII") );
	}
	
	@Test
	public void nineFromRomanNumerals() {
		
		assertEquals(9, romanNumerals.fromRomanNumerals("IX") );
	}
	
	@Test
	public void tenFromRomanNumerals() {
		
		assertEquals(10, romanNumerals.fromRomanNumerals("X") );
	}
	
	@Test
	public void elevenTwelveThirteenFromRomanNumerals() {
		
		assertEquals(11, romanNumerals.fromRomanNumerals("XI") );
		assertEquals(12, romanNumerals.fromRomanNumerals("XII") );
		assertEquals(13, romanNumerals.fromRomanNumerals("XIII") );
	}
	
	@Test
	public void thirtyFourFromRomanNumerals() {
		
		assertEquals( 34 ,romanNumerals.fromRomanNumerals("XXXIV") );
	}
	
	@Test
	public void ninetyFromRomanNumerals() {
		
		assertEquals( 90, romanNumerals.fromRomanNumerals("XC") );
	}
	
	@Test
	public void fourHundredFromRomanNumerals() {
		
		assertEquals( 400, romanNumerals.fromRomanNumerals("CD") );
	}
	
	@Test
	public void twoThousandAndFifteenFromRomanNumerals() {
		
		assertEquals( 2015, romanNumerals.fromRomanNumerals("MMXV") );
	}
}
