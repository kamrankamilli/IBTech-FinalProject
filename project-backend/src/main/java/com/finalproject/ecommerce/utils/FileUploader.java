package com.finalproject.ecommerce.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUploader {

	public static File uploadFile(String uploadDir, Part filePart, HttpServletRequest request)
			throws ServletException, IOException {
		File file = null;
		;
		String applicationPath = request.getServletContext().getRealPath("/");
		String uploadFilePath = applicationPath + uploadDir;
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		File uploads = new File(uploadFilePath);

		if (!uploads.exists()) {
			uploads.mkdirs();
		}
		String extension = getFileExtension(fileName);
		String fileNameWithoutExt = getFileNameWithouExtension(fileName);
		file = File.createTempFile(fileNameWithoutExt, "." + extension, uploads);

		try (InputStream input = filePart.getInputStream()) {
			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		return file;
	}

	private static String getFileExtension(String fileName) {
		String extension = "";
		int index = fileName.lastIndexOf('.');
		if (index > 0) {
			extension = fileName.substring(index + 1);
		}
		return extension;
	}

	private static String getFileNameWithouExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
	}

}
