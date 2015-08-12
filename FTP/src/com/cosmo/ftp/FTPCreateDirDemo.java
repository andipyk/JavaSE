package com.cosmo.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPCreateDirDemo {

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 21;
		String user = "cosmo";
		String pass = "cosmo";

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
			showServerReply(ftpClient);

			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Operation failed. Server reply code: "	+ replyCode);
				return;
			}

			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);
			if (!success) {
				System.out.println("Could not login to the server");
				return;
			}

			String dirToCreate = "/upload";
			success = ftpClient.makeDirectory(dirToCreate);
			showServerReply(ftpClient);
			if (success) {
				System.out.println("Successfully created directory: " + dirToCreate);
			} else {
				System.out.println("Failed to create directory. See server's reply.");
			}

			ftpClient.logout();
			ftpClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String reply : replies) {
				System.out.println("SERVER: " + reply);
			}
		}
	}

}
