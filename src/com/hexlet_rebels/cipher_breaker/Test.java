package com.hexlet_rebels.cipher_breaker;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String text = s.nextLine();
		System.out.println(text);
		System.out.println();
		//Language fingerprint has to be full
		text = Util.setDisplacement(VarHolder.getLetters().get(VarHolder.getCurrentLanguage()), text, 1);
		System.out.println(text);
		System.out.println(Breaker.decryptCaesarCipher(text));
		s.close();
	}

}
