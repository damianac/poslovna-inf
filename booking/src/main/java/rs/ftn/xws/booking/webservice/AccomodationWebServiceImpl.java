package rs.ftn.xws.booking.webservice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.xws.booking.persistence.domain.Accomodation;
import rs.ftn.xws.booking.persistence.domain.AccomodationType;
import rs.ftn.xws.booking.persistence.domain.AdditionalService;
import rs.ftn.xws.booking.persistence.domain.Category;
import rs.ftn.xws.booking.persistence.domain.Message;
import rs.ftn.xws.booking.persistence.domain.Term;
import rs.ftn.xws.booking.persistence.domain.User;
import rs.ftn.xws.booking.persistence.repository.AccomodationRepository;
import rs.ftn.xws.booking.persistence.repository.AccomodationTypeRepository;
import rs.ftn.xws.booking.persistence.repository.AdditionalServiceRepository;
import rs.ftn.xws.booking.persistence.repository.CategoryRepository;
import rs.ftn.xws.booking.persistence.repository.MessageRepository;
import rs.ftn.xws.booking.persistence.repository.TermRepository;
import rs.ftn.xws.booking.persistence.repository.UserRepository;
import rs.ftn.xws.booking.service.AccomodationService;
import rs.ftn.xws.booking.xsd.AccomodationSoap;
import rs.ftn.xws.booking.xsd.AccomodationTypeSoap;
import rs.ftn.xws.booking.xsd.AdditionalServiceSoap;
import rs.ftn.xws.booking.xsd.CategorySoap;
import rs.ftn.xws.booking.xsd.MessageSoap;
import rs.ftn.xws.booking.xsd.TermSoap;
import rs.ftn.xws.booking.xsd.UserSoap;

@Service
@WebService(endpointInterface = "rs.ftn.xws.booking.webservice.AccomodationWebService",
			serviceName = "AccomodationWebService",
			portName = "AccomodationWebServicePort",
			targetNamespace = "http://booking.xws.ftn.rs/accomodationWebService")
public class AccomodationWebServiceImpl implements AccomodationWebService{
	
	@Autowired
	private AccomodationService accService;
	
	@Autowired
	private AccomodationRepository accRepository;
	
	@Autowired
	private AccomodationTypeRepository accTypeRepository;
	
	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Resource
	private WebServiceContext webServiceContext;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public Long addAccomodation(AccomodationSoap accomodation) {
						
		Set<AdditionalService> services = new HashSet<AdditionalService>(additionalServiceRepository.findAllById(accomodation.getAdditionalServices()));
		//termini
		Accomodation newaccomodation = new Accomodation();
		newaccomodation.setName(accomodation.getName());
		newaccomodation.setDescription(accomodation.getDescription());
		newaccomodation.setCapacity(accomodation.getCapacity());
		newaccomodation.setAdditionalServices(services);
		newaccomodation.setAccomodationType(accTypeRepository.getOne(accomodation.getAccomodationType()));
		newaccomodation.setCity(accomodation.getCity());
		newaccomodation.setCountry(accomodation.getCountry());
		newaccomodation.setAddress(accomodation.getAddress());
		newaccomodation.setCategory(categoryRepository.getOne(accomodation.getCategory()));
		newaccomodation.setAgent(webServiceContext.getUserPrincipal().getName());
		newaccomodation = accService.addAccomodation(newaccomodation);
		
		
		/*for(TermSoap termSoap : accomodation.getTerms()) {
			Term term = new Term();
			term.setStartDate(termSoap.getStartDate());
			term.setEndDate(termSoap.getEndDate());
			term.setPrice(termSoap.getPrice());
			term.setAccomodation(newaccomodation);
			termRepository.save(term);
		}*/
		
		
		return newaccomodation.getId();
	}

	@Override
	public Long modifyAccomodation(AccomodationSoap accomodation) {
		Accomodation acc = accRepository.getOne(accomodation.getId());
		
		//acc.getTerms().clear();
		/*List<Term> tempTerms = new ArrayList<>();
		for(Term term : termRepository.findAll()) {
			if(term.getAccomodation().getId() == acc.getId()) {
				tempTerms.add(term);
			}
		}
		
		termRepository.deleteAll(tempTerms);*/
		
		//modifikovanje
		acc.setName(accomodation.getName());
		acc.setCountry(accomodation.getCountry());
		acc.setCity(accomodation.getCity());
		acc.setAddress(accomodation.getAddress());
		acc.setAccomodationType(accTypeRepository.getOne(accomodation.getAccomodationType()));
		acc.setCategory(categoryRepository.getOne(accomodation.getCategory()));
		acc.setDescription(accomodation.getDescription());
		acc.setCapacity(accomodation.getCapacity());
		Set<AdditionalService> services = new HashSet<AdditionalService>(additionalServiceRepository.findAllById(accomodation.getAdditionalServices()));
		acc.getAdditionalServices().clear();
		acc.setAdditionalServices(services);
		
		accRepository.save(acc);
		
		return acc.getId();
	}

	@Override
	public Long deleteAccomodation(Long accomodationId) {
		accRepository.deleteById(accomodationId);
		return accomodationId;
	}

