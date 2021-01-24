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

public class MultiThread {

    
	public static void main(String[] args) throws IOException, InterruptedException {
				
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
	
		

		Thread t[] = new Thread[10];
		int i;
		for(i=0;i<10;i++) {
			t[i] = new CountThread(list.size()/10, parts, i);
			t[i].start();
		}
		
		
		
	}
	
	
}

