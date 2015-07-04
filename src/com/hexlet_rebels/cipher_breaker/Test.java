package com.hexlet_rebels.cipher_breaker;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		//TODO move to starter method
		VarHolder.readFingerprintsFromFile("res/fingerprints");
		Scanner s = new Scanner(System.in);
		String text = s.nextLine();
		
		//Language fingerprint has to be full
		text = Breaker.encryptByPolyalphabeticCipher(text, "a");
		System.out.println(text);
		System.out.println(Breaker.decryptPolyalphabeticCipher(text));
		s.close();
		
	}

}
/*Ukrainian
� � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �
0.1 0.1 0.1
Russian
� � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �
0.1 0.1 0.1*/