package com.cosmo.ftp.util;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FTPUtil {

	public static void removeDirectory(FTPClient ftpClient, String parentDir,
			String currentDir) throws IOException {
		String dirToList = parentDir;
		if (!"".equals(currentDir)) {
			dirToList += "/" + currentDir;
		}

		FTPFile[] subFiles = ftpClient.listFiles(dirToList);
		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile ftpfile : subFiles) {
				String currentFileName = ftpfile.getName();
				if (".".equals(currentFileName) || "..".equals(currentFileName)) {
					continue;
				}
				String filePath = parentDir + "/" + currentDir + "/" + currentFileName;
				if ("".equals(currentDir)) {
					filePath = parentDir + "/" + currentFileName;
				}

				if (ftpfile.isDirectory()) {
					removeDirectory(ftpClient, dirToList, currentFileName);
				} else {
					boolean deleted = ftpClient.deleteFile(filePath);
					if (deleted) {
						System.out.println("DELETED the file: " + filePath);
					} else {
						System.out.println("CANNOT delete the file: " + filePath);
					}
				}
			}

			boolean removed = ftpClient.removeDirectory(dirToList);
			if (removed) {
				System.out.println("REMOVED the directory: " + dirToList);
			} else {
				System.out.println("CANNOT remove the directory: " + dirToList);
			}
		}
	}
}
