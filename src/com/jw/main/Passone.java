package com.jw.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jw.main.Info;

public class Passone {
	
	public static HashMap<Integer, Integer> FirstMap = new HashMap();

	
	//get Bag_amount and Support_number from input
	public static void init(BufferedReader file) throws Exception {
		String line;
		if ((line = file.readLine()) != null) {
			String[] info = line.split("\\s+");

			// get total number of bags
			Info.BAG_AMOUNT = Integer.valueOf(info[0]);

			// get support number
			Info.SUPPORT_NUMBER = Integer.valueOf(info[1]);
			if (Info.SUPPORT_NUMBER<3){
				throw new Exception("support number is too small");
			}
		}

	}
	
	public static void run() throws Exception{
		//read file
		BufferedReader file = new BufferedReader(new FileReader(Info.recordFilePath));
		
		init(file);
		
		String line = null;
		int[] set = new int[100];
		int count = 0;
		//count each one, result into array
		while (count < Info.BAG_AMOUNT){
			line = file.readLine();
			String[] data = line.split(",");
			for(int i=1;i<data.length;i++){
				int ItemStringtoInt = Integer.parseInt(data[i]);
                set[ItemStringtoInt]++;
            }
			count++;
		}
		//put the exceed support number item into hashmap{key£ºitem£¬value£ºnumber}
		for(int i = 0;i < set.length ;i++){
			if (set[i]>=Info.SUPPORT_NUMBER){
				FirstMap.put(i, set[i]);
				Info.FIRSTFREQUENTSETNUMBER++;
			}
		}
		//output
		output(FirstMap);
	}
	
	//output
	public static void output(HashMap Map) throws Exception{
		
		//Set<Entry<Object, Integer>> kv =Map.entrySet();

		Object[] key_arr = Map.keySet().toArray();   
		//sort
		Arrays.sort(key_arr); 
		for (Object key : key_arr) {
			System.out.println("{"+key+"}-"+Map.get(key));
		}	
		
	}
	
	
	
}
