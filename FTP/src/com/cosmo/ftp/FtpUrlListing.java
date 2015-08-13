package com.cosmo.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class FtpUrlListing {

	public static void main(String[] args) {
		String ftpURL = "ftp://%s:%s@%s/%s;type=d";
		String host = "127.0.0.1";
		String user = "cosmo";
		String pass = "cosmo";
		String dirPath = "/bak_2015";

		ftpURL = String.format(ftpURL, user, pass, host, dirPath);
		System.out.println("URL: " + ftpURL);

		try {
			URL url = new URL(ftpURL);
			URLConnection conn = url.openConnection();
			InputStream inputStream = conn.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(inputStream));

			System.out.println("==start==");
			String line = null;
			while ((line = read.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("==end==");
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
