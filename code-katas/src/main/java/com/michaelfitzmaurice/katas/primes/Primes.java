package com.michaelfitzmaurice.katas.primes;

import java.util.BitSet;


public class Primes {
	
	public boolean isPrime(int num) {
		
		return checkWIthBruteForce(num);
//		return checkWithSieve(num);
//		return checkWithBitSetSieve(num);
	}
	
	private boolean checkWIthBruteForce(int num) {
		
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				System.out.println("Divisible by " + i + "!");
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkWithSieve(int num) {
		
		boolean[] sieve = new boolean[num + 1];
		for (int i = 0; i < sieve.length; i++) {
			sieve[i] = true;
		}
		
		int limit = (int)Math.round( Math.sqrt(num) );
		System.out.println("Limit: " + limit);
		for (int i = 2; i <= limit; i++) {
			if (sieve[i] == true) {
				for (int j = i * i; j <= num; j += i) {
					sieve[j] = false;
				}
			}
		}
		
		return sieve[num];
	}
	
	private boolean checkWithBitSetSieve(int num) {
		
		BitSet sieve = new BitSet(num + 1);
		sieve.set( 0, sieve.size() );
		
		int limit = (int)Math.round( Math.sqrt(num) );
		for (int i = 2; i <= limit; i++) {
			if ( sieve.get(i) ) {
				for (int j = i * i; j <= num; j += i) {
					sieve.clear(j);
				}
			}
		}
		
		return sieve.get(num);
	}

}
