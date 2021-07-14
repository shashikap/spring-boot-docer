package com.randoli.event.consumer.service;

import com.randoli.event.consumer.model.ConsumerRequest;
import com.randoli.event.consumer.model.Event;
import reactor.core.publisher.Flux;

public interface ConsumerService {

	public String postEvents(ConsumerRequest request);

	public Flux<Event> postEvents2(ConsumerRequest request);
}
