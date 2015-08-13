package com.cosmo.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPListRecursiveDemo {

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
				System.out.println("Connect failed");
				return;
			}
			boolean success = ftpClient.login(user, pass);
			if (!success) {
				System.out.println("Could not login to the server");
				return;
			}
			String dirToList = "/";
			listDirectory(ftpClient, dirToList, "", 0);
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

	private static void listDirectory(FTPClient ftpClient, String parentDir,
			String currentDir, int level) throws IOException {
		String dirToList = parentDir;
		if (!"".equals(currentDir)) {
			dirToList += "/" + currentDir;
		}

		FTPFile[] subFiles = ftpClient.listFiles(dirToList);
		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile ftpFile : subFiles) {
				String currentFileName = ftpFile.getName();
				if (".".equals(currentFileName) || "..".equals(currentFileName)) {
					continue;
				}
				for (int i = 0; i < level; i++) {
					System.out.print("\t");
				}
				if (ftpFile.isDirectory()) {
					System.out.print("[" + currentFileName + "]");
					listDirectory(ftpClient, dirToList, currentFileName,
							level + 1);
				} else {
					System.out.println(currentFileName);
				}
			}
		}
	}

}
