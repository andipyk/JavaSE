package com.cosmo.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPRenamer {

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 21;
		String user = "cosmo";
		String pass = "cosmo";

		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(server, port);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return;
			}

			boolean success = ftpClient.login(user, pass);
			if (!success) {
				return;
			}

			String oldDir = "/bak";
			String newDir = "/bak_2015";

			success = ftpClient.rename(oldDir, newDir);
			if (success) {
				System.out.println(oldDir + " was successfully renamed to: " + newDir);
			} else {
				System.out.println("Failed to rename: " + oldDir);
			}

			// renaming file
			String oldFile = "/work/error.png";
			String newFile = "/work/screenshot.png";

			success = ftpClient.rename(oldFile, newFile);
			if (success) {
				System.out.println(oldFile + " was successfully renamed to: " + newFile);
			} else {
				System.out.println("Failed to rename: " + oldFile);
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
