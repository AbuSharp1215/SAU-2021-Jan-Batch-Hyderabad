package com.sharp.main;

import java.io.*;

public class SingleThreaded {
	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		File file = new File("Book.txt"); 
        FileInputStream fileStream = new FileInputStream(file); 
        InputStreamReader input = new InputStreamReader(fileStream); 
        BufferedReader reader = new BufferedReader(input);
        
        String line; 
        
        int countWord = 0; 
        int sentenceCount = 0; 
        int paragraphCount = 1; 
        
        while((line = reader.readLine()) != null) 
        { 
            if(line.equals("")) 
            { 
                paragraphCount++; 
            } 
            else {                   
                
                String[] wordList = line.split("\\s+"); 
                  
                countWord += wordList.length; 
                  
                
                String[] sentenceList = line.split("[!?.:]+"); 
                  
                sentenceCount += sentenceList.length; 
            } 
        } 
        System.out.println("Total word count = " + countWord); 
        System.out.println("Total number of sentences = " + sentenceCount); 
        System.out.println("Total Number of paragraphs = " + paragraphCount); 
        long end = System.currentTimeMillis();
        System.out.println("Time taken in single Thread "+(end - start) + " ms");
	}
}
