package agentapp.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagesInfo {

	@NotNull
	@Size(min = 1)
	private List<MultipartFile> image;

	public List<MultipartFile> getImage() {
		return image;
	}
	
	public void setImage(List<MultipartFile> files) {
		this.image = files;
	}

}
