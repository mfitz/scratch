package com.michaelfitzmaurice.katas.pattern;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PatternMatcherTest {
	
	private PatternMatcher patternMatcher;
	
	@Before
	public void setup() {
		patternMatcher = new PatternMatcher();
	}
	
	@Test
	public void findsNoMatchUsingBoyerMooreWhenPatternIsLongerThanText() {
		
		String text = "Madam, I'm Adam";
		String pattern = text + "!";
		assertEquals(-1, patternMatcher.boyerMooreMatch(text, pattern) );
	}
	
	@Test
	public void findsNoMatchUsingBoyerMooreWhenPatternNotFoundInText() {
		
		String text = "Madam, I'm Adam";
		String pattern = "nope!";
		assertEquals(-1, patternMatcher.boyerMooreMatch(text, pattern) );
	}

	@Test
	public void matchesIdenticalStringsAtIndex0UsingBoyerMoore() {
		
		String text = "Madam, I'm Adam";
		String pattern = text;
		assertEquals(0, patternMatcher.boyerMooreMatch(text, pattern) );
	}
	
	@Test
	public void findsTheOnlyMatchingSubstringIndexUsingBoyerMoore() {
		
		String text = "Madam, I'm Adam";
		String pattern = "adam";
		assertEquals(1, patternMatcher.boyerMooreMatch(text, pattern) );
	}
	
	@Test
	public void findsLeftMostMatchingSubstringIndexAmongstMultipleMatchesUsingBoyerMoore() {
		
		String text = "123abc123abc123abc";
		String pattern = "abc";
		assertEquals(3, patternMatcher.boyerMooreMatch(text, pattern) );
	}
}
