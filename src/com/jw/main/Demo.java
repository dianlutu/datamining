package com.jw.main;

public class Demo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		try {
			Passone.run();
			Passtwo.FindFrequentItems();
			Passthreeandmore.MultipleFerquentSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("running time=" + (System.nanoTime() - startTime));
	}

}

