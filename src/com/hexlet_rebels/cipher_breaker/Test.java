package com.hexlet_rebels.cipher_breaker;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		VarHolder.readFingerprintsFromFile("res/fingerprints");
		Scanner s = new Scanner(System.in);
		String text = s.nextLine();
		
		//Language fingerprint has to be full
//		text = Util.setDisplacement(VarHolder.getCurrentAlphabet(), text, 40);
//		System.out.println(Breaker.decryptCaesarCipher(text));
		System.out.println(Breaker.encryptByPolyalphabeticCipher(text, "asdfsadfsdf"));
		s.close();
		
	}

}
/*Ukrainian
� � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �
0.1 0.1 0.1
Russian
� � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �
0.1 0.1 0.1*/