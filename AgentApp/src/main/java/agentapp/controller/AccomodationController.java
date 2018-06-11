package agentapp.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.activation.DataHandler;
import javax.validation.Valid;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import agentapp.domain.Accomodation;
import agentapp.domain.AdditionalService;
import agentapp.domain.Term;
import agentapp.dto.AccomodationInfo;
import agentapp.dto.ImagesInfo;
import agentapp.dto.InputStreamDataSource;
import agentapp.dto.TermInfo;
import agentapp.repository.AccomodationRepository;
import agentapp.repository.AccomodationTypeRepository;
import agentapp.repository.AdditionalServiceRepository;
import agentapp.repository.CategoryRepository;
import agentapp.repository.TermRepository;
import agentapp.service.AccomodationService;
import rs.ftn.xws.booking.accomodationwebservice.AccomodationSoap;
import rs.ftn.xws.booking.accomodationwebservice.AccomodationWebServiceSoap;
import rs.ftn.xws.booking.accomodationwebservice.TermSoap;
import rs.ftn.xws.booking.test.TestServiceSoap;
import rs.ftn.xws.booking.test.UploadModelXsd;

@RestController
@RequestMapping("/accomodations")
public class AccomodationController {
	
	@Autowired
	private AccomodationWebServiceSoap accWebService;
	
	@Autowired
	private AccomodationService accService;
	
	@Autowired
	private AdditionalServiceRepository addServiceRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AccomodationTypeRepository accTypeRepo;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private AccomodationRepository accomodationRepository;
	
