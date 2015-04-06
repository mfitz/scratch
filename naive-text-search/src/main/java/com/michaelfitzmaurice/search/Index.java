package com.michaelfitzmaurice.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Index {
	
	private final int numberOfDocuments;
	private final int numerOfWords;
	private final Map<String, Set<File>> invertedIndex;

	public Index(File... files) throws IOException {
		numberOfDocuments = files.length;
		invertedIndex = new HashMap< String, Set<File> >();
		numerOfWords = buildInvertedIndex(files);
	}
	
	private int buildInvertedIndex(File... files) 
	throws IOException {
		
		for (File file : files) {
	        StringTokenizer tokenizer = 
				new StringTokenizer( fileContentAsString(file) );
			while ( tokenizer.hasMoreTokens() ) {
				String word = tokenizer.nextToken();
				Set<File> filesContainingWord = invertedIndex.get(word);
				if (filesContainingWord == null) {
					filesContainingWord = new HashSet<File>();
					invertedIndex.put(word, filesContainingWord);
				}
				filesContainingWord.add(file);
			}
        }
		
		return invertedIndex.keySet().size();
    }
	
	private String fileContentAsString(File file) 
	throws IOException {
		
		BufferedReader reader = new BufferedReader( new FileReader(file) );
		StringBuffer fileContent = new StringBuffer();
		String nextLine = reader.readLine();
		while (nextLine != null) {
			fileContent.append(nextLine);
			fileContent.append( System.getProperty("line.separator") );
			nextLine = reader.readLine();
		}
		reader.close();
		
		return fileContent.toString();
	}
	
	public File[] find(String text) {
		
		Set<File> filesMatchingAllWords = new HashSet<File>();
		StringTokenizer tokenizer = new StringTokenizer(text);
		while ( tokenizer.hasMoreTokens() ) {
			Set<File> matchingFileSet = 
				invertedIndex.get( tokenizer.nextToken() );
			if ( filesMatchingAllWords.isEmpty() && matchingFileSet != null) {
				filesMatchingAllWords.addAll(matchingFileSet);
			}
			filesMatchingAllWords.retainAll(matchingFileSet);
		}
		List<File> fileList = new ArrayList<File>(filesMatchingAllWords);
		
		return fileList.toArray( new File[fileList.size()] );
	}
	
	public int indexedDocuments() {
		return numberOfDocuments;
	}
	
	public int indexedWords() {
		return numerOfWords;
	}
}
