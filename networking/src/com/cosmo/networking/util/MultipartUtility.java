package com.cosmo.networking.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MultipartUtility {

	private final String boundary;
	private static final String LINE_FEED = "\r\n";
	private HttpURLConnection httpConn;
	private String charset;
	private OutputStream outputStream;
	private PrintWriter writer;

	public MultipartUtility(String requestURL, String charset)
			throws IOException {
		this.charset = charset;

		boundary = "===" + System.currentTimeMillis() + "===";

		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
		httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
		httpConn.setRequestProperty("Test", "Bonjour");
		outputStream = httpConn.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(outputStream, charset));
	}

	public void addFormField(String name, String value) {
		writer.append("--" + boundary)
				.append(LINE_FEED)
				.append("Content-Disposition: form-data; name=\"" + name + "\"")
				.append(LINE_FEED)
				.append("Content-Type: text/plain; charset=" + charset)
				.append(LINE_FEED)
				.append(LINE_FEED)
				.append(value)
				.append(LINE_FEED);
		writer.flush();
	}

	public void addFilePart(String fieldName, File uploadFile)
			throws IOException {
		String fileName = uploadFile.getName();
		writer.append("--" + boundary)
				.append(LINE_FEED)
				.append("Content-Disposition: form-data; name=\"" + fieldName
						+ "\"; filename=\"" + fileName + "\"")
				.append(LINE_FEED)
				.append("Content-Type: "
						+ URLConnection.guessContentTypeFromName(fileName))
				.append(LINE_FEED)
				.append("Content-Transfer-Encoding: binary")
				.append(LINE_FEED)
				.append(LINE_FEED);
		writer.flush();

		FileInputStream fis = new FileInputStream(uploadFile);
		byte[] buffer = new byte[4096];
		int bytesReader = -1;
		while ((bytesReader = fis.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesReader);
		}
		outputStream.flush();
		fis.close();

		writer.append(LINE_FEED);
		writer.flush();
	}

	public void addHeaderField(String name, String value) {
		writer.append(name + ":" + value).append(LINE_FEED);
		writer.flush();
	}

	public List<String> finish() throws IOException {
		List<String> response = new ArrayList<String>();
		writer.append(LINE_FEED).flush();
		writer.append("--" + boundary + "--").append(LINE_FEED);
		writer.close();

		int status = httpConn.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				response.add(line);
			}
			reader.close();
			httpConn.disconnect();
		} else {
			throw new IOException("Server returned non-OK status: " + status);
		}
		return response;
	}
}
