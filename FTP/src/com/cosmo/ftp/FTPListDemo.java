package com.cosmo.ftp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPListDemo {

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 21;
		String user = "cosmo";
		String pass = "cosmo";

		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.connect(server, port);
			showServerReply(ftpClient);

			int replycode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replycode)) {
				System.out.println("Connect failed");
				return;
			}

			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);

			if (!success) {
				System.out.println("Could not login to the server");
				return;
			}

			FTPFile[] file1 = ftpClient.listFiles("/bak");
			printFileDetails(file1);

			String[] file2 = ftpClient.listNames();
			printName(file2);

		} catch (IOException ex) {
			System.out.println("Oops! Something wrong happened");
			ex.printStackTrace();
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

	private static void printFileDetails(FTPFile[] files) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (FTPFile file : files) {
			String detail = file.getName();
			if (file.isDirectory()) {
				detail = "[" + detail + "]";
			}
			detail += "\t\t" + file.getSize();
			detail += "\t\t" + dateFormat.format(file.getTimestamp().getTime());
			System.out.println(detail);
		}
	}

	private static void printName(String[] files) {
		if (files != null && files.length > 0) {
			for (String file : files) {
				System.out.println(file);
			}
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
