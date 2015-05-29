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
//			Integer[] displacementsArray = (Integer[]) displacementsList.toArray();
			
			return Arrays.copyOf(displacementsArray, numberOfPossibleDisplacements);
		} else {
			return new Integer[]{Integer.MIN_VALUE};
		}
	}
	
	public static String setDisplacement (char[] alphabet, String text, int displacement) {
		displacement = alphabet.length - 1 + displacement;
		System.out.println("inside");
		boolean upperCase = false;
		
		String newText = "";
		char[] dividedText = text.toCharArray();
		
		for(char c : dividedText) {
			boolean isLetter = false;
			for(char letter : alphabet)
				if(letter == String.valueOf(c).toLowerCase().toCharArray()[0])
					isLetter = true;
			if(isLetter) {
				//TODO problematic place
				int index = 0;
				for(index = 0; index < alphabet.length; index ++) {
					if(c == alphabet[index]) {
						break;
					} else if(c == String.valueOf(alphabet[index]).toUpperCase().charAt(0)) {
						upperCase = true;
						break;
					}
				}
				System.out.println("new index = " + ((index + displacement) % alphabet.length));
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
	
	//TODO make method private
	public static LinkedHashMap<Double, Integer> sortHashMap (LinkedHashMap<Double, Integer> map) {
		
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
			System.out.println(key);
			sortedMap.put(key, map.get(key));
		}
		return sortedMap;
	}
	
}
