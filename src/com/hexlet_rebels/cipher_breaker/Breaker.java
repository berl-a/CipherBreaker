package com.hexlet_rebels.cipher_breaker;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class Breaker {
	
	private static final double PERCENT_OF_HALF_OF_TEXT_NEEDED_FOR_ENCRYPTION = 0.5;

	public static String decryptCaesarCipher (String text) {
		
		double[] textFingerprint = Util.getTextFingerprint(text);
		
		//getting only one displacement out of many possible displacements
		LinkedHashMap<Integer[], Double> result = Util.getDisplacement(VarHolder.getCurrentFingerprint(), textFingerprint, 10);
		
		if(result != null) {
			int bestDisplacement = new LinkedList<Integer[]>(result.keySet()).get(0)[0];
			return Util.setDisplacement(text, -bestDisplacement);
		} else {
			System.err.println("Lengths of language fingerprint and text fingerprint are not the same");
			return text;
		}
	}
	
	public static String decryptPolyalphabeticCipher (String text) {
		char[] textChars = text.toCharArray();
		
		LinkedHashMap<Double, Integer> periodsMap = new LinkedHashMap<Double, Integer>();
		char[] textPartChars = null;
		String textPart = null;;
		for(int period = 1; period < (text.length() / 2) * PERCENT_OF_HALF_OF_TEXT_NEEDED_FOR_ENCRYPTION; period ++) {
			textPartChars = new char[(int)((text.length() - text.length() % period) / period)];
			textPart = new String();                                      //Needed for not clear enouch number of chars to add
			for(int indexInPartCharsArray = 0, indexInTextCharsArray = 0; indexInPartCharsArray < textPartChars.length && indexInTextCharsArray < text.length(); indexInPartCharsArray ++, indexInTextCharsArray += period) {
//				System.out.println("IndexInPart is " + indexInPartCharsArray + ", numberOfChar = " + indexInTextCharsArray);
				try{
					textPartChars[indexInPartCharsArray] = textChars[indexInTextCharsArray];
				}catch(Exception e) {
					System.err.println(indexInPartCharsArray + " " + indexInTextCharsArray);
				}
			}
			textPart = String.copyValueOf(textPartChars);
			
			LinkedHashMap<Integer[], Double> result = Util.getDisplacement(VarHolder.getCurrentFingerprint(), Util.getTextFingerprint(textPart), 1);
			
			if(result != null) {
				double errorRate = result.get(new LinkedList<Integer[]>(result.keySet()).get(0));
				periodsMap.put(errorRate, period);
				//Error rates are decreacing all the time, and IDK is is good or not
//				System.out.println("Putting error rate for period: " + errorRate + " for " + period);
			} else {
				System.err.println("Lengths of language fingerprint and text fingerprint are not similar");
				return text;
			}
		}
		
		LinkedList<Double> errorRates = new LinkedList<Double>(periodsMap.keySet());
//		for(Double d : errorRates) {
//			System.out.println(d);
//		}
		errorRates.sort(new Comparator<Double> () {
			@Override
			public int compare(Double o1, Double o2) {
				if (o1 > o2) return 1;
				else if (o1 == o2) return 0;
				else return -1;
			}
		});
		int period = periodsMap.get(errorRates.get(0));
		System.out.println("period is " + periodsMap.get(errorRates.get(0)));
		
		
		LinkedHashMap<Integer, Integer> displacementPerAdditionMap = new LinkedHashMap<Integer, Integer>();
		
		for(int addition = 0; addition < period; addition ++) {
			int size = (int)((text.length() - text.length() % period) / period - addition);
			if(size < 0) size += addition;
			textPartChars = new char[size];
			textPart = new String();
			for(int indexInPartCharsArray = 0, indexInTextCharsArray = addition; indexInPartCharsArray < textPartChars.length && indexInTextCharsArray + addition < text.length(); indexInPartCharsArray ++, indexInTextCharsArray += period) {
				try{
					textPartChars[indexInPartCharsArray] = textChars[indexInTextCharsArray];
				}catch(Exception e) {
					System.err.println(indexInPartCharsArray + " " + indexInTextCharsArray);
				}
			}
			textPart = String.copyValueOf(textPartChars);
			
			LinkedHashMap<Integer[], Double> result = Util.getDisplacement(VarHolder.getCurrentFingerprint(), Util.getTextFingerprint(textPart), 10);
			
			if(result != null) {
				int bestDisplacement = new LinkedList<Integer[]>(result.keySet()).get(0)[0];
				displacementPerAdditionMap.put(addition, bestDisplacement);
			} else {
				System.err.println("Lengths of language fingerprint and text fingerprint are not the same");
				return text;
			}
		}
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < textChars.length; i ++) {
			builder.append(Util.setDisplacement(String.valueOf(textChars[i]), displacementPerAdditionMap.get(i % period)));
		}
		
		return builder.toString();
	}
	
	public static String encryptByPolyalphabeticCipher (String text, String key) {
		char[] startCharArray = text.toCharArray();
		char[] keyCharArray = key.toCharArray();
		StringBuilder encryptedText = new StringBuilder();
		for(int nOfLetterInText = 0, nOfLetterInKey = 0; nOfLetterInText < text.length(); nOfLetterInText ++, nOfLetterInKey = (nOfLetterInKey + 1) % key.length())
			encryptedText.append(Util.setDisplacement(String.valueOf(startCharArray[nOfLetterInText]), Util.findCharInArray(VarHolder.getCurrentAlphabet(), keyCharArray[nOfLetterInKey])[0] != -1 ? Util.findCharInArray(VarHolder.getCurrentAlphabet(), keyCharArray[nOfLetterInKey])[0] : 0));//displacement is staring from 0 (eg. if letter is 'a', then displacement is 0)
		return encryptedText.toString();
	}
	
}
