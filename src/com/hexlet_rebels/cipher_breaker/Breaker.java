package com.hexlet_rebels.cipher_breaker;

import java.util.HashMap;

public class Breaker {
	
	public static String decryptCaesarCipher (String text) {
		
		//TODO move to starter method
		VarHolder.readFingerprintsFromFile("res/fingerprints");
		
		char[] alphabet = VarHolder.getLetters().get(VarHolder.getCurrentLanguage());
		double[] textFingerprint = new double[alphabet.length];
		
		for(double d : textFingerprint)
			d = 0;
		
		char[] textChars = text.toCharArray();
		
		for(char c : textChars) {
			textFingerprint[Util.findCharInArray(alphabet, c)] += 1;
		}
		
		for(double d : textFingerprint)
			d = d / textChars.length;
		
		//getting only one displacement out of many possible displacements
		int displacement = Util.getDisplacement(VarHolder.getFingerprints().get(VarHolder.getCurrentLanguage()), textFingerprint, 1)[0];
		
		return Util.setDisplacement(alphabet, text, -displacement);
	}
	
}
