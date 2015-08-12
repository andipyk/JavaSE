package com.cosmo.io;

import java.io.File;
import java.io.IOException;

import com.cosmo.io.util.CopyUtil;

public class CopyDirectoryTest {

	public static void main(String[] args) {
		File sourceDir = new File("E:\\FlySheet\\7-11");
		File destDir = new File("E:\\imoaix");
		try {
			CopyUtil.copyDirectory(sourceDir, destDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
