package com.cosmo.io;

import java.io.IOException;

import com.cosmo.io.util.UnzipUtility;

public class UnzipUtilityTest {

	public static void main(String[] args) {
		String zipFilePath = "E:\\PDFBinder.zip";
		String destDirectory = "E:\\123";
		UnzipUtility unzipper = new UnzipUtility();
		try{
			unzipper.unzip(zipFilePath, destDirectory);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
