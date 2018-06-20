package rs.ftn.xws.booking.service;

import java.io.InputStream;

import rs.ftn.xws.booking.exception.UnsupportedFileType;

public interface StorageService {

	String saveFile(InputStream inputStream) throws UnsupportedFileType;
	
}
