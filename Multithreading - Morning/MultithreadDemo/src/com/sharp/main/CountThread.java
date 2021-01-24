package com.sharp.main;

import java.util.ArrayList;
import java.util.List;

public class CountThread extends Thread{
	static long start = System.currentTimeMillis();
	MultiThread m;
	int size;
	List<ArrayList<String>> parts;
	int index;
	public CountThread(int size, List<ArrayList<String>> parts, int index){
		m = new MultiThread();
		this.size = size;
		this.parts = parts;
		this.index = index;
	}
	
	@Override
	public void run() {
		resource(size, parts, index);
	}
	
	public void resource(int size, List<ArrayList<String>> lines, int index) {
		int w[] = new int[10];
		int s[] = new int[10];
		int p[] = new int[10];
		
		
		for(int i = 0; i < size; i++){
		    if(lines.get(i).get(index).equals("")) 
            { 
                p[index]++; 
            } 
		    else {                   
                String[] wordList = lines.get(i).get(index).split("\\s+"); 
                  
                w[index] += wordList.length; 
                  
                String[] sentenceList = lines.get(i).get(index).split("[!?.:]+"); 
                  
                s[index] += sentenceList.length; 
            } 
		  }
		System.out.println("Total word count in Thread " + (index+1) +" = "+ (w[index])); 
        System.out.println("Total number of sentences in Thread " + (index+1) +" = "+ (s[index])); 
        System.out.println("Total Number of paragraphs in Thread " + (index+1) +" = "+ (p[index])); 
		long end = System.currentTimeMillis();
        System.out.println("Time taken for each thread "+(end - start) + " ms");
		
	}
}
