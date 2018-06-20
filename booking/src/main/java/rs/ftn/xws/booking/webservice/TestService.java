package rs.ftn.xws.booking.webservice;

import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.annotations.SchemaValidation.SchemaValidationType;

@WebService(name = "TestServiceSoap", targetNamespace = "http://booking.xws.ftn.rs/test")
@SchemaValidation(type = SchemaValidationType.REQUEST)
public interface TestService {

	void uploadMultiple(UploadModelXsd fileUploadServer);
	
	String testMethod();
	
}
