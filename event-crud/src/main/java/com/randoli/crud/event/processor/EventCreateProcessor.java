package com.randoli.crud.event.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.randoli.crud.event.dao.model.Event;
import com.randoli.crud.event.service.EventService;

@Component
public class EventCreateProcessor implements Processor {

	@Autowired
	private EventService service;

	@Override
	public void process(Exchange exchange) throws Exception {
			service.createEvent(exchange.getIn().getBody(Event.class));
	}

}
