package rs.ftn.xws.booking.service;

import java.util.Date;
import java.util.List;

import rs.ftn.xws.booking.dto.Order;
import rs.ftn.xws.booking.dto.Sort;
import rs.ftn.xws.booking.dto.TermDto;
import rs.ftn.xws.booking.persistence.domain.Accomodation;

public interface AccomodationService {

	Accomodation addAccomodation(Accomodation accomodation);

	Accomodation modifyAccomodation(Accomodation accomodation);

	Accomodation removeAccomodation(Long accomodationId);

	List<Accomodation> getAll();

	List<TermDto> getTerms();

	List<TermDto> searchBy(String city, String country, int capacity, Date startDate, Date endDate,
			List<Long> types, List<Long> categories, List<Long> services, Sort sortBy, Order orderBy);

}
