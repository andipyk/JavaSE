package com.cosmo.networking;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cosmo.networking.util.HttpUtility;

public class HttpUtilityTester {

	public static void main(String[] args) {
		String requestURL = "http://www.google.com";

		try {
			HttpUtility.sendGetRequest(requestURL);
			String[] response = HttpUtility.readMultipleLinesResponse();
			for (String line : response) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpUtility.disconnect();

		System.out.println("=====================================");

		Map<String, String> params = new HashMap<String, String>();
		requestURL = "https://accounts.google.com/ServiceLoginAuth";
		params.put("Email", "your_email");
		params.put("Passwd", "your_password");
		try {
			HttpUtility.sendPostRequest(requestURL, params);
			String[] response = HttpUtility.readMultipleLinesResponse();
			for (String line : response) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpUtility.disconnect();
	}

}
