package com.hexlet_rebels.cipher_breaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Util {
	
	protected static double[] getTextFingerprint(String text) {
		char[] alphabet = VarHolder.getCurrentAlphabet();
		double[] textFingerprint = new double[alphabet.length];
		
		for(int i = 0; i < alphabet.length; i ++) {
			textFingerprint[i] = 1;
		}
		char[] textChars = text.toCharArray();
		
		int index;
		for(char c : textChars)
			if((index = Util.findCharInArray(alphabet, c)[0]) != -1)
				textFingerprint[index] += 1;
		
		for(double d : textFingerprint)
			d = d / textChars.length;
		return textFingerprint;
	}
	
	protected static LinkedHashMap<Integer[], Double> getDisplacement (double[] langFingerprint, double[] textFingerprint, int numberOfPossibleDisplacements) {
		if(langFingerprint.length == textFingerprint.length) {
			
			LinkedHashMap<Double, Integer> displacementsMap = new LinkedHashMap<Double, Integer>();
			
			double difference;
			for(int displacement = 0; displacement < langFingerprint.length; displacement ++) {
				difference = 0;
				for(int i = 0; i < langFingerprint.length; i++) {
					if(Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]) > difference)
						difference = Math.abs(textFingerprint[(i + displacement) % textFingerprint.length] - langFingerprint[i]);
				}
				displacementsMap.put(difference, displacement);
			}
			
			Integer[] sortedArrayOfDisplacements = getValues(sortMapByKeys(displacementsMap));
			
			
			LinkedHashMap<Integer[], Double> result = new LinkedHashMap<Integer[], Double>();
			result.put(numberOfPossibleDisplacements != Integer.MAX_VALUE ? Arrays.copyOf(sortedArrayOfDisplacements, numberOfPossibleDisplacements) : sortedArrayOfDisplacements, getKeyByValue(displacementsMap, sortedArrayOfDisplacements[0]));
			
			return result;
		} else {
			return null;
		}
	}
	
	public static String setDisplacement (String text, int displacement) {
		char[] alphabet = VarHolder.getCurrentAlphabet();
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
	
	protected static LinkedHashMap<Double, Integer> sortMapByKeys (LinkedHashMap<Double, Integer> displacementsMap) {
		
		ArrayList<Double> errorRates = new ArrayList<Double>(displacementsMap.keySet());
		errorRates.sort(new Comparator<Double> () {
			@Override
			public int compare(Double o1, Double o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		LinkedHashMap<Double, Integer> sortedDisplacementsMap = new LinkedHashMap<Double, Integer>();
		for(Double errorRate : errorRates) {
			sortedDisplacementsMap.put(errorRate, displacementsMap.get(errorRate));
		}
		return sortedDisplacementsMap;
	}
	
	protected static Integer[] getValues (LinkedHashMap<Double, Integer> map) {
		LinkedList<Integer> sortedListOfDisplacements = new LinkedList<Integer> (map.values());
		Object[] sortedArrayOfDisplacementsAsObjects = sortedListOfDisplacements.toArray();
		
		Integer[] sortedArrayOfDisplacements = new Integer[sortedArrayOfDisplacementsAsObjects.length];
		for(int i = 0; i < sortedArrayOfDisplacementsAsObjects.length; i ++) {
			sortedArrayOfDisplacements[i] = (Integer) sortedArrayOfDisplacementsAsObjects[i];
		}
		return sortedArrayOfDisplacements;
	}
	
	protected static Double getKeyByValue (LinkedHashMap<Double, Integer> map, Integer value) {
		for(Double key : map.keySet())
			if(map.get(key) == value)
				return key;
		return Double.MIN_VALUE;
	}
	
}
