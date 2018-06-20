package rs.ftn.xws.booking.webservice;

import java.util.List;

import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.annotations.SchemaValidation.SchemaValidationType;

import rs.ftn.xws.booking.xsd.AccomodationSoap;
import rs.ftn.xws.booking.xsd.AccomodationTypeSoap;
import rs.ftn.xws.booking.xsd.AdditionalServiceSoap;
import rs.ftn.xws.booking.xsd.CategorySoap;
import rs.ftn.xws.booking.xsd.MessageSoap;
import rs.ftn.xws.booking.xsd.TermSoap;
import rs.ftn.xws.booking.xsd.UserSoap;

@WebService(name = "AccomodationWebServiceSoap", targetNamespace = "http://booking.xws.ftn.rs/accomodationWebService")
@SchemaValidation(type = SchemaValidationType.REQUEST)
public interface AccomodationWebService {

	// prevodi jaxb model u model koji se cuva u bazu i vraca id iz baze
	Long addAccomodation(AccomodationSoap accomodation);

	Long modifyAccomodation(AccomodationSoap accomodation);

	Long deleteAccomodation(Long accomodationId);

	List<AccomodationSoap> getAll();

	List<AccomodationTypeSoap> getAllAccomodationTypes();

	List<AdditionalServiceSoap> getAllAdditionalServices();
	
	List<CategorySoap> getAllCategories();
	
	Long creatingTerm(TermSoap termSoap, Long accdatabaseId);
	
	Long setVisitedValue(boolean visited, Long termId);
	
	List<UserSoap> getAllUsers();
	
	List<MessageSoap> getMessagesForAgent();
	
	Long sendMessage(MessageSoap messageSoap);
	
	Long modifyTerm(TermSoap termSoap);
}
