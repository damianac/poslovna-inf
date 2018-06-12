package agentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentapp.domain.Accomodation;
import agentapp.repository.AccomodationRepository;
import agentapp.service.AccomodationService;

@Service
public class AccomodationServiceImpl implements AccomodationService{
	
	@Autowired
	private AccomodationRepository accRepository;

	@Override
	public Accomodation addAccomodation(Accomodation accomodation) {
		return accRepository.save(accomodation);
	}

	
}
