package com.hexlet_rebels.cipher_breaker;

public class Util {
	
	public static int getDisplacement (double[] langFingerprint, double[] textFingerprint) {
		if(langFingerprint.length == textFingerprint.length) {
			
			double minGlobalDifference = 0, maxLocalDifference = 0;
			int displacementForMinGlobalDifference = 0;
			
			for(int displacement = 0; displacement < langFingerprint.length; displacement ++) {
				maxLocalDifference = 0;
				for(int i = 0; i < langFingerprint.length; i++) {
					if(Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]) > maxLocalDifference) {
						maxLocalDifference = Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]);
					}
				}
				if(maxLocalDifference < minGlobalDifference) {
					minGlobalDifference = maxLocalDifference;
					displacementForMinGlobalDifference = displacement;
				}
			}
			
			return displacementForMinGlobalDifference;
			
		} else {
			return Integer.MIN_VALUE;
		}
	}
	
	public static String setDisplacement (char[] alphabet, String text, int displacement) {
		
		boolean upperCase = false;
		
		String newText = "";
		char[] dividedText = text.toCharArray();
		
		for(char c : dividedText) {
			boolean isLetter = false;
			for(char letter : alphabet)
				if(letter == String.valueOf(c).toLowerCase().toCharArray()[0])
					isLetter = true;
			if(isLetter) {
				int index = 0;
				for(index = 0; index < alphabet.length; index ++) {
					if(c == alphabet[index]) {
						break;
					} else if(c == String.valueOf(alphabet[index]).toUpperCase().charAt(0)) {
						upperCase = true;
						break;
					}
				}
				
				int newIndex = (index + displacement) % alphabet.length;
				if(!upperCase)
					newText += alphabet[newIndex];
				else
					newText += String.valueOf(alphabet[newIndex]).toUpperCase().charAt(0);
			} else {
				newText += c;
			}
		}
		
		return newText;
		
	}
	
}
