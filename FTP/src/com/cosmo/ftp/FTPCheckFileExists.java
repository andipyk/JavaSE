package com.cosmo.ftp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPCheckFileExists {

	private FTPClient ftpClient;
	private int returnCode;

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 21;
		String user = "cosmo";
		String pass = "cosmo";
		String dirPath = "bak";
		String filePath = "/logging.zip";

		FTPCheckFileExists ftpApp = new FTPCheckFileExists();

		try {
			ftpApp.connect(server, port, user, pass);

			boolean exist = ftpApp.checkDirectoryExists(dirPath);
			System.out.println("Is directory " + dirPath + " exists? " + exist);

			exist = ftpApp.checkFileExists(filePath);
			System.out.println("Is file " + filePath + " exists? " + exist);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ftpApp.logout();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	void connect(String server, int port, String user, String pass)
			throws IOException {
		ftpClient = new FTPClient();
		ftpClient.connect(server, port);
		int replyCode = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			throw new IOException("Could not connect");
		}
		boolean success = ftpClient.login(user, pass);
		if (!success) {
			throw new IOException("Could not login");
		}
		System.out.println("Connected and logged in.");
	}

	void logout() throws IOException {
		if (ftpClient.isConnected()) {
			ftpClient.logout();
			ftpClient.disconnect();
			System.out.println("Logged out");
		}
	}

	boolean checkDirectoryExists(String dirPath) throws IOException {
		ftpClient.changeWorkingDirectory(dirPath);
		returnCode = ftpClient.getReplyCode();
		if (returnCode == 550) {
			return false;
		}
		return true;
	}

	boolean checkFileExists(String filePath) throws IOException {
		InputStream inputStream = ftpClient.retrieveFileStream(filePath);
		returnCode = ftpClient.getReplyCode();
		if (inputStream == null || returnCode == 550) {
			return false;
		}
		return true;
	}

}