	@Autowired
	private TestServiceSoap testService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public Accomodation addAccomodation(@RequestBody AccomodationInfo info) throws DatatypeConfigurationException {
		System.out.println(info.getCategory());
		//soap
		AccomodationSoap accSoap = new AccomodationSoap();
		accSoap.setName(info.getName());
		accSoap.setCountry(info.getCountry());
		accSoap.setCity(info.getCity());
		accSoap.setAddress(info.getAddress());
		accSoap.setCategory(categoryRepository.getOne(info.getCategory()).getDatabaseId());
		accSoap.setAccomodationType(accTypeRepo.getOne(info.getAccomodationType()).getDatabaseId());
		accSoap.setCapacity(info.getCapacity());
		accSoap.setDescription(info.getDescription());
		
		List<Long> addservices = new ArrayList<Long>();
		List<AdditionalService> services = addServiceRepository.findAllById(info.getAdditionalServices());
		
		for(AdditionalService service : services) {
			addservices.add(service.getDatabaseId());
		}
		accSoap.setAdditionalServices(addservices);
		
		List<TermInfo> termsInfo = info.getTerms();
		List<TermSoap> termsSoap = new ArrayList<TermSoap>();
		
		for(TermInfo terminfo : termsInfo ) {
			GregorianCalendar c = new GregorianCalendar();
			
			
			TermSoap termSoap = new TermSoap();
			c.setTime(terminfo.getStartDate());
			XMLGregorianCalendar startdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			termSoap.setStartDate(startdate);
			c.setTime(terminfo.getEndDate());
			XMLGregorianCalendar enddate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			termSoap.setEndDate(enddate);
			termSoap.setPrice(terminfo.getPrice());
			termSoap.setVisited(terminfo.isVisited());
			termsSoap.add(termSoap);
		}
		
		accSoap.setTerms(termsSoap);
		
		Long databaseId = accWebService.addAccomodation(accSoap);
		
		//soap
		
		//lokalno
		Accomodation acc = new Accomodation();
		
		acc.setName(info.getName());
		acc.setCountry(info.getCountry());
		acc.setCity(info.getCity());
		acc.setAddress(info.getAddress());
		acc.setCategory(categoryRepository.getOne(info.getCategory()));
		acc.setAccomodationType(accTypeRepo.getOne(info.getAccomodationType()));
		acc.setCapacity(info.getCapacity());
		acc.setDescription(info.getDescription());
		acc.setDatabaseId(databaseId);
		
		Set<AdditionalService> serviceslocal = new HashSet<AdditionalService>(addServiceRepository.findAllById(info.getAdditionalServices()));
		acc.setAdditionalServices(serviceslocal);
		accomodationRepository.save(acc);
		
		List<Term> termsLocal = new ArrayList<Term>();
		for(TermInfo termInfo : termsInfo) {
			//term soap
			GregorianCalendar c = new GregorianCalendar();
			TermSoap termSoap = new TermSoap();
			c.setTime(termInfo.getStartDate());
			XMLGregorianCalendar startdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			termSoap.setStartDate(startdate);
			c.setTime(termInfo.getEndDate());
			XMLGregorianCalendar enddate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			termSoap.setEndDate(enddate);
			termSoap.setPrice(termInfo.getPrice());
			termSoap.setReserved(false);
			termSoap.setVisited(false);
			//
			Term termlocal = new Term();
			termlocal.setStartDate(termInfo.getStartDate());
			termlocal.setEndDate(termInfo.getEndDate());
			termlocal.setPrice(termInfo.getPrice());
			termlocal.setAccomodation(acc);
			termlocal.setReserved(false);
			termlocal.setVisited(false);
			Long id = accWebService.creatingTerm(termSoap, databaseId);
			termlocal.setDatabaseId(id);
			termRepository.save(termlocal);
			termsLocal.add(termlocal);
		}
		acc.setTerms(termsLocal);
		accomodationRepository.save(acc);
		
		//lokalno
		return acc;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("{id}")
	public ResponseEntity<?> uploadAccomodationImages(@PathVariable Long id, @ModelAttribute @Valid ImagesInfo imagesInfo) throws Exception {
		List<MultipartFile> images = imagesInfo.getImage();
		Accomodation accomodation = accomodationRepository.getOne(id);
		
		if (accomodation == null) {
			return ResponseEntity.notFound().build();
		}
		
		UploadModelXsd uploadModelXsd = new UploadModelXsd();
		List<DataHandler> dataHandlerImages = new ArrayList<>();
		
		for (MultipartFile image : images) {
			dataHandlerImages.add(new DataHandler(new InputStreamDataSource(image.getInputStream(), image.getContentType())));
		}		
		
		uploadModelXsd.setImages(dataHandlerImages);
		uploadModelXsd.setId(accomodation.getDatabaseId());
		
		testService.uploadMultiple(uploadModelXsd);
		
		return ResponseEntity.ok().build();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public List<Accomodation> getAllAccomodations(){
		return accomodationRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public Accomodation getAccomodationById(@PathVariable("id") Long id){
		return accomodationRepository.getOne(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public Accomodation modifyAccomodation(@RequestBody AccomodationInfo info,@PathVariable("id") Long id) throws DatatypeConfigurationException{
		//local
		Accomodation accomodation = accomodationRepository.getOne(id);
		accomodation.setName(info.getName());
		accomodation.setCountry(info.getCountry());
		accomodation.setCity(info.getCity());
		accomodation.setAddress(info.getAddress());
		accomodation.setAccomodationType(accTypeRepo.getOne(info.getAccomodationType()));
		accomodation.setCategory(categoryRepository.getOne(info.getCategory()));
		accomodation.setDescription(info.getDescription());
		accomodation.setCapacity(info.getCapacity());
		Set<AdditionalService> services = new HashSet<AdditionalService>(addServiceRepository.findAllById(info.getAdditionalServices()));
		accomodation.getAdditionalServices().clear();
		accomodation.setAdditionalServices(services);
		accomodation.getTerms().clear();
		List<Term> tempTerms = new ArrayList<>();
		for(Term term : termRepository.findAll()) {
			if(term.getAccomodation().getId() == accomodation.getId()) {
				tempTerms.add(term);
			}
		}
		
		accomodation = accomodationRepository.save(accomodation);
		
		
		termRepository.deleteAll(tempTerms);
		
		List<TermSoap> termsSoap = new ArrayList<>();
		for(TermInfo terminfo : info.getTerms() ) {
			GregorianCalendar c = new GregorianCalendar();
			
			
			TermSoap termSoap = new TermSoap();
			c.setTime(terminfo.getStartDate());
			XMLGregorianCalendar startdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			termSoap.setStartDate(startdate);
			c.setTime(terminfo.getEndDate());
			XMLGregorianCalendar enddate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			termSoap.setEndDate(enddate);
			termSoap.setPrice(terminfo.getPrice());
			termSoap.setReserved(terminfo.isReserved());
			termSoap.setVisited(terminfo.isVisited());
			termsSoap.add(termSoap);
		}
		
		
		AccomodationSoap accSoap = new AccomodationSoap();
		accSoap.setName(info.getName());
		accSoap.setCountry(info.getCountry());
		accSoap.setCity(info.getCity());
		accSoap.setAddress(info.getAddress());
		accSoap.setAccomodationType(info.getAccomodationType());
		accSoap.setCategory(info.getCategory());
		accSoap.setDescription(info.getDescription());
		accSoap.setCapacity(info.getCapacity());
		accSoap.setId(accomodation.getDatabaseId());
		accSoap.setAdditionalServices(info.getAdditionalServices());
		accSoap.setTerms(termsSoap);
		
		accWebService.modifyAccomodation(accSoap);
		
		
		for(TermInfo terminfo : info.getTerms()) {
			
			
			//soap
			GregorianCalendar c = new GregorianCalendar();
			TermSoap termSoap = new TermSoap();
			c.setTime(terminfo.getStartDate());
			termSoap.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			c.setTime(terminfo.getEndDate());
			termSoap.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(c));
			termSoap.setPrice(terminfo.getPrice());
			termSoap.setReserved(terminfo.isReserved());
			termSoap.setVisited(terminfo.isVisited());
			termSoap.setAccomodationId(accomodation.getId());
			Long dbid = accWebService.creatingTerm(termSoap, accomodation.getDatabaseId());
			termsSoap.add(termSoap);
			
			
			//local
			Term term = new Term();
			term.setStartDate(terminfo.getStartDate());
			term.setEndDate(terminfo.getEndDate());
			term.setPrice(terminfo.getPrice());
			term.setReserved(terminfo.isReserved());
			term.setVisited(terminfo.isVisited());
			term.setAccomodation(accomodation);
			term.setDatabaseId(dbid);
			termRepository.save(term);
			
		}
		// local
		
		
		return accomodationRepository.save(accomodation);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeAccomodation(@PathVariable("id") Long id){
		
		Accomodation acc = accomodationRepository.getOne(id);
		
		accWebService.deleteAccomodation(acc.getDatabaseId());
		accomodationRepository.deleteById(id);
		
		return new ResponseEntity<>("Deleted",HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}/{visited}")
	public ResponseEntity<String> setVisitedValue(@PathVariable("visited") boolean visited,@PathVariable("id") Long id){
		
		Term term = termRepository.getOne(id);
		
		term.setVisited(visited);
		
		termRepository.save(term);
		
		accWebService.setVisitedValue(visited, term.getId());
		
		return new ResponseEntity<>("Visited changed",HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/visited")
	public List<Term> getTerms(){
		/*List<Term> terms = termRepository.findAll();
		if(terms == null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(terms,HttpStatus.OK);*/
		return termRepository.findAll();
	}

}
