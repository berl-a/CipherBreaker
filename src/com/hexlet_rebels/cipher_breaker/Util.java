package com.hexlet_rebels.cipher_breaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Util {
	
	public static Integer[] getDisplacement (double[] langFingerprint, double[] textFingerprint, int numberOfPossibleDisplacements) {
		if(langFingerprint.length == textFingerprint.length) {
			
			LinkedHashMap<Double, Integer> displacements = new LinkedHashMap<Double, Integer>();
			
			double maxLocalDifference = 0;
			
			for(int displacement = 0; displacement < langFingerprint.length; displacement ++) {
				maxLocalDifference = 0;
				for(int i = 0; i < langFingerprint.length; i++) {
					if(Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]) > maxLocalDifference) {
						maxLocalDifference = Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]);
					}
				}
				displacements.put(maxLocalDifference, displacement);
			}
			
			displacements = sortHashMap(displacements);
			
			LinkedList<Integer> displacementsList = new LinkedList<Integer> (displacements.values());
			Object[] disArr = displacementsList.toArray();
			Integer[] displacementsArray = new Integer[disArr.length];
			for(int i = 0; i < disArr.length; i ++) {
				displacementsArray[i] = (Integer) disArr[i];
			}
			
			return Arrays.copyOf(displacementsArray, numberOfPossibleDisplacements);
		} else {
			return new Integer[]{Integer.MIN_VALUE};
		}
	}
	
	public static String setDisplacement (char[] alphabet, String text, int displacement) {
		System.out.println("have to set displacement = " + displacement);
		displacement = alphabet.length - 1 + displacement;
		boolean upperCase;
		
		String newText = "";
		char[] dividedText = text.toCharArray();
		
		for(char c : dividedText) {
			upperCase = false;
			boolean isLetter = findCharInArray(alphabet, c)[0] != -1 ? true : false;
			if(isLetter) {
				int index = findCharInArray(alphabet, c)[0];
				if(index != -1) {
					if(findCharInArray(alphabet, c)[1] == 1) {
						upperCase = true;
					}
				}
				int newIndex = (index + displacement) % (alphabet.length - 1);
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
	
	public static int[] findCharInArray (char[] letters, char letter) {
		int i;
		boolean found = false;
		boolean upperCase = false;
		for(i = 0; i < letters.length; i ++)
			if(letters[i] == letter) {
				found = true;
				upperCase = false;
				break;
			} else if(String.valueOf(letters[i]).toUpperCase().charAt(0) == letter) {
				found = true;
				upperCase = true; 
				break;
			}
		return new int[]{(found ? i : -1), (upperCase ? 1 : 0)};
	}
	
	private static LinkedHashMap<Double, Integer> sortHashMap (LinkedHashMap<Double, Integer> map) {
		
		ArrayList<Double> keys = new ArrayList<Double>(map.keySet());
		keys.sort(new Comparator<Double> () {
			@Override
			public int compare(Double o1, Double o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		LinkedHashMap<Double, Integer> sortedMap = new LinkedHashMap<Double, Integer>();
		for(Double key : keys) {
			sortedMap.put(key, map.get(key));
		}
		return sortedMap;
	}
	
}
