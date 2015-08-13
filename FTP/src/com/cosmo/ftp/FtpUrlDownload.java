package com.cosmo.ftp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FtpUrlDownload {

	private static final int BUFFER_SIZE = 4096;

	public static void main(String[] args) {
		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String server = "127.0.0.1";
		String user = "cosmo";
		String pass = "cosmo";
		String filePath = "/bak_2015/logging.zip";
		String savePath = "E:/123/ccc.zip";

		ftpUrl = String.format(ftpUrl, user, pass, server, filePath);
		System.out.println("URL: " + ftpUrl);

		try {
			URL url = new URL(ftpUrl);
			URLConnection conn = url.openConnection();
			InputStream inputStream = conn.getInputStream();

			FileOutputStream fos = new FileOutputStream(savePath);

			byte[] buffer = new byte[BUFFER_SIZE];
			int read = -1;
			while ((read = inputStream.read(buffer)) != -1) {
				fos.write(buffer, 0, read);
			}
			fos.close();
			inputStream.close();
			System.out.println("File downloaded");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
