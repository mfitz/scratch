package com.michaelfitzmaurice.katas.luhntest;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class CardCheckerTest {
	
	public static @DataPoints String[] validCardNumbers = {
		"79927398713",
		"49927398716",
		"1234567812345670"
	};
	
	public static @DataPoints String[] invalidCardNumbers = {
		"1234",
		"1",
		"99999",
		"79927398711",
		"79927398712",
		"79927398714",
		"79927398715",
		"79927398716",
		"79927398717"
	};
	
	private CardChecker cardChecker;
	
	@Before
	public void setup() {
		cardChecker = new CardChecker();
	}
	
	@Theory
    public void detectsInvalidCardNumbersViaLuhnTest(String cardNumber) {
		
		if ( Arrays.asList(validCardNumbers).contains(cardNumber) ) {
			return;
		}
		
		String failMsg = 
			format("%s should fail the Luhn test but did not;", cardNumber);
		assertFalse( failMsg, cardChecker.passesLuhnTest(cardNumber) );
    }
	
	@Theory
    public void detectsValidCardNumbersViaLuhnTest(String cardNumber) {
		
		if ( Arrays.asList(invalidCardNumbers).contains(cardNumber) ) {
			return;
		}
		
		String failMsg = 
			format("%s should pass the Luhn test but did not;", cardNumber);
		assertTrue( failMsg, cardChecker.passesLuhnTest(cardNumber) );
    }
	
	@Test
	public void sumsCardDigitsUsingLuhnAlgorithm() {
		
		String cardNumber = "1234";
		
		int expectedSum = 10;
		
		String failMsg = 
			format("Incorrect digit sum for card number %s;", cardNumber);
		assertEquals(failMsg, expectedSum, cardChecker.luhnSumDigits(cardNumber) );
	}
	
	@Test
	public void failsLuhnTestForNullCardNumber() {
		
		String failMsg = 
			"Null card number should fail the Luhn test but did not;";
		assertFalse( failMsg, cardChecker.passesLuhnTest(null) );
	}
	
	@Test
	public void cardNumberZeroPassesLuhnTest() {
		
		String failMsg = 
			"'0' card number should pass the Luhn test but did not;";
		assertTrue( failMsg, cardChecker.passesLuhnTest("0") );
	}

}
