package com.michaelfitzmaurice.katas.romannumerals;

public class RomanNumerals {
	
	public String toRomanNumerals(int arabicNumber) {
		 
		StringBuffer romanNumerals = new StringBuffer();
		int remainder = arabicNumber;
		
		if (remainder >= 9) {
			remainder -= 9;
			romanNumerals.append("IX");
		}
		
		if (remainder >= 5) {
			remainder -= 5;
			romanNumerals.append("V");
		} 
		
		if (remainder >= 4) {
			remainder -=4;
			romanNumerals.append("IV");
		}
			
		while (remainder > 0) {
			remainder --;
			romanNumerals.append("I");
		}
		
		return romanNumerals.toString();
	}
}
