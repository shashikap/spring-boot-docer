package com.randoli.event.consumer.web;

import com.randoli.event.consumer.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.randoli.event.consumer.model.ConsumerRequest;
import com.randoli.event.consumer.service.ConsumerService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("payload")
public class ConsumerController {

	private final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	private ConsumerService service;

	@PostMapping(path="/non-react")
	public ResponseEntity<?> savePayload(@RequestBody final ConsumerRequest request) {
		LOGGER.debug("event payload : {}", request);
		service.postEvents(request);
		return ResponseEntity.ok("posted!");
	}


	@PostMapping(path = "reactive")
	public Flux<Event> savePayload2(@RequestBody final ConsumerRequest request) {
		LOGGER.debug("event payload : {}", request);

//		service.postEvents2(request)
//				.map(event -> ResponseEntity.status(HttpStatus.OK).body(event))
//				.cast(ResponseEntity.class)
//				.defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong"));

		return service.postEvents2(request);
	}
}
