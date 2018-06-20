package rs.ftn.xws.booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import rs.ftn.xws.booking.persistence.domain.Accomodation;
import rs.ftn.xws.booking.persistence.domain.AccomodationType;
import rs.ftn.xws.booking.persistence.domain.AdditionalService;
import rs.ftn.xws.booking.persistence.domain.Term;
import rs.ftn.xws.booking.persistence.repository.AccomodationRepository;
import rs.ftn.xws.booking.persistence.repository.AccomodationTypeRepository;
import rs.ftn.xws.booking.persistence.repository.AdditionalServiceRepository;

@Component
public class TestData {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private AccomodationRepository accomodationRepository;

	@Autowired
	private AdditionalServiceRepository additionalServiceRepository;
	
	@Autowired
	private AccomodationTypeRepository accomodationTypeRepository;
	
//	@PostConstruct
	@Transactional
	public void test() throws ParseException {
		// api/accomodations?bprice=400&tprice=700&startDate=2018-06-12&endDate=2018-06-28&types=1,2&services=1,3
		String city = "novi sad";
		String country = "srbija";
		float bprice = 400;
		float tprice = 700;
		Date startDate = sdf.parse("2018-06-12");
		Date endDate = sdf.parse("2018-06-28");
		List<Long> types = Arrays.asList(1l, 2l);
		List<Long> services = Arrays.asList(1l, 3l, 2l);
		
		List<Accomodation> accomodations = accomodationRepository.findByCapacityGreaterThanEqualAndCityAndCountryAllIgnoringCase(0, city, country);
		List<AccomodationType> accomodationTypes = accomodationTypeRepository.findAllById(types);
		List<AdditionalService> accomodationServices = additionalServiceRepository.findAllById(services);
		
		accomodations = filterByTypes(accomodations, accomodationTypes);
		accomodations = filterByServices(accomodations, accomodationServices);
		accomodations = filterByTerms(accomodations, startDate, endDate, bprice, tprice);
		
		System.out.println("Broj pronadjenih akomodacija: " + accomodations.size());
		
		for (Accomodation acc : accomodations) {
			System.out.println("Broj pronadjenih termina po akomodaciji: " + acc.getTerms().size());
		}
	}
	
//	private List<Accomodation> filterByTypes(List<Accomodation> accomodations, List<AccomodationType> types) {
//		return accomodations.stream()
//			.filter(a -> types.contains(a.getAccomodationType()))
//			.collect(Collectors.toList());
//	}
	
	private List<Accomodation> filterByTypes(List<Accomodation> accomodations, List<AccomodationType> types) {
		return accomodations.stream()
			.filter(a -> {
				System.out.println("Poredjenje tipova");				
				System.out.println("Tip: " + a.getAccomodationType().getType());
				boolean comparison = types.contains(a.getAccomodationType());
				types.forEach(t -> System.out.println("Pronadjeni tip " + t.getType()));
				System.out.println("Rezultat poredjenja " + comparison);
				return comparison;
			})
			.collect(Collectors.toList());
	}

	private List<Accomodation> filterByServices(List<Accomodation> accomodations, List<AdditionalService> services) {
		return accomodations.stream()
				.filter(a -> {
					boolean comparison = a.getAdditionalServices().containsAll(services);
					System.out.println("Provera kod servisa: " + comparison);
					return comparison;
				})
				.collect(Collectors.toList());
	}

	private List<Accomodation> filterByTerms(List<Accomodation> accomodations, Date startDate, Date endDate,
			float bPrice, float tPrice) {
		accomodations.forEach(a -> {
			List<Term> terms = a.getTerms().stream()
					.filter(t -> {
						boolean dateComparison = t.getStartDate().after(startDate) && t.getEndDate().before(endDate);
						if (dateComparison)
							return !(t.getPrice() >= bPrice && t.getPrice() <= tPrice);
						return !dateComparison;
					}).collect(Collectors.toList());

			a.getTerms().removeAll(terms);
		});

		return accomodations.stream()
				.filter(a -> a.getTerms().size() > 0)
				.collect(Collectors.toList());
	}
	
}
