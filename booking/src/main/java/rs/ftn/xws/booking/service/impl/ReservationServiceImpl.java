package rs.ftn.xws.booking.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rs.ftn.xws.booking.dto.MessageDto;
import rs.ftn.xws.booking.dto.ReservationDto;
import rs.ftn.xws.booking.exception.ReservationException;
import rs.ftn.xws.booking.persistence.domain.Message;
import rs.ftn.xws.booking.persistence.domain.Term;
import rs.ftn.xws.booking.persistence.domain.User;
import rs.ftn.xws.booking.persistence.repository.MessageRepository;
import rs.ftn.xws.booking.persistence.repository.TermRepository;
import rs.ftn.xws.booking.persistence.repository.UserRepository;
import rs.ftn.xws.booking.service.ReservationService;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private TermRepository termRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Resource
	private WebServiceContext webServiceContext;

	@Override
	public void reserve(Long termId, String userId) throws ReservationException {
		Term term = termRepository.findByIdAndReservedFalse(termId)
				.orElseThrow(() -> new ReservationException("Reservation not found or has already been reserverd"));

		User currentUser = userRepository.findCurrentUser();
		
		term.setUser(currentUser);
		term.setReserved(true);
		termRepository.save(term);
	}

	@Override
	public List<ReservationDto> getReservations() {
		return termRepository.findCurrentUserReservations().stream()
				.map(ReservationDto::new)
				.collect(Collectors.toList());
	}

	@Override
	public void cancelReservation(Long termId) throws ReservationException {
		Term term = termRepository.findByIdCurrentUser(termId);
		
		if (term == null) {
			throw new ReservationException("Error while canceling reservaton!");
		}
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -3);
		
		if (term.getStartDate().after(now.getTime())) {
			term.setUser(null);
			term.setReserved(false);
			messageRepository.deleteAll(term.getMessages());
			termRepository.save(term);
		} else {
			throw new ReservationException("Can only cancel three days before start time!");
		}
	}

	public List<MessageDto> getMessages(Long termId) {
		Term term = termRepository.findByIdCurrentUser(termId);
		
		if (term == null) {
			throw new ReservationException("Cannot access messages!");
		}
		
		return term.getMessages().stream()
				.map(m -> new MessageDto(m.getMessage(), m.getUser() == null))
				.collect(Collectors.toList());
	}
	
	@Override
	public MessageDto sendMessage(Long termId, String message, String userId) throws ReservationException {
		Term term = termRepository.findByIdCurrentUser(termId);

		if (term == null) {
			throw new ReservationException("Cannot send message!");
		}

		User currentUser = userRepository.findCurrentUser();
		String agent = term.getAccomodation().getAgent();

		Message messageEntity = new Message(message, currentUser, term, agent);
		messageRepository.save(messageEntity);

		return new MessageDto(messageEntity.getMessage(), false);
	}

}
