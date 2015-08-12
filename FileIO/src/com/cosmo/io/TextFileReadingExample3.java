package com.cosmo.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

public class TextFileReadingExample3 {

	public static void main(String[] args) {
		Date startd = new Date();
		try {
			FileReader reader = new FileReader("MyFile.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.print((new Date().getTime() - startd.getTime()) + "ms.");
	}

}
