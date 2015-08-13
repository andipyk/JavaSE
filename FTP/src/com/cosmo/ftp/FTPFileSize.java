package com.cosmo.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPFileSize {

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 21;
		String user = "cosmo";
		String pass = "cosmo";

		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);

			String filePath = "/bak_2015/logging.zip";

			FTPFile file = ftpClient.mlistFile(filePath);
			long size = file.getSize();
			System.out.println("File size = " + size);

			ftpClient.sendCommand("SIZE", filePath);
			String reply = ftpClient.getReplyString();
			System.out.println("Reply for size command: " + reply);
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
