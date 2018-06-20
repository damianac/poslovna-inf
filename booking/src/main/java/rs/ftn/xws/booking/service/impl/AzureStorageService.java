package rs.ftn.xws.booking.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPermissions;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobOutputStream;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import rs.ftn.xws.booking.exception.UnsupportedFileType;
import rs.ftn.xws.booking.service.StorageService;

@Service("Azure")
public class AzureStorageService implements StorageService {

	private CloudStorageAccount cloudStorageAccount;
	final String containerName = "images";
	
	@Autowired
	public AzureStorageService(CloudStorageAccount cloudStorageAccount) {
		this.cloudStorageAccount = cloudStorageAccount;
		
		try {
			final CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();
			final CloudBlobContainer container = blobClient.getContainerReference(containerName);
			container.createIfNotExists();
			BlobContainerPermissions permissions = new BlobContainerPermissions();
			permissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
			container.uploadPermissions(permissions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String saveFile(InputStream inputStream) throws UnsupportedFileType {
		try {
			final CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();
			CloudBlobContainer container = blobClient.getContainerReference(containerName);
			
			String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
			String fileName = getRandomFileName() + getFileExtension(mimeType);
			
			CloudBlockBlob blob = container.getBlockBlobReference(fileName);
			blob.getProperties().setContentType(mimeType);
			
			BlobOutputStream outputStream = blob.openOutputStream();		
			IOUtils.copy(inputStream, outputStream);		
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(outputStream);
			
			return blob.getUri().toString();
		} catch (URISyntaxException | StorageException | IOException e) {
			throw new UnsupportedFileType(e.getMessage());
		}
	}
	
	private String getRandomFileName() {
		return UUID.randomUUID().toString().replaceAll("-", "_");
	}
	
	private String getFileExtension(String fileType) {
		if (fileType == null) {
			throw new UnsupportedFileType("Image type can not be determined.");
		}
		
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
