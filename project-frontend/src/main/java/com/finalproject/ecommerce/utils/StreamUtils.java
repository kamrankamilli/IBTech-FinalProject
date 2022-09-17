package com.finalproject.ecommerce.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class StreamUtils {
	public static void write(OutputStream out, String output) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8"));
		writer.write(output);
		writer.flush();
		out.flush();
		out.close();
	}

	public static String read(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

		String line;
		StringBuilder builder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			builder.append(line).append("\r\n");
		}
		reader.close();
		return builder.toString();
	}

	public static InputStream get(String address) throws IOException {
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		return in;
	}

	public static InputStream post(String address, String output) throws IOException {
		URL url = new URL(address);

		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);

		write(connection.getOutputStream(), output);

		return connection.getInputStream();
	}
}
