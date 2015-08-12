package com.cosmo.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileWritingExample2 {

	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter("YourFile.txt", false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write("Hello World");
			bufferedWriter.newLine();
			bufferedWriter.write("See You Again!");
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
