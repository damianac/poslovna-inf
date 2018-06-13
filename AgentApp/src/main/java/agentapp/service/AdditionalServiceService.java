package agentapp.service;

import java.util.List;

import agentapp.domain.AdditionalService;

public interface AdditionalServiceService {
	
	AdditionalService addAdditionalService(AdditionalService additionalService);
	
	List<AdditionalService> getAllServices();
}
