package com.michaelfitzmaurice.katas.leapyear;

import static java.lang.String.format;

public class Year {
	
	public static final String ILLEGAL_YEAR_ERROR_TEMPLATE = 
		"'%s' is not a valid calendar year";
	
	private int year;
	
	public Year(int year) {
		
		if (year < 0) {
			throw new IllegalArgumentException(
				format(ILLEGAL_YEAR_ERROR_TEMPLATE, year) );
		}
		
		this.year = year;
	}
	
	public boolean isLeapYear() {
		
		boolean isLeapYear = false;
		
		if ( isDivisibleByFour(year) 
				&& ( isDivisibleBy100(year) == false 
						|| isDivisibleBy400(year) ) ) {
			isLeapYear = true;
		}
	
		return isLeapYear;
	}

	@Override
	public String toString() {
		return "Year [year=" + year + "]";
	}
	
	private boolean isDivisibleByFour(int num) {
		return isDivisibleBy(num, 4);
	}
	
	private boolean isDivisibleBy100(int num) {
		return isDivisibleBy(num, 100);
	}
	
	private boolean isDivisibleBy400(int num) {
		return isDivisibleBy(num, 400);
	}
	
	private boolean isDivisibleBy(int dividend, int divisor) {
		return dividend % divisor == 0;
	}

}
