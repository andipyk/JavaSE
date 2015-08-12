package com.cosmo.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUploadFileDemo {

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 21;
		String user = "cosmo";
		String pass = "cosmo";

		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			File firstLocalFile = new File(
					"C:/Users/cosmo/Downloads/lib/javax.servlet.jar.zip");
			String firstRemoteFile = "javax.zip";

			InputStream inputStream = new FileInputStream(firstLocalFile);
			System.out.println("Start uploading first file");

			boolean success = ftpClient.storeFile(firstRemoteFile, inputStream);
			inputStream.close();
			if (success) {
				System.out.println("The first file is uploaded successfully.");
			}

			File secondLocalFile = new File(
					"C:/Users/cosmo/Downloads/lib/commons-logging-1.2-bin.zip");
			String secondRemoteFile = "logging.zip";
			inputStream = new FileInputStream(secondLocalFile);

			System.out.println("Start uploading second file");
			OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
			byte[] bytesIn = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(bytesIn)) != -1) {
				outputStream.write(bytesIn, 0, bytesRead);
			}
			inputStream.close();
			outputStream.close();

			success = ftpClient.completePendingCommand();
			if (success) {
				System.out.println("The second file is uploaded successfully.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
