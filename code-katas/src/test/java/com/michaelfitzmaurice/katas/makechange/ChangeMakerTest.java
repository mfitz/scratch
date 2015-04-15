package com.michaelfitzmaurice.katas.makechange;

import static com.michaelfitzmaurice.katas.makechange.ChangeMaker.INSUFFICIENT_TENDER_AMOUNT_MESSAGE_TEMPLATE;
import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ChangeMakerTest {
	
	private static final Set<Integer> COIN_DENOMINATIONS =
		new HashSet<Integer>();
	
	private ChangeMaker changeMaker;
	
	@Before()
	public void setup() {
		int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
		for (int i = 0; i < coins.length; i++) {
			int coinDenomination = coins[i];
			COIN_DENOMINATIONS.add(coinDenomination);
		}
		
		changeMaker = new ChangeMaker(COIN_DENOMINATIONS);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void rejectsTenderedAmountsLowerThanITemPrice() {
		
		int purchasePrice = 100;
		int amountTendered = 50;
		String expectedMessage = 
			format(INSUFFICIENT_TENDER_AMOUNT_MESSAGE_TEMPLATE, 
					amountTendered, 
					purchasePrice);
		try {
			changeMaker.makeChange(purchasePrice, amountTendered);
		} catch (IllegalArgumentException e) {
			assertEquals( expectedMessage, e.getMessage() );
			throw e;
		}
	}
	
	@Test
	public void givesNoChangeWhenExactAmountIsTendered() {
		
		int purchasePrice = 100;
		int amountTendered = purchasePrice;
		
		Map<Integer, Integer> change = 
			changeMaker.makeChange(purchasePrice, amountTendered);
		assertTrue( "No change should have been returned;", change.isEmpty() );
	}
	
	@Test
	public void givesSingleCoinInChangeWherePossible() {
		
		int purchasePrice = 50;
		int amountTendered = 100;
		
		Map<Integer, Integer> change = 
			changeMaker.makeChange(purchasePrice, amountTendered);
		assertEquals( 1, change.size() );
		assertTrue( change.keySet().contains(50) );
		assertEquals(1, change.get(50).intValue() );
	}
	
	@Test
	public void selectsCoinsOnlyFromSuppliedDenominations() {
		
		int purchasePrice = 50;
		int amountTendered = 80;
		
		Map<Integer, Integer> change = 
			changeMaker.makeChange(purchasePrice, amountTendered);
		for ( int coinDenomination : change.keySet() ) {
			String failMsg = 
				format("%s is not a legal coin denomination;", 
						coinDenomination);
			assertTrue( failMsg, COIN_DENOMINATIONS.contains(coinDenomination) );
		}
	}
	
	@Test
	public void givesSmallestNumberOfCoinsInChangeWhereMultipleCoinsRequired() {
		
		int purchasePrice = 72;
		int amountTendered = 100;
		
		Map<Integer, Integer> expectedChange = new HashMap<Integer, Integer>();
		expectedChange.put(20, 1);
		expectedChange.put(5, 1);
		expectedChange.put(2, 1);
		expectedChange.put(1, 1);
		
		Map<Integer, Integer> change = 
			changeMaker.makeChange(purchasePrice, amountTendered);
		String failMsg = 
			format("Change of %s should be composed of %s", 
					(amountTendered - purchasePrice), 
					expectedChange);
		assertEquals(failMsg, expectedChange, change);
	}
	
	@Test
	public void givesMultipleInstancesOfADenominationWhereRequired() {
		
		int purchasePrice = 1;
		int amountTendered = 10;
		
		Map<Integer, Integer> expectedChange = new HashMap<Integer, Integer>();
		expectedChange.put(5, 1);
		expectedChange.put(2, 2);
		
		Map<Integer, Integer> change = 
			changeMaker.makeChange(purchasePrice, amountTendered);
		String failMsg = 
			format("Change of %s should be composed of %s", 
					(amountTendered - purchasePrice), 
					expectedChange);
		assertEquals(failMsg, expectedChange, change);
	}
	
	@Test
	public void differentCoinSystem() {
		
		Set<Integer> coinDenominations = new HashSet<Integer>();
		
		int[] coins = {1, 3, 4};
		for (int i = 0; i < coins.length; i++) {
			int coinDenomination = coins[i];
			coinDenominations.add(coinDenomination);
		}
		changeMaker = new ChangeMaker(coinDenominations);
		
		int purchasePrice = 4;
		int amountTendered = 10;
		Map<Integer, Integer> change = 
			changeMaker.makeChange(purchasePrice, amountTendered);
		System.out.println(change);
	}

}
