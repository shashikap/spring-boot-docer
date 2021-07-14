package com.randoli.event.consumer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.randoli.event.consumer.model.ConsumerRequest;
import com.randoli.event.consumer.model.Event;
import com.randoli.event.consumer.web.ConsumerController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	@Autowired
	WebClient webClient;
	
	private final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);
	
	@Override
	public String postEvents(ConsumerRequest request) {
		
	List<Event> events =  buildEvents(request);
	
	LOGGER.debug("About to post {} events.",events.size());
	
	List<String> responses =events.stream().map(e ->{
		return webClient.post()
		.body(Mono.just(e), Event.class)
		.retrieve()
		.bodyToMono(String.class)
		.block();
	}).collect(Collectors.toList());
	
	LOGGER.debug("response:"+responses);
		
	return responses.stream().map(Object::toString).collect(Collectors.joining(","));
	}
	

	/** FIXME: according to the sample payload currently considering first element of the event array to pull out the details.**/
	private List<Event> buildEvents(ConsumerRequest request) {

		return request.getRecords().stream().map(r -> {
			return r.getEvent().stream().findFirst()
					.map(e -> Event.builder().transId(r.getTransId()).transTms(r.getTransTms()).rcNum(r.getRcNum())
							.clientId(r.getClientId()).eventCnt(e.getEventCnt()).locationCd(e.getLocationCd())
							.locationId1(e.getLocationId1()).locationId2(e.getLocationId2()).addrNbr(e.getAddrNbr())
							.build());
		}).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		
	}
}