package com.cosmo.ftp.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
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
	
	public static boolean downloadSingleFile(FTPClient ftpClient,
			String remotoFilePath, String savePath) throws IOException {
		File downloadFile = new File(savePath);

		File parentDir = downloadFile.getParentFile();

		if (!parentDir.exists()) {
			parentDir.mkdir();
		}
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			return ftpClient.retrieveFile(remotoFilePath, outputStream);
		} catch (IOException e) {
			throw e;
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
	
	public static void downloadDirectory(FTPClient ftpClient, String parentDir,
			String currentDir, String saveDir) throws IOException {
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
				String filePath = parentDir + "/" + currentDir + "/"
						+ currentFileName;
				if ("".equals(currentDir)) {
					filePath = parentDir + "/" + currentFileName;
				}
				String newDirPath = saveDir + parentDir + File.separator
						+ currentDir + File.separator + currentFileName;
				if ("".equals(currentDir)) {
					newDirPath = saveDir + parentDir + File.separator + currentFileName;
				}

				if (ftpFile.isDirectory()) {
					File newDir = new File(newDirPath);
					boolean created = newDir.mkdir();
					if (created) {
						System.out.println("CREATED the directory: " + newDirPath);
					} else {
						System.out.println("COULD NOT create the directory: " + newDirPath);
					}
					downloadDirectory(ftpClient, dirToList, currentFileName, saveDir);
				} else {
					boolean success = downloadSingleFile(ftpClient, filePath, newDirPath);
					if (success) {
						System.out.println("DOWNLOADED the file: " + filePath);
					} else {
						System.out.println("COULD NOT download the file: " + filePath);
					}
				}
			}
		}
	}
}
