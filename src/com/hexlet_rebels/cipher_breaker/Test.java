package com.hexlet_rebels.cipher_breaker;

import java.util.HashMap;

public class Test {

	public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//		String text = s.nextLine();
//		System.out.println(text);
//		System.out.println();
//		//Language fingerprint has to be full
//		text = Util.setDisplacement(VarHolder.getLetters().get(VarHolder.getCurrentLanguage()), text, 1);
//		System.out.println(text);
//		System.out.println(Breaker.decryptCaesarCipher(text));
//		s.close();
		
		HashMap<Double, Integer> map = new HashMap<Double, Integer>();
		map.put(0.1, 1);
		map.put(100d, 2);
		map.put(0d, 3);
		map = Util.sortHashMap(map);
		System.out.println("sdf");
		for(Double key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
		
	}

}
