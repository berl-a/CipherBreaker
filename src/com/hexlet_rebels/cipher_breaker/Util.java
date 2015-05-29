package com.hexlet_rebels.cipher_breaker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Util {
	
	public static Integer[] getDisplacement (double[] langFingerprint, double[] textFingerprint, int numberOfPossibleDisplacements) {
		if(langFingerprint.length == textFingerprint.length) {
			
			HashMap<Double, Integer> displacements = new HashMap<Double, Integer>();
			
			double minGlobalDifference = 0, maxLocalDifference = 0;
//			int displacementForMinGlobalDifference = 0;
			
			for(int displacement = 0; displacement < langFingerprint.length; displacement ++) {
				maxLocalDifference = 0;
				for(int i = 0; i < langFingerprint.length; i++) {
					if(Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]) > maxLocalDifference) {
						maxLocalDifference = Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]);
					}
				}
				if(maxLocalDifference < minGlobalDifference) {
					minGlobalDifference = maxLocalDifference;
//					displacementForMinGlobalDifference = displacement;
				}
			}
			
			
			return displacements.keySet().toArray(new Integer[displacements.keySet().size()]);
			
		} else {
			return new Integer[]{Integer.MIN_VALUE};
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
	
	public static int findCharInArray (char[] letters, char letter) {
		int i;
		for(i = 0; i < letters.length; i ++)
			if(letters[i] == letter)
				break;
		return i;
	}
	
	public static int[] sortNumbers (int[] numbers) {
		
		//TODO Remove default return
		return null;
	}
	
	//TODO make method private
	public static HashMap<Double, Integer> sortHashMap (HashMap<Double, Integer> map) {
		
		ArrayList<Double> keys = new ArrayList<Double>(map.keySet());
		keys.sort(new Comparator<Double> () {
			@Override
			public int compare(Double o1, Double o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		
		HashMap<Double, Integer> sortedMap = new HashMap<Double, Integer>();
		for(Double key : keys)
			sortedMap.put(key, map.get(key));
		
		
		return sortedMap;
	}
	
}