	@Override
	public List<AccomodationSoap> getAll() {
		List<AccomodationSoap> accsoap = new ArrayList<>();
		for(Accomodation acc : accRepository.findAll()) {
			if(webServiceContext.getUserPrincipal().getName().equals(acc.getAgent())) {
				AccomodationSoap accS = new AccomodationSoap();
				accS.setId(acc.getId());
				accS.setName(acc.getName());
				accS.setCountry(acc.getCountry());
				accS.setCity(acc.getCity());
				accS.setAddress(acc.getAddress());
				accS.setAccomodationType(acc.getAccomodationType().getId());
				accS.setCategory(acc.getCategory().getId());
				accS.setCapacity(acc.getCapacity());
				accS.setDescription(acc.getDescription());
				List<Long> asids = new ArrayList<>();
				for(AdditionalService as : acc.getAdditionalServices()) {
					asids.add(as.getId());
				}
				accS.setAdditionalServices((ArrayList<Long>) asids);
				
				List<TermSoap> termsSoap = new ArrayList<>();
				for(Term term : acc.getTerms()) {
					TermSoap termSoap = new TermSoap();
					termSoap.setStartDate(term.getStartDate());
					termSoap.setEndDate(term.getEndDate());
					termSoap.setPrice(term.getPrice());
					termSoap.setId(term.getId());
					termSoap.setAccomodationId(acc.getId());
					termSoap.setReserved(term.isReserved());
					termSoap.setVisited(term.isVisited());
					if(term.getUser() != null) {
						termSoap.setUserId(term.getUser().getId());
					}
					termsSoap.add(termSoap);
				}
				accS.setTerms((ArrayList<TermSoap>) termsSoap);
				
				accsoap.add(accS);
			}
		}
		
		return accsoap;
	}
	
	@Override
	public List<AccomodationTypeSoap> getAllAccomodationTypes() {
		List<AccomodationType> types = accTypeRepository.findAll();
		List<AccomodationTypeSoap> typesSoap = new ArrayList<AccomodationTypeSoap>();
		for(AccomodationType type : types) {
			AccomodationTypeSoap typeSoap = new AccomodationTypeSoap();
			typeSoap.setId(type.getId());
			typeSoap.setType(type.getType());
			typesSoap.add(typeSoap);
		}
		return typesSoap;
	}

	@Override
	public List<AdditionalServiceSoap> getAllAdditionalServices() {
		List<AdditionalService> services = additionalServiceRepository.findAll();
		List<AdditionalServiceSoap> servicesSoap = new ArrayList<AdditionalServiceSoap>();
		for(AdditionalService service : services) {
			AdditionalServiceSoap addSoap = new AdditionalServiceSoap();
			addSoap.setId(service.getId());
			addSoap.setName(service.getName());
			servicesSoap.add(addSoap);
		}
		return servicesSoap;
	}

	@Override
	public List<CategorySoap> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		List<CategorySoap> categoriesSoap = new ArrayList<CategorySoap>();
		for(Category category : categories) {
			CategorySoap categorySoap = new CategorySoap();
			categorySoap.setId(category.getId());
			categorySoap.setCategory(category.getCategory());
			categoriesSoap.add(categorySoap);
		}
		return categoriesSoap;
	}

	@Override
	public Long creatingTerm(TermSoap termSoap, Long accDatabaseId) {
		Term term = new Term();
		term.setStartDate(termSoap.getStartDate());
		term.setEndDate(termSoap.getEndDate());
		term.setPrice(termSoap.getPrice());
		term.setAccomodation(accRepository.getOne(accDatabaseId));
		term.setReserved(termSoap.isReserved());
		term.setVisited(termSoap.isVisited());
		term = termRepository.save(term);
		return term.getId();
	}

	@Override
	public Long setVisitedValue(boolean visited, Long termId) {
		Term term = termRepository.getOne(termId);
		term.setVisited(visited);
		termRepository.save(term);
		
		return term.getId();
	}

	@Override
	public List<UserSoap> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserSoap> usersSoap = new ArrayList<>();
		for(User user : users) {
			UserSoap userSoap = new UserSoap();
			userSoap.setId(user.getId());
			userSoap.setEmail(user.getEmail());
			usersSoap.add(userSoap);
		}
		return usersSoap;
	}

	@Override
	public List<MessageSoap> getMessagesForAgent() {
		List<Message> messages = messageRepository.findAll();
		List<MessageSoap> messagesSoap = new ArrayList<>();
		for(Message msg : messages) {
			if(webServiceContext.getUserPrincipal().getName().equals(msg.getAgent())) {
				MessageSoap msgSoap = new MessageSoap();
				msgSoap.setId(msg.getId());
				msgSoap.setMessage(msg.getMessage());
				msgSoap.setTermId(msg.getTerm().getId());
				if(msg.getUser() != null) {
					msgSoap.setUserId(msg.getUser().getId());
				}
				messagesSoap.add(msgSoap);
			}
		}
		return messagesSoap;
	}

	@Override
	public Long sendMessage(MessageSoap messageSoap) {
		Message msg = new Message();
		msg.setAgent(webServiceContext.getUserPrincipal().getName());
		msg.setMessage(messageSoap.getMessage());
		msg.setTerm(termRepository.getOne(messageSoap.getTermId()));
		messageRepository.save(msg);
		return msg.getId();
	}

	@Override
	public Long modifyTerm(TermSoap termSoap) {
		Term term = termRepository.getOne(termSoap.getId());
		term.setStartDate(termSoap.getStartDate());
		term.setEndDate(term.getEndDate());
		term.setPrice(termSoap.getPrice());
		term.setReserved(termSoap.isReserved());
		termRepository.save(term);
		return term.getId();
	}

}
