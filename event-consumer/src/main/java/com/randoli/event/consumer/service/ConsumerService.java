package com.randoli.event.consumer.service;

import com.randoli.event.consumer.model.ConsumerRequest;

public interface ConsumerService {

	public String postEvents(ConsumerRequest request);
}
