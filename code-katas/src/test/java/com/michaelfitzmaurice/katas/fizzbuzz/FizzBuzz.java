package com.michaelfitzmaurice.katas.fizzbuzz;

public class FizzBuzz {
	
	public String valueFor(int number) {
		
		String value = "";
		if (number % 3 == 0) {
			value += "fizz";
		} if (number % 5 == 0){
			value += "buzz";
		} 
		
		if ( "".equals(value) ) {
			value = "" + number;
		}
		
		return value;
	}

	public static void main(String[] args) {
		
		int numberToCountUpTo = Integer.parseInt(args[0]);
		FizzBuzz fizzBuzz = new FizzBuzz();
		
		for (int i = 1; i <= numberToCountUpTo; i++) {
			System.out.println( fizzBuzz.valueFor(i) );
		}
	}

}
