package com.michaelfitzmaurice.katas.makechange;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ChangeMaker {
	
	public static final String INSUFFICIENT_TENDER_AMOUNT_MESSAGE_TEMPLATE = 
			"%s is not enough money to pay for an item costing %s";
	
	private final TreeSet<Integer> sortedCoinDenominations;
	
	public ChangeMaker(Set<Integer> coinDenominations) {
		this.sortedCoinDenominations = new TreeSet<Integer>(coinDenominations);
	}
	
	public Map<Integer, Integer> makeChange(int purchasePrice, 
											int amountTendered) {
		
		if (amountTendered < purchasePrice) {
			throw new IllegalArgumentException(
				format(INSUFFICIENT_TENDER_AMOUNT_MESSAGE_TEMPLATE, 
						amountTendered, 
						purchasePrice) );
		}
		
		Map<Integer, Integer> change = new HashMap<Integer, Integer>();
		int changeRequired = amountTendered - purchasePrice;
		// greedy algorithm - will not produce optimal result (in terms 
		// of fewest coins used) under all sets of denominations, but will
		// do so under most commonly used sets, e.g. UK or US coin systems
		Iterator<Integer> coins = sortedCoinDenominations.descendingIterator();
		while (changeRequired > 0) {
			int coin = coins.next();
			while (coin <= changeRequired) {
				changeRequired -= coin;
				if (change.get(coin) == null) {
					change.put(coin, 0);
				}
				
				change.put(coin, change.get(coin) + 1);
			}
		}
		
		return change;
	}
}
