package com.cosmo.io;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class TextFileReadingExample1 {

	public static void main(String[] args) {
		Date startd = new Date();
		try {
			FileReader reader = new FileReader("MyFile.txt");
			int character;
			while ((character = reader.read()) != -1) {
				System.out.print((char) character);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.print((new Date().getTime() - startd.getTime()) + "ms.");
	}

}
