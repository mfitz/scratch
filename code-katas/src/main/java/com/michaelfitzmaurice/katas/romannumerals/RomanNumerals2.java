package com.michaelfitzmaurice.katas.romannumerals;

import java.util.NavigableSet;
import java.util.TreeMap;

public class RomanNumerals2 extends RomanNumerals {
	
	TreeMap<Integer, String> numeralsMap = new TreeMap<Integer, String>();
	
	public RomanNumerals2() {
		numeralsMap.put(1000, "M");
		numeralsMap.put(900, "CM");
		numeralsMap.put(500, "D");
		numeralsMap.put(400, "CD");
		numeralsMap.put(100, "C");
		numeralsMap.put(90, "XC");
		numeralsMap.put(50, "L");
		numeralsMap.put(40, "XL");
		numeralsMap.put(10, "X");
		numeralsMap.put(9, "IX");
		numeralsMap.put(5, "V");
		numeralsMap.put(4, "IV");
		numeralsMap.put(1, "I");
	}

	@Override
	public String toRomanNumerals(int arabicNumber) {
		
		int remainder = arabicNumber;	
		StringBuffer romanNumerals = new StringBuffer();
			
		NavigableSet<Integer> numeralValues = numeralsMap.descendingKeySet();
		for (Integer numeralValue : numeralValues) {
			while (remainder > 0 && remainder >= numeralValue) {
				remainder -= numeralValue;
				romanNumerals.append( numeralsMap.get(numeralValue) );
			}
		}
		
		return romanNumerals.toString();
	}
	
}
