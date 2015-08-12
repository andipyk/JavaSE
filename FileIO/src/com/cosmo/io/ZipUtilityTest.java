package com.cosmo.io;

import com.cosmo.io.util.ZipUtility;

public class ZipUtilityTest {

	public static void main(String[] args) {
		String[] myFiles = { "E:/Test/PIC1.JPG", "E:/Test/PIC2.JPG",
				"E:/Test/PIC3.JPG", "E:/Test/PIC4.JPG" };
		String zipFile = "E:/Test/MyPics.zip";
		ZipUtility zipUtil = new ZipUtility();
		try {
			zipUtil.zip(myFiles, zipFile);
		} catch (Exception ex) {
			// some errors occurred
			ex.printStackTrace();
		}
	}

}
