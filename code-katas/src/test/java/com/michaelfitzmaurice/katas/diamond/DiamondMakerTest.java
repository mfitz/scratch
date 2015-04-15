package com.michaelfitzmaurice.katas.diamond;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiamondMakerTest {
	
	/*
	       A
	      B B
	       A
	       
	       A
	      B B
	     C   C
	      B B
	       A  
	*/
	
	private DiamondMaker diamondMaker;
	private PrintStream collector;
	private ByteArrayOutputStream byteStream;
	
	@Before
	public void setup() {
		diamondMaker = new DiamondMaker();
		byteStream = new ByteArrayOutputStream();
		collector = new PrintStream(byteStream);
	}
	
	@After
	public void tearDown() {
		collector.close();
	}
	
	@Test
	public void printsOnlyAForA() {
		
		String expected = format("A%n");
		diamondMaker.printDiamond('A', collector);
		assertEquals(expected, stringFromByteArray() );
	}
	
	@Test
	public void convertsLowerCaseAToUpperCase() {
		
		String expected = format("A%n");
		diamondMaker.printDiamond('a', collector);
		assertEquals(expected, stringFromByteArray() );
	}
	
	@Test
	public void printsDiamond() {
		
//		   A
//		  B B
//		 C   C
//		  B B
//		   A
		
		String expected = format("  A%n B B%nC   C%n B B%n  A%n");
		diamondMaker.printDiamond('C', collector);
		String actual = stringFromByteArray();
		assertEquals(expected, actual);
	}
	
	@Test
	public void printsBigDiamond() {
		
		diamondMaker.printDiamond('Z', System.out);
	}
	
	///////////////////////////////////////////////////////
	// helper methods
	///////////////////////////////////////////////////////
	
	private String stringFromByteArray() {
		
		return new String( byteStream.toByteArray() );
	}

}
