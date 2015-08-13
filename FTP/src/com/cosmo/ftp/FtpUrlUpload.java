package com.cosmo.ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class FtpUrlUpload {

	private static final int BUFFER_SIZE = 4096;

	public static void main(String[] args) {
		String ftpURL = "ftp://%s:%s@%s/%s;type=i";
		String host = "127.0.0.1";
		String user = "cosmo";
		String pass = "cosmo";
		String filePath = "C:/Users/Public/Pictures/Sample Pictures/Desert.jpg";
		String uploadPath = "/desert.jpg";

		ftpURL = String.format(ftpURL, user, pass, host, uploadPath);
		System.out.println("URL: " + ftpURL);

		try {
			URL url = new URL(ftpURL);
			URLConnection conn = url.openConnection();
			OutputStream outputStream = conn.getOutputStream();

			FileInputStream fis = new FileInputStream(filePath);

			byte[] buffer = new byte[BUFFER_SIZE];
			int read = -1;

			while ((read = fis.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
			}
			fis.close();
			outputStream.close();
			System.out.println("file upload done.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
