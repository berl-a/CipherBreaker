package com.hexlet_rebels.cipher_breaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VarHolder {
	
	private static final String DEFAULT_TEXT_LANGUAGE = "English";

	private static String currentLanguage = DEFAULT_TEXT_LANGUAGE;
	
	private static ArrayList<String> languages = new ArrayList<String>();
	private static ArrayList<char[]> alphabets = new ArrayList<char[]>();
	private static ArrayList<double[]> fingerprints = new ArrayList<double[]>();
	
	public static void readFingerprintsFromFile(String url) {
		File file = new File(url);
		try {
			Scanner s = new Scanner(file);
			String language;
			String[] letters;
			String[] fingerprints;
			
			char[] charLetters;
			double[] doubleFingerprints;
			
			while(s.hasNext()) {
				try {
					language = s.nextLine();
					letters = s.nextLine().split(" ");
					fingerprints = s.nextLine().split(" ");
					charLetters = new char[letters.length];
					for(int i = 0; i < letters.length; i++)
						charLetters[i] = String.valueOf(letters[i]).toCharArray()[0];
					doubleFingerprints = new double[fingerprints.length];
					for(int i = 0; i < fingerprints.length; i++)
						doubleFingerprints[i] = Double.valueOf(fingerprints[i]);
					getLanguages().add(language);
					VarHolder.getAlphabets().add(charLetters);
					VarHolder.getFingerprints().add(doubleFingerprints);
				} catch (NullPointerException e) {
					System.out.println("Wrong file format");
				}
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public static ArrayList<String> getLanguages() {
		return languages;
	}

	public static void setLanguages(ArrayList<String> languages) {
		VarHolder.languages = languages;
	}

	public static String getCurrentLanguage() {
		return VarHolder.currentLanguage;
	}

	public static void setCurrentLanguage(String currentLanguage) {
		VarHolder.currentLanguage = currentLanguage;
	}

	public static ArrayList<char[]> getAlphabets() {
		return alphabets;
	}
	public static char[] getCurrentAlphabet() {
		return getAlphabets().get(getLanguages().indexOf(getCurrentLanguage()));
	}

	public static void setAlphabets(ArrayList<char[]> alphabets) {
		VarHolder.alphabets = alphabets;
	}

	public static ArrayList<double[]> getFingerprints() {
		return fingerprints;
	}
	
	public static double[] getCurrentFingerprint() {
		return getFingerprints().get(getLanguages().indexOf(getCurrentLanguage()));
	}

	public static void setFingerprints(ArrayList<double[]> fingerprints) {
		VarHolder.fingerprints = fingerprints;
	}
	
}
