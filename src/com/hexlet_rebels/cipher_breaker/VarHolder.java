package com.hexlet_rebels.cipher_breaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VarHolder {
	
	private static ArrayList<String> languages;
	private static HashMap<String, char[]> letters;
	private static HashMap<String, double[]> fingerprints;
	
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
					VarHolder.getLetters().put(language, charLetters);
					VarHolder.getFingerprints().put(language, doubleFingerprints);
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

	public static HashMap<String, char[]> getLetters() {
		return letters;
	}

	public static void setLetters(HashMap<String, char[]> letters) {
		VarHolder.letters = letters;
	}

	public static HashMap<String, double[]> getFingerprints() {
		return fingerprints;
	}

	public static void setFingerprints(HashMap<String, double[]> fingerprints) {
		VarHolder.fingerprints = fingerprints;
	}
	
}
