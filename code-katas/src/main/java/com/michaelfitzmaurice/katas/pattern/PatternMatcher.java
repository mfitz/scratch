package com.michaelfitzmaurice.katas.pattern;

public class PatternMatcher {
	
	public int boyerMooreMatch(String text, String pattern) {
		
		int leftmostMatchIndex = -1;
		
		if (pattern.length() <= text.length() ) {
			leftmostMatchIndex = findLeftMostMatchIndex(text, pattern);
		}
		
		return leftmostMatchIndex;
	}

	private int findLeftMostMatchIndex(String text, 
										String pattern) {
		
		int matchIndex = -1;
		
		int[] last = buildLastArray(pattern);
		int textIndex = pattern.length() - 1;
		int patternIndex = pattern.length() - 1;

		while ( textIndex < text.length() ) {
			if (pattern.charAt(patternIndex) == text.charAt(textIndex) ) {
				if (patternIndex == 0) {
					matchIndex = textIndex;
					break;
				} else {
					patternIndex--;
					textIndex--;
				}
			} else {
				textIndex = 
					textIndex + pattern.length() - Math.min(patternIndex, 1 + last[text.charAt(patternIndex)]);
			}
		}
		
		return matchIndex;
	}
	
	private int[] buildLastArray(String pattern) {
		
		// assumes ASCII character set
		int[] last = new int[128];
		for (int i = 0; i < last.length; i++) {
			last[i] = -1;
		}
		
		for (int i = 0; i < pattern.length(); i++) {
			last[pattern.charAt(i)] = i;
		}
		
		return last;
	}

}
