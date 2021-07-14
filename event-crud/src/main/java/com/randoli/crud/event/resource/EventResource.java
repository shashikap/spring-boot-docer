package com.randoli.crud.event.resource;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.randoli.crud.event.dao.model.Event;
import com.randoli.crud.event.processor.EventCreateProcessor;
import com.randoli.crud.event.processor.EventUpdateProcessor;
import com.randoli.crud.event.service.EventService;

@Component
public class EventResource extends RouteBuilder {

	
	@BeanInject
	private EventUpdateProcessor eventUpdateProcessor;

	@BeanInject
	private EventCreateProcessor eventCreateProcessor;

	@Autowired
	private EventService eventService;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.json)
		//Enable swagger endpoint.
		.apiContextPath("/swagger") 
		.apiContextRouteId("swagger") 

		 //Swagger properties
		.contextPath("/api") 
		.apiProperty("api.title", "Event REST api")
		.apiProperty("api.version", "1.0");

		rest().description("Event REST service")
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)

		.get("/events").description("Find all events").route()
				.setBody(() -> eventService.listEvents())
				.endRest()
		
		.get("/event/{id}").description("Get event by event id").to("bean:eventService?method=getEvent(${header.id})")
		
		.post("/event").description("Save event to db").type(Event.class).outType(Event.class)
				.route().process(eventCreateProcessor)
				.endRest()

		.put("/event").description("Update event by event id").type(Event.class).outType(Event.class).route()
				.process(eventUpdateProcessor).endRest()

		.delete("/event/{id}").description("Delete event by event id").to("bean:eventService?method=deleteEvent(${header.id})");
		
		
	}

}
