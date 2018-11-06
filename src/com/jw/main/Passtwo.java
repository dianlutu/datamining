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
		for(int i=0;i<list.size();i++){
			for(int j = i+1; j<list.size();j++){
				
				String Candidate = list.get(i)+","+list.get(j);
				SetCandidate.add(Candidate);
			}
		}
//		for (String Settemp : SetCandidate) {
//			System.out.println(Settemp);
//		}
		for(int i=0;i<SetCandidate.size();i++){
			int SupportNumber = 0;
			String[] temp = SetCandidate.get(i).split(",");
			ArrayList<String> arrC = new ArrayList<String>();
			for (String temp1 : temp) {
				arrC.add(temp1);
			}
			String line = null;
			BufferedReader file = new BufferedReader(new FileReader(Info.recordFilePath));
			line = file.readLine();//skip first line
			int count = 0;
			while (count < Info.BAG_AMOUNT){
				line = file.readLine();
				String[] data = line.split(",");
				ArrayList<String> arrD = new ArrayList<String>();
				for (String data1 : data) {
					arrD.add(data1);
				}
				if (arrD.containsAll(arrC)){
					SupportNumber++;
				}
				count++;
			}
			
			if(SupportNumber>=Info.SUPPORT_NUMBER){
				SecondMap.put(SetCandidate.get(i), SupportNumber);
			}	
			file.close();
		}
		
		Passone.output(SecondMap);
		
		
	}
	
	
	
	
}
