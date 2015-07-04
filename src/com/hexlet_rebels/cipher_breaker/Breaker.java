package com.hexlet_rebels.cipher_breaker;


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
	
	public static String encryptByPolyalphabeticCipher (String text, String key) {
		char[] startCharArray = text.toCharArray();
		char[] keyCharArray = key.toCharArray();
		StringBuilder encryptedText = new StringBuilder();
		for(int nOfLetterInText = 0, nOfLetterInKey = 0; nOfLetterInText < text.length(); nOfLetterInText ++, nOfLetterInKey = (nOfLetterInKey + 1) % key.length())
			encryptedText.append(Util.setDisplacement(VarHolder.getCurrentAlphabet(), String.valueOf(startCharArray[nOfLetterInText]), Util.findCharInArray(VarHolder.getCurrentAlphabet(), keyCharArray[nOfLetterInKey])[0] != -1 ? Util.findCharInArray(VarHolder.getCurrentAlphabet(), keyCharArray[nOfLetterInKey])[0] + 1 : 0));//displacement is staring from 1 (eg. if letter is 'a', then displacement is 1)
		return encryptedText.toString();
	}
	
}
