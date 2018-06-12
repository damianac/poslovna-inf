package agentapp.service;

import java.util.List;

import agentapp.domain.AccomodationType;

public interface AccomodationTypeService {
	
	AccomodationType addAccType(AccomodationType acctype);
	
	List<AccomodationType> getAllTypes();
	
}
