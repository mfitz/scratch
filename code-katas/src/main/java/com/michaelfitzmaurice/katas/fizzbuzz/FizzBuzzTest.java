package com.michaelfitzmaurice.katas.fizzbuzz;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FizzBuzzTest {
	
	private FizzBuzz fizzBuzz;
	
	@Before
	public void setup() {
		fizzBuzz = new FizzBuzz();
	}
	
	@Test
	public void leavesNumbersIndivisibleByThreeOrFiveAlone() {
		
		int[] nonFizzBuzzNumbers = {1, 2, 4, 7, 11, 22, 53, 101};
		for (int i = 0; i < nonFizzBuzzNumbers.length; i++) {
			int number = nonFizzBuzzNumbers[i];
			String failMsg = 
				format("%s should be unchanged by fizzbuzzer but was not;", 
						number);
			assertEquals(failMsg, "" + number, fizzBuzz.valueFor(number) );
		}
	}
	
	@Test
	public void replacesNumbersDivisibleByOnlyThreeWithFizz() {
		
		int[] fizzNumbers = {3, 6, 9, 12, 18, 102, 234};
		for (int i = 0; i < fizzNumbers.length; i++) {
			int number = fizzNumbers[i];
			String failMsg = 
				format("%s should be replaced by fizz but was not;", 
						number);
			assertEquals(failMsg, "fizz", fizzBuzz.valueFor(number) );
		}
	}
	
	@Test
	public void replacesNumbersDivisibleByOnlyFiveWithBuzz() {
		
		int[] buzzNumbers = {5, 10, 20, 25, 125, 245};
		for (int i = 0; i < buzzNumbers.length; i++) {
			int number = buzzNumbers[i];
			String failMsg = 
				format("%s should be replaced by buzz but was not;", 
						number);
			assertEquals(failMsg, "buzz", fizzBuzz.valueFor(number) );
		}
	}
	
	@Test
	public void replacesNumbersDivisibleByBothThreeAndFiveWithFizzBuzz() {
		
		int[] fizzBuzzNumbers = {15, 30, 45, 75, 105};
		for (int i = 0; i < fizzBuzzNumbers.length; i++) {
			int number = fizzBuzzNumbers[i];
			String failMsg = 
				format("%s should be replaced by fizzbuzz but was not;", 
						number);
			assertEquals(failMsg, "fizzbuzz", fizzBuzz.valueFor(number) );
		}
	}

}
