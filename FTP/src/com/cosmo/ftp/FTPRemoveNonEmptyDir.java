package com.cosmo.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

import com.cosmo.ftp.util.FTPUtil;

public class FTPRemoveNonEmptyDir {

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

			String dirPath = "/bak_2015/7-11";
			FTPUtil.removeDirectory(ftpClient, dirPath, "");
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
