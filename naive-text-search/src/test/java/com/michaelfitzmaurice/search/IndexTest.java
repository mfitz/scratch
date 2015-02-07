package com.michaelfitzmaurice.search;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.StringTokenizer;

import org.junit.Test;

public class IndexTest {
	
	
	
	@Test
	public void stripsPunctuationOffIndexedWords() throws Exception {
		URL fileUrl = 
			new Object().getClass().getResource("/wings-dove.txt");
		File file = new File( fileUrl.getPath() );
		BufferedReader reader = new BufferedReader( new FileReader(file) );
		String line = reader.readLine();
		System.out.println(line);
		
	}
	
	@Test
	public void tracksNumberOfIndexedDocuments() {
		fail("TODO");
	}
	
	@Test
	public void tracksNumberOfIndexedWords() {
		fail("TODO");
	}

}
