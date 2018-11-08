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
	
		//because you do not know how many passes it will make 
	while(true){
		
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> SetCandidate = new ArrayList<String>();
		Object[] keys = null;
		//if it is the third pass then use second result
		if (FLAG==0){
			keys = Passtwo.SecondMap.keySet().toArray();
			FLAG = 1;
		}
		//else use the previous pass result
		else{
			keys = OtherMap.keySet().toArray();
		}
		//sort key
		Arrays.sort(keys);
		for (Object key : keys) {
			list.add((String) key);
		}
		//find candidate different from second pass
		SetCandidate = FindCandidate(list);
		//if no candidate then finish
		if (SetCandidate.size() == 0){
			break;
		}
		//clear map, very important
		OtherMap.clear();
		//same with pass two
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
		//out put
		Passone.output(OtherMap);
		
		//if the candidate number is 1, then it will not generate the next pass candidate so finish
		int CZN = SetCandidate.size();
		if (CZN==1){
			break;
		}
		
		
	}	
	System.out.println("finish");
	}
	
	//find candidate with pruning
	public static ArrayList<String> FindCandidate(ArrayList<String> list){
		//key list must be sorted
		ArrayList<String> SetCandidatetemp = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			for(int j = i+1; j<list.size();j++){
				//find two previous key set that the first n-1 are same and the last one is not same 
				String ifront = list.get(i).substring(0,list.get(i).length()-1);
				String iend = list.get(i).substring(list.get(i).length()-1,list.get(i).length());
				String jfront = list.get(j).substring(0,list.get(j).length()-1);
				String jend = list.get(j).substring(list.get(j).length()-1,list.get(j).length());
				if(ifront.equals(jfront)&&(iend.equals(jend)==false)){
					//merge the new candidate
					String NCandidate = list.get(i)+","+jend;
					SetCandidatetemp.add(NCandidate);
				}
				//i:A    j:B
				//if find this B set n-1 are not same with A, then the key set (n-1) after it will not same with A,because it sorted. so find the next A 
				if(ifront.equals(jfront)==false){
					break;
				}	
			}
		}
		return SetCandidatetemp;
	}

}
