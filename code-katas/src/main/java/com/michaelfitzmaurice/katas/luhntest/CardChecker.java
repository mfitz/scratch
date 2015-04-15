package com.michaelfitzmaurice.katas.luhntest;

public class CardChecker {
	
	/**
	 * For a description of the Luhn check algorithm, see:
	 * http://en.wikipedia.org/wiki/Luhn_algorithm
	 * 
	 * @param cardNumber
	 * 
	 * @return
	 */
	public boolean passesLuhnTest(String cardNumber) {
		
		boolean passesLuhnTest = false;
		
		if (cardNumber != null) {
			int checkDigitOnCard = 
				Character.getNumericValue( 
						cardNumber.charAt(cardNumber.length() - 1) );
			int sumDigitsUnit = luhnSumDigits(cardNumber) % 10;
			int computedCheckDigit = 10 - sumDigitsUnit;
			if (computedCheckDigit == 10) {
				computedCheckDigit = 0;
			}

			passesLuhnTest = (checkDigitOnCard == computedCheckDigit);
		}
		
		return passesLuhnTest;
	}
	
	public int luhnSumDigits(String cardNumber) {
		
		int sum = 0;
		boolean doubleDigit = true;
		
		for (int i = cardNumber.length() - 2; i >= 0; i--) {
			int digit = Character.getNumericValue( cardNumber.charAt(i) );
			if (doubleDigit) {
				digit *= 2;
				if (digit > 9) {
					digit = (digit % 10) + 1;
				}
 			}
			sum += digit;
			
			doubleDigit = ! doubleDigit;
		}
		
		return sum;
	}
	
}
