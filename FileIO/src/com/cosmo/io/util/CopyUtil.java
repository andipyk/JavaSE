package com.cosmo.io.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopyUtil {

	public static void copyDirectory(File sourceDir, File destDir)
			throws IOException {
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		if (!sourceDir.exists()) {
			throw new IllegalArgumentException("sourceDir does not exist");
		}
		if (sourceDir.isFile() || destDir.isFile()) {
			throw new IllegalArgumentException(
					"Either sourceDir or destDir is not a directory");
		}
		copyDirectoryImpl(sourceDir, destDir);
	}

	public static void copyDirectoryImpl(File sourceDir, File destDir)
			throws IOException {
		File[] items = sourceDir.listFiles();
		if (items != null && items.length > 0) {
			for (File file : items) {
				if (file.isDirectory()) {
					File newDir = new File(destDir, file.getName());
					System.out.println("CREATED DIR: "
							+ newDir.getAbsolutePath());
					newDir.mkdir();
					copySingleFile(file, newDir);
				} else {
					File destFile = new File(destDir, file.getName());
					copySingleFile(file, destFile);
				}
			}
		}
	}

	public static void copySingleFile(File sourceFile, File destFile)
			throws IOException {
		System.out.println("COPY FILE: " + sourceFile.getAbsolutePath()
				+ " TO: " + destFile.getAbsolutePath());
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel sourceChannel = null;
		FileChannel destChannel = null;

		try {
			sourceChannel = new FileInputStream(sourceFile).getChannel();
			destChannel = new FileOutputStream(destFile).getChannel();

			sourceChannel.transferTo(0, sourceChannel.size(), destChannel);
		} finally {
			if (sourceChannel != null) {
				sourceChannel.close();
			}
			if (destChannel != null) {
				destChannel.close();
			}
		}
	}
}
