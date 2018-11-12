package com.jw.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Passtwo {

	public static HashMap<String, Integer> SecondMap = new HashMap();
	
	public static void FindFrequentItems() throws Exception{
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		ArrayList<String> SetCandidate = new ArrayList<String>();
		
		Set<Integer> keys = Passone.FirstMap.keySet();
		
		for (Object key : keys) {
			list.add((Integer) key);
		}
		//making data pairs as the pass two candidate by using pass one result
		for(int i=0;i<list.size();i++){
			for(int j = i+1; j<list.size();j++){
				String Candidate = null;
				if(list.get(i)<10 && list.get(j)<10){
					Candidate = "0"+list.get(i)+","+"0"+list.get(j);
				}
				else if(list.get(i)<10 && list.get(j)>=10){
					Candidate = "0"+list.get(i)+","+list.get(j);
				}
				else if(list.get(i)>=10 && list.get(j)<10){
					Candidate = list.get(i)+","+"0"+list.get(j);
				}
				else{
					Candidate = list.get(i)+","+list.get(j);
				}
				
				SetCandidate.add(Candidate);
			}
		}
//		for (String Settemp : SetCandidate) {
//			System.out.println(Settemp);
//		}
		//find number of each candidate in the whole baskets and find out the good one
		for(int i=0;i<SetCandidate.size();i++){
			//present support number
			int SupportNumber = 0;
			String[] temp = SetCandidate.get(i).split(",");
			//split candidate set into ArrayList
			ArrayList<String> arrC = new ArrayList<String>();
			for (String temp1 : temp) {
				arrC.add(temp1);
			}
			String line = null;
			//read file
			BufferedReader file = new BufferedReader(new FileReader(Info.recordFilePath));
			line = file.readLine();//skip first line£¨50 5£©
			
			int count = 0;
			//read every line of the input
			while (count < Info.BAG_AMOUNT){
				line = file.readLine();
				String[] data = line.split(",");
				//split
				ArrayList<String> arrD = new ArrayList<String>();
				
				for (String data1 : data) {
					if(data1.length()==1)
					{
						arrD.add("0"+data1);
					}
					else{
						arrD.add(data1);
					}
				}
				//if this basket contains all the data of this candidate, which mains that this candidate occurred in this basket
				if (arrD.containsAll(arrC)){
					SupportNumber++;
				}
				count++;
			}
			//find the good one and put it into secondmap
			if(SupportNumber>=Info.SUPPORT_NUMBER){
				SecondMap.put(SetCandidate.get(i), SupportNumber);
			}
			//close file to insure that next time will not read the file from line (52)
			file.close();
		}
		//output
		Passone.output(SecondMap);
		
		
	}
	
	
	
	
}
