package com.hexlet_rebels.cipher_breaker;

import java.util.HashMap;
import java.util.LinkedList;

public class Breaker {
	
	public static String decryptCaesarCipher (String text) {
		
		//TODO move to starter method
//		VarHolder.readFingerprintsFromFile("res/fingerprints");
		
		char[] alphabet = VarHolder.getCurrentAlphabet();
		double[] textFingerprint = new double[alphabet.length];
		
		for(int i = 0; i < alphabet.length; i ++) {
			textFingerprint[i] = 1;
		}
		char[] textChars = text.toCharArray();
		
		int index;
		for(char c : textChars) {
			if((index = Util.findCharInArray(alphabet, c)[0]) != -1) {
				try {
					textFingerprint[index] += 1;
				} catch (Exception e) {
					System.err.println("err: " + c);
				}
			}
		}
		
		for(double d : textFingerprint)
			d = d / textChars.length;
		
		//getting only one displacement out of many possible displacements
		
		int displacement = Util.getDisplacement(VarHolder.getCurrentFingerprint(), textFingerprint, 10)[0];
		return Util.setDisplacement(alphabet, text, -displacement);
	}
	
}
