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
à á â ã ´ ä å º æ ç è ³ ¿ ê ë ì í î ï ð ñ ò ó ô õ ö ÷ ø ù ü þ ÿ
0.1 0.1 0.1
Russian
à á â ã ä å ¸ æ ç ì é ê ë ì í î ï ð ñ ò ó ô õ ö ÷ ø ù ú û ü ý þ ÿ
0.1 0.1 0.1*/