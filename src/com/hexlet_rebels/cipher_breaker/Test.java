package com.hexlet_rebels.cipher_breaker;

import java.util.LinkedList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		VarHolder.readFingerprintsFromFile("res/fingerprints");
		LinkedList<char[]> list = new LinkedList<char[]>(VarHolder.getLetters().values());
		Scanner s = new Scanner(System.in);
		String text = s.nextLine();
		//Language fingerprint has to be full
		
		text = Util.setDisplacement(list.get(0), text, 1);
		System.out.println(Breaker.decryptCaesarCipher(text));
		s.close();
		
	}

}
/*Ukrainian
� � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �
0.1 0.1 0.1
Russian
� � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � � �
0.1 0.1 0.1*/