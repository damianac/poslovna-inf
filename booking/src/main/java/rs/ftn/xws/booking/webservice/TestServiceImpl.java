package rs.ftn.xws.booking.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rs.ftn.xws.booking.persistence.domain.Accomodation;
import rs.ftn.xws.booking.persistence.domain.AccomodationImage;
import rs.ftn.xws.booking.persistence.repository.AccomodationRepository;
import rs.ftn.xws.booking.persistence.repository.ImageRepository;
import rs.ftn.xws.booking.service.StorageService;

@Service
@MTOM(enabled = true)
@WebService(endpointInterface = "rs.ftn.xws.booking.webservice.TestService",
			serviceName = "TestService",
			portName = "TestServicePort",
			targetNamespace = "http://booking.xws.ftn.rs/test")
public class TestServiceImpl implements TestService {

	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Resource
	private WebServiceContext webServiceContext;
	
	@Autowired
	@Qualifier("Azure")
	private StorageService storageService;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	@Override
	public void uploadMultiple(UploadModelXsd model)  {
		Accomodation a = accomodationRepository.findById(model.getId()).get();
		List<AccomodationImage> images = new ArrayList<>();
		
		for (DataHandler data: model.getImages()) {
			try {
				String imageUrl = storageService.saveFile(data.getInputStream());
				images.add(new AccomodationImage(imageUrl, a));
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new WebServiceException("An error has occured while uploading images.s");
			}
		}
		
		imageRepository.saveAll(images);
	}

	@Override
	public String testMethod() {
		System.out.println(webServiceContext.getUserPrincipal());
		return "Server testMethod()";
	}

}
