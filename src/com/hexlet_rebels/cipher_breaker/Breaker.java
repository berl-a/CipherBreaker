package com.hexlet_rebels.cipher_breaker;

import java.util.HashMap;
import java.util.LinkedList;

public class Breaker {
	
	public static String decryptCaesarCipher (String text) {
		
		//TODO move to starter method
//		VarHolder.readFingerprintsFromFile("res/fingerprints");
		
		LinkedList<char[]> list = new LinkedList<char[]>(VarHolder.getLetters().values());
		LinkedList<double[]> list1 = new LinkedList<double[]>(VarHolder.getFingerprints().values());
		char[] alphabet = list.get(0);
		double[] textFingerprint = new double[alphabet.length];
		
		for(int i = 0; i < alphabet.length; i ++) {
			textFingerprint[i] = 1;
		}
		char[] textChars = text.toCharArray();
		
		for(char c : textChars) {
			textFingerprint[Util.findCharInArray(alphabet, c)] += 1;
		}
		
		for(double d : textFingerprint)
			d = d / textChars.length;
		
		//getting only one displacement out of many possible displacements
		int displacement = Util.getDisplacement(list1.get(0), textFingerprint, 1)[0];
		
		return Util.setDisplacement(alphabet, text, -displacement);
	}
	
}
