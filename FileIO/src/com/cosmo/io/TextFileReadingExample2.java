package com.cosmo.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class TextFileReadingExample2 {

	public static void main(String[] args) {
		Date startd = new Date();
		try{
			FileInputStream inputStream = new FileInputStream("MyFile.txt");
			InputStreamReader reader = new InputStreamReader(inputStream);
			int character;
			
			while((character = reader.read()) != -1){
				System.out.print((char)character);
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println();
		System.out.print((new Date().getTime() - startd.getTime()) + "ms.");
	}

}
