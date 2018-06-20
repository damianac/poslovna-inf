package agentapp.agentapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import agentapp.domain.Accomodation;
import agentapp.domain.AccomodationType;
import agentapp.domain.AdditionalService;
import agentapp.domain.Category;
import agentapp.domain.Term;
import agentapp.repository.AccomodationRepository;
import agentapp.repository.AccomodationTypeRepository;
import agentapp.repository.AdditionalServiceRepository;
import agentapp.repository.CategoryRepository;
import agentapp.repository.TermRepository;
import rs.ftn.xws.booking.accomodationwebservice.AccomodationSoap;
import rs.ftn.xws.booking.accomodationwebservice.AccomodationTypeSoap;
import rs.ftn.xws.booking.accomodationwebservice.AccomodationWebServiceSoap;
import rs.ftn.xws.booking.accomodationwebservice.AdditionalServiceSoap;
import rs.ftn.xws.booking.accomodationwebservice.CategorySoap;
import rs.ftn.xws.booking.accomodationwebservice.TermSoap;

@Component
public class SyncData {
	
	@Autowired
	private AccomodationWebServiceSoap accWebService;
	
	@Autowired
	private AccomodationTypeRepository accTypeRepository;
	
	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private AccomodationRepository accRepository;

	@PostConstruct
	public void init() {
			
			System.out.println("########## SINHRONIZACIJAA #############");
		
			/*accTypeRepository.deleteAll();
			additionalServiceRepository.deleteAll();
			categoryRepository.deleteAll();*/
		
		
			List<AccomodationTypeSoap> typesSoap = accWebService.getAllAccomodationTypes();
			List<AdditionalServiceSoap> servicesSoap = accWebService.getAllAdditionalServices();
			List<CategorySoap> categoriesSoap = accWebService.getAllCategories();
			
			for(AccomodationTypeSoap typeSoap : typesSoap) {
				AccomodationType type = new AccomodationType();
				type.setType(typeSoap.getType());
				type.setDatabaseId(typeSoap.getId());
				accTypeRepository.save(type);
			}
			
			for(AdditionalServiceSoap serviceSoap : servicesSoap) {
				AdditionalService service = new AdditionalService();
				service.setName(serviceSoap.getName());
				service.setDatabaseId(serviceSoap.getId());
				additionalServiceRepository.save(service);
			}
			
			for(CategorySoap categorySoap : categoriesSoap) {
				Category category = new Category();
				category.setCategory(categorySoap.getCategory());
				category.setDatabaseId(categorySoap.getId());
				categoryRepository.save(category);
			}
			
			for(AccomodationSoap accSoap : accWebService.getAll()) {
				Accomodation acc = new Accomodation();
				acc.setName(accSoap.getName());
				acc.setCountry(accSoap.getCountry());
				acc.setCity(accSoap.getCity());
				acc.setAddress(accSoap.getAddress());
				acc.setDescription(accSoap.getDescription());
				acc.setDatabaseId(accSoap.getId());
				acc.setAccomodationType(accTypeRepository.getOne(accSoap.getAccomodationType()));
				acc.setCategory(categoryRepository.getOne(accSoap.getCategory()));
				acc.setCapacity(accSoap.getCapacity());
				Set<AdditionalService> serviceslocal = new HashSet<AdditionalService>(additionalServiceRepository.findAllById(accSoap.getAdditionalServices()));
				acc.setAdditionalServices(serviceslocal);
				acc.setTerms(new ArrayList<Term>());
				acc = accRepository.save(acc);
				for(TermSoap termSoap: accSoap.getTerms() ) {
					Term term = new Term();
					term.setStartDate(termSoap.getStartDate().toGregorianCalendar().getTime());
					term.setEndDate(termSoap.getEndDate().toGregorianCalendar().getTime());
					term.setPrice(termSoap.getPrice());
					term.setDatabaseId(termSoap.getId());
					term.setAccomodation(acc);
//					acc.getTerms().add(term);
					termRepository.save(term);
					
				}
//				accRepository.save(acc);
				
			}
		
	}
	
}
