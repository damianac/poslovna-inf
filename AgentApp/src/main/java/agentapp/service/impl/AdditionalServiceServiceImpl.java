package agentapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentapp.domain.AdditionalService;
import agentapp.repository.AdditionalServiceRepository;
import agentapp.service.AdditionalServiceService;

@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService{

	@Autowired
	private AdditionalServiceRepository addServiceRepository;
	
	@Override
	public AdditionalService addAdditionalService(AdditionalService additionalService) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdditionalService> getAllServices() {
		return addServiceRepository.findAll();
	}

}
