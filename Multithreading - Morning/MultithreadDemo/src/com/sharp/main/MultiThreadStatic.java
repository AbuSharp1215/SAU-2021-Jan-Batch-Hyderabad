package com.sharp.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadStatic {

    static long start = System.currentTimeMillis();
	public static void main(String[] args) throws IOException, InterruptedException {
		
		MultiThreadStatic s = new MultiThreadStatic();
		
		
		File file = new File("Book.txt"); 
        FileInputStream fileStream = new FileInputStream(file); 
        InputStreamReader input = new InputStreamReader(fileStream); 
		BufferedReader br = new BufferedReader(input);
		List<String> list = new ArrayList<String>();
		String line;

		while((line = br.readLine()) != null){
		    list.add(line);
		}
		br.close();
		
		
		List<ArrayList<String>> parts  = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < list.size()/10; i++){
		  parts.add(new ArrayList<String>());
		  for(int j =0; j < 10; j++){
		    parts.get(i).add(list.get(i*10+j));
		  }
		}
	
		Thread t1 = new Thread(new Runnable() {
		 
			@Override
			public void run() {
				s.resource(list.size()/10, parts, 0);

			}
		});
		
	
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				s.resource(list.size()/10, parts, 1);
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 2);
			}
		});
		
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 3);
			}
		});

		Thread t5 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 4);
			}
		});

		Thread t6 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 5);
			}
		});

		Thread t7 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 6);
			}
		});

		Thread t8 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 7);
			}
		});
		
		Thread t9 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 8);
			}
		});
		
		Thread t10 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				s.resource(list.size()/10, parts, 9);
				
			}
		});
		
		

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		
		
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

