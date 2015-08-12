package com.cosmo.networking;

import java.io.IOException;

import com.cosmo.networking.util.HttpDownloadUtility;

public class HttpDownloader {

	public static void main(String[] args) {

		String fileURL = "https://jdbc.postgresql.org/download/postgresql-9.2-1002.jdbc4.jar";
		String saveDir = "E:\\123";

		try {
			HttpDownloadUtility.downloadFile(fileURL, saveDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
