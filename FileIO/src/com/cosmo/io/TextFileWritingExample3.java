package com.cosmo.io;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TextFileWritingExample3 {

	public static void main(String[] args) {
		try {
			FileOutputStream outputStream = new FileOutputStream("YourFile.txt", true);
			OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-16");
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			bufferedWriter.write("Xin chào");
			bufferedWriter.newLine();
			bufferedWriter.write("Hẹn gặp lại!");

			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
