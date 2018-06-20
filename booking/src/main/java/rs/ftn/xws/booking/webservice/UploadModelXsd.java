package rs.ftn.xws.booking.webservice;

import java.util.List;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "id", "images" })
@XmlAccessorType(XmlAccessType.FIELD)
public class UploadModelXsd {

	@XmlElement(required = true, name = "id")
	protected Long id;

	@XmlMimeType("application/octet-stream")
	@XmlElementWrapper(required = true, name = "images")
	@XmlElement(required = true, name = "image")
	private List<DataHandler> images;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<DataHandler> getImages() {
		return images;
	}

	public void setImages(List<DataHandler> images) {
		this.images = images;
	}

}
