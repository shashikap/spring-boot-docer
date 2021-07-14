package com.randoli.event.consumer.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.randoli.event.consumer.model.ConsumerRequest;
import com.randoli.event.consumer.service.ConsumerService;

@RestController
@RequestMapping("payload")
public class ConsumerController {

	private final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	private ConsumerService service;

	@PostMapping
	public ResponseEntity<?> savePayload(@RequestBody final ConsumerRequest request) {
		LOGGER.debug("event payload : {}", request);
		service.postEvents(request);
		return ResponseEntity.ok("posted!");
	}

}
