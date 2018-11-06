package com.jw.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Passthreeandmore {

	public static HashMap<String, Integer> OtherMap = new HashMap();
	
	public static int FLAG = 0;
	
	public static void MultipleFerquentSet() throws Exception{
		
	while(true){
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> SetCandidate = new ArrayList<String>();
		Object[] keys = null;
		if (FLAG==0){
			keys = Passtwo.SecondMap.keySet().toArray();
			FLAG = 1;
		}
		else{
			keys = OtherMap.keySet().toArray();
		}
		Arrays.sort(keys);
		for (Object key : keys) {
			list.add((String) key);
		}	
		SetCandidate = FindCandidate(list);
		if (SetCandidate.size() == 0){
			break;
		}
		OtherMap.clear();	
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
				OtherMap.put(SetCandidate.get(i), SupportNumber);
			}
			
			file.close();
		}
		Passone.output(OtherMap);
		
		
		int CZN = SetCandidate.size();
		if (CZN==1){
			break;
		}
		
		
	}	
	System.out.println("finish");
	}
	public static ArrayList<String> FindCandidate(ArrayList<String> list){
		ArrayList<String> SetCandidatetemp = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			for(int j = i+1; j<list.size();j++){
				
				String ifront = list.get(i).substring(0,list.get(i).length()-1);
				String iend = list.get(i).substring(list.get(i).length()-1,list.get(i).length());
				String jfront = list.get(j).substring(0,list.get(j).length()-1);
				String jend = list.get(j).substring(list.get(j).length()-1,list.get(j).length());
				if(ifront.equals(jfront)&&(iend.equals(jend)==false)){
					String NCandidate = list.get(i)+","+jend;
					SetCandidatetemp.add(NCandidate);
				}
				if(ifront.equals(jfront)==false){
					break;
				}	
			}
		}
		return SetCandidatetemp;
	}

}
