package rs.ftn.xws.booking.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ftn.xws.booking.dto.AccomodationDto;
import rs.ftn.xws.booking.dto.Order;
import rs.ftn.xws.booking.dto.Sort;
import rs.ftn.xws.booking.dto.TermDto;
import rs.ftn.xws.booking.persistence.domain.Accomodation;
import rs.ftn.xws.booking.persistence.domain.AccomodationImage;
import rs.ftn.xws.booking.persistence.domain.AccomodationType;
import rs.ftn.xws.booking.persistence.domain.AdditionalService;
import rs.ftn.xws.booking.persistence.domain.Term;
import rs.ftn.xws.booking.persistence.repository.AccomodationRepository;
import rs.ftn.xws.booking.persistence.repository.AccomodationTypeRepository;
import rs.ftn.xws.booking.persistence.repository.AdditionalServiceRepository;
import rs.ftn.xws.booking.service.AccomodationService;

@Service
@Transactional
public class AccomodationServiceImpl implements AccomodationService {

	@Autowired
	private AccomodationRepository accomodationRepository;

	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;

	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;

	@Override
	public Accomodation addAccomodation(Accomodation accomodation) {
		return accomodationRepository.save(accomodation);
	}

	@Override
	public Accomodation modifyAccomodation(Accomodation accomodation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Accomodation removeAccomodation(Long accomodationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Accomodation> getAll() {
		return accomodationRepository.findAll();
	}

	@Override
	public List<TermDto> getTerms() {
		List<Accomodation> accomodations = accomodationRepository.findAll();
		accomodations = filterByNonReserved(accomodations);
		return convert(accomodations);
	}
	
	public List<Accomodation> filterByNonReserved(List<Accomodation> accomodations) {
		accomodations.forEach(a -> {
			List<Term> terms = a.getTerms().stream()
					.filter(Term::isReserved)
					.collect(Collectors.toList());

			a.getTerms().removeAll(terms);
		});

		return accomodations.stream()
				.filter(a -> a.getTerms().size() > 0)
				.collect(Collectors.toList());
	}

	@Override
	public List<TermDto> searchBy(String city, String country, int capacity, Date startDate,
			Date endDate, List<Long> types, List<Long> categories, List<Long> services, Sort sortBy, Order orderBy) {
		
		List<Accomodation> accomodations = accomodationRepository.findByCapacityGreaterThanEqualAndCityAndCountryAllIgnoringCase(capacity, city, country);

		accomodations = filterByTypes(accomodations, types);
		accomodations = filterByServices(accomodations, services);
		accomodations = filterByTerms(accomodations, startDate, endDate);
		
		List<TermDto> terms = convert(accomodations);
		
		return sortAndOrer(terms, sortBy, orderBy);
	}

	private List<Accomodation> filterByTypes(List<Accomodation> accomodations, List<Long> types) {
		if (types.size() == 0) {
			return accomodations;
		}
		
		List<AccomodationType> accomodationTypes = accomodationTypeRepository.findAllById(types);
		
		return accomodations.stream()
			.filter(a -> accomodationTypes.contains(a.getAccomodationType()))
			.collect(Collectors.toList());
	}

	private List<Accomodation> filterByServices(List<Accomodation> accomodations, List<Long> services) {
		if (services.size() == 0) {
			return accomodations;
		}
		
		List<AdditionalService> accomodationServices = additionalServiceRepository.findAllById(services);
		
		return accomodations.stream()
				.filter(a -> a.getAdditionalServices().containsAll(accomodationServices))
				.collect(Collectors.toList());
	}

	private List<Accomodation> filterByTerms(List<Accomodation> accomodations, Date startDate, Date endDate) {
		accomodations.forEach(a -> {
			List<Term> terms = a.getTerms().stream()
					.filter(t -> {
						if (t.isReserved()) {
							return true;
						}
						if (t.getStartDate().before(startDate) || t.getEndDate().after(endDate)) {
							return true;
						}
						return false;
					})
					.collect(Collectors.toList());

			a.getTerms().removeAll(terms);
		});

		return accomodations.stream()
				.filter(a -> a.getTerms().size() > 0)
				.collect(Collectors.toList());
	}

	public List<TermDto> convert(List<Accomodation> accomodations) {
		List<TermDto> termDtos = new ArrayList<>();

		for (Accomodation a : accomodations) {
			String type = a.getAccomodationType().getType();

			List<String> images = a.getImages().stream()
					.map(AccomodationImage::getUrl)
					.collect(Collectors.toList());

			List<String> services = a.getAdditionalServices().stream()
					.map(AdditionalService::getName)
					.collect(Collectors.toList());

			AccomodationDto accDto = new AccomodationDto(a.getId(), a.getName(), a.getCountry(), a.getCity(),
					a.getAddress(), a.getDescription(), a.getCategory().getCategory(), type, services, images);
			
			List<TermDto> terms = a.getTerms().stream()
					.map(t -> new TermDto(t.getId(), t.getStartDate(), t.getEndDate(), t.getPrice(), accDto))
					.collect(Collectors.toList());
			
			termDtos.addAll(terms);
		}

		return termDtos;
	}
	
	public List<TermDto> sortAndOrer(List<TermDto> terms, Sort sortBy, Order orderBy) {
		Comparator<? super TermDto> comparator = null;

		if (sortBy.equals(Sort.PRICE)) {
			comparator = Comparator.comparing(a -> ((TermDto) a).getPrice());
		} else if (sortBy == Sort.CATEGORY) {
			comparator = Comparator.comparing(a -> ((TermDto) a).getAccomodation().getCategory());
		} else {
			// Ovo je za rejting
			comparator = Comparator.comparing(a -> ((TermDto) a).getPrice());
		}
		
		if (orderBy == Order.DESC) {
			comparator = comparator.reversed();
		}
		
		return terms.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
	}

}
