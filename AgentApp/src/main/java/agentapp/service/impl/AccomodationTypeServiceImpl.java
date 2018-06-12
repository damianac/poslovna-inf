package agentapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentapp.domain.AccomodationType;
import agentapp.repository.AccomodationTypeRepository;
import agentapp.service.AccomodationTypeService;

@Service
public class AccomodationTypeServiceImpl implements AccomodationTypeService{

	@Autowired
	private AccomodationTypeRepository accTypeRepository;
	
	@Override
	public AccomodationType addAccType(AccomodationType acctype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccomodationType> getAllTypes() {
		return accTypeRepository.findAll();
	}

}
