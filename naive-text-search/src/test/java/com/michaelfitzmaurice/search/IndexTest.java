package com.michaelfitzmaurice.search;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.Test;

public class IndexTest {
	
	private FilenameFilter filenameFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			return name.endsWith(".txt");
		}
	};
	
	@Test
	public void tracksNumberOfIndexedDocuments() 
	throws Exception {
		
		File testFile = getFile("brown-fox.txt");
		File testDir = testFile.getParentFile();
		File[] testDocs = testDir.listFiles(filenameFilter);
		
		Index index = new Index(testDocs);
		assertEquals( "Unexpected number of documents indexed;", 
						testDocs.length, 
						index.indexedDocuments() );
	}
	
	@Test
	public void tracksNumberOfUniqueIndexedWords() throws IOException {
		
		File testDir = getFile("brown-fox.txt").getParentFile();
		File[] testDocs = testDir.listFiles(filenameFilter);
		Set<String> uniqueWords = new HashSet<String>();
		for (File file : testDocs) {
	        String fileWords = fileContent( file.getName() );
	        StringTokenizer tokenizer = new StringTokenizer(fileWords);
	        while ( tokenizer.hasMoreTokens() ) {
	        	uniqueWords.add( tokenizer.nextToken() );
	        }
        }
		
		Index index = new Index(testDocs);
		assertEquals( "Unexpected number of words indexed;", 
						uniqueWords.size(), 
						index.indexedWords() );
	}
	
	@Test
	public void retrievesAppropriateFilesForSingleWordSearch() 
	throws Exception {
		
		String filename = "brown-fox.txt";
		String searchTerm = "lazy";
		assertFileContainsAllWords(filename, searchTerm);
		
		File testFile = getFile(filename);
		Index index = new Index(testFile);
		
		File[] matches = index.find(searchTerm);
		assertNotNull(matches);
		String failMsg = 
			format("Search results are missing file %s;", 
					filename);
		assertTrue( failMsg, Arrays.asList(matches).contains(testFile) );
	}
	
	@Test
	public void retrievesAppropriateFilesForMultipleWordSearch() 
	throws Exception {
		
		String filename = "cat-mat.txt";
		String[] searchTerms = new String[] {"the", "cat"};
		assertFileContainsAllWords(filename, searchTerms);
		
		File testFile = getFile(filename);
		Index index = 
			new Index( testFile.getParentFile().listFiles(filenameFilter) );
		
		String searchTerm = buildCompoundSearchTerm(searchTerms);
		File[] matches = index.find(searchTerm);
		String failMsg = 
			format("Search results are missing expected file %s;", 
					filename);
		assertTrue( failMsg, Arrays.asList(matches).contains(testFile) );
	}
	
	@Test
	public void stripsPunctuationOffIndexedWords() throws Exception {
		
		String filename = "wings-dove.txt";
		assertTrue( "Test file does not contain punctuation term;",
					fileContent(filename).contains("dove!") );
		File file = getFile(filename);
		Index index = new Index(file);
//		
//		File[] matches = index.find("dove!");
//		assertNotNull(matches);
//		assertEquals(0, matches.length);
		
		File[] matches = index.find("dove");
		String failMsg = 
			format("Search results are missing file '%s';", 
					filename);
		assertTrue( failMsg, Arrays.asList(matches).contains(file) );
	}

	///////////////////////////////////////////////////////
	// helper methods
	///////////////////////////////////////////////////////
	
	private String fileContent(String filename) throws IOException {
		
		File file = getFile(filename);
		BufferedReader reader = new BufferedReader( new FileReader(file) );
		StringBuffer buffer = new StringBuffer();
		String line = reader.readLine();
		while (line != null) {
			buffer.append(line);
			buffer.append(System.getProperty("line.separator") );
			line = reader.readLine();
		}
		reader.close();
		
		return buffer.toString();
	}

	private File getFile(String filename) {
		
	    URL fileUrl = 
			new Object().getClass().getResource("/" + filename);
	    return new File( fileUrl.getPath() );
    }
	
	private void assertFileContainsAllWords(String filename,
            								String... searchTerms) 
    throws IOException {
		
	    String fileContent = fileContent(filename);
		for (int i = 0; i < searchTerms.length; i++) {
	        String searchTerm = searchTerms[i];
	        assertTrue( "Test file does not contain expected search term;", 
						fileContent.contains(searchTerm) );
        }
    }
	
	private String buildCompoundSearchTerm(String[] searchTerms) {
		
	    String searchTerm = "";
		
	    for (int i = 0; i < searchTerms.length; i++) {
			String searchWord = searchTerms[i];
			if (i != 0) {
				searchTerm += " ";
			}
			searchTerm += searchWord;
		}
		
	    return searchTerm;
    }

}
