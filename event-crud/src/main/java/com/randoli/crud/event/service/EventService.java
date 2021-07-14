package com.randoli.crud.event.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.randoli.crud.event.dao.EventRepository;
import com.randoli.crud.event.dao.model.Event;
import com.randoli.crud.event.service.exception.EventNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	public Event createEvent(Event request) {
		return repository.save(request);
	}

	public List<Event> listEvents() {
		return repository.findAll();
	}
	
	public Event getEvent(UUID eventId) throws EventNotFoundException {
		return repository.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Unable to find event with id :" + eventId));
	}
	
	public Event updateEvent(Event request) throws EventNotFoundException {
		Event event = repository.findById(request.getEventId())
				.orElseThrow(() -> new EventNotFoundException("Unable to find event with id :" + request.getEventId()));

		event.setTransId(request.getTransId());
		event.setTransTms(request.getTransTms());
		event.setRcNum(request.getRcNum());
		event.setEventCnt(request.getEventCnt());
		event.setClientId(request.getClientId());
		event.setLocationCd(request.getLocationCd());
		event.setLocationId1(request.getLocationId1());
		event.setLocationId2(request.getLocationId2());
		event.setAddrNbr(request.getAddrNbr());

		return repository.save(event);
	}

	public void deleteEvent(UUID eventId) throws EventNotFoundException {

		repository.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Unable to find event with id :" + eventId));

		repository.deleteById(eventId);
	}
}
