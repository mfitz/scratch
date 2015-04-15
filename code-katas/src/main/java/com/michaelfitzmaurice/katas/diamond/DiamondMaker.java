package com.michaelfitzmaurice.katas.diamond;

import static java.lang.String.format;

import java.io.PrintStream;
import java.util.Stack;

public class DiamondMaker {
	
	private static final char START_OF_ALPHABET = 'A';
	
	public void printDiamond(char midPoint, PrintStream stream) {
		
		char midPointLetter = Character.toUpperCase(midPoint);
		Stack<String> linesStack = 
			printDiamondTopHalfAndMiddle(stream, midPointLetter);
		printDiamondBottomHalf(stream, linesStack);
	}

	private Stack<String> printDiamondTopHalfAndMiddle(PrintStream stream,
													char midPointLetter) {
		
		// store lines (except the middle line) to reuse in the bottom half
		Stack<String> linesStack = new Stack<String>();
		
		for (char letter = START_OF_ALPHABET; letter <= midPointLetter; letter++) {
			
			StringBuffer line = new StringBuffer();
			writeLine(midPointLetter, letter, line);
			
			if (letter != midPointLetter) {
				linesStack.push( line.toString() );
			}
			
			stream.print( line.toString() );
		}
		
		return linesStack;
	}
	
	private void printDiamondBottomHalf(PrintStream stream,
										Stack<String> linesStack) {
		
		while (linesStack.isEmpty() == false) {
			stream.print( linesStack.pop() );
		}
	}

	private void writeLine(char diamondMidPointLetter, 
							char currentLetter, 
							StringBuffer line) {
		
		addLeadingSpaces(diamondMidPointLetter, currentLetter, line);
		line.append(currentLetter);
		
		if (currentLetter != START_OF_ALPHABET) {
			addPaddingSpaces(currentLetter, line);
			line.append(currentLetter);
		}
		line.append( format("%n") );
	}

	private void addPaddingSpaces(char currentLetter, StringBuffer line) {
		
		int paddingSpaces = 
			( 2 * distanceBetween(START_OF_ALPHABET, currentLetter) ) - 1;
		for (int j = 0; j < paddingSpaces; j++) {
			line.append(" ");
		}
	}

	private void addLeadingSpaces(char diamondMidPointLetter, 
									char currentLetter, 
									StringBuffer line) {
		
		int leadingSpaces = 
			distanceBetween(currentLetter, diamondMidPointLetter);
		for (int j = 0; j < leadingSpaces; j++) {
			line.append(" ");
		}
	}
	
	private int distanceBetween(char first, char second) {
		return second - first;
	}

}
