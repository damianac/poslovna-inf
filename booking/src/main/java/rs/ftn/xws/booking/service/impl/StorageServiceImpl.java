package rs.ftn.xws.booking.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import rs.ftn.xws.booking.exception.UnsupportedFileType;
import rs.ftn.xws.booking.service.StorageService;

@Service("Desktop")
public class StorageServiceImpl implements StorageService {

	private static final String PATH = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "images" + File.separator;
	
	@Override
	public String saveFile(InputStream inputStream) throws UnsupportedFileType {
		try {
			String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
			String fileName = getRandomFileName() + getFileExtension(mimeType);
			
			OutputStream os = new FileOutputStream(new File(PATH + fileName));
			
			IOUtils.copy(inputStream, os);
			
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(os);
			
			return "images/" + fileName;
		} catch (IOException e) {
			throw new UnsupportedFileType(e.getMessage());
		}
	}

	private String getRandomFileName() {
		return UUID.randomUUID().toString().replaceAll("-", "_");
	}
	
	private String getFileExtension(String fileType) {
		if (fileType.contains("jpeg")) {
			return ".jpeg";
		} else if (fileType.contains("jpg")) {
			return ".jpg";
		} else if (fileType.contains("png")) {
			return ".png";
		} else {
			throw new UnsupportedFileType("Unsupported image type");
		}
	}
	
}
