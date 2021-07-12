package com.randoli.crud.event.router;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.randoli.crud.event.model.Event;

@Component
public class EventJPARouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration()
		  .contextPath("/api-doc") 
		  .port("8080")
		  .enableCORS(true)
		  .apiContextPath("/api-doc")
		  .apiProperty("api.title", "Test REST API")
		  .apiProperty("api.version", "v1")
		  .apiContextRouteId("doc-api")
		  .component("servlet")
		  .bindingMode(RestBindingMode.json);

		rest().get("event").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.to("jpa://com.randoli.crud.event.model.Event?resultClass=" + Event.class.getName()
						+ "&namedQuery=findAll")
				.log("---select all Events---");

		rest().get("event/{id}").description("Details of an Event by id").outType(Event.class)
				.produces(MediaType.APPLICATION_JSON_VALUE).route().log("--- 1 select a Event ${body} ---")
				.toD("jpa://" + Event.class.getName() + "?query=select b from " + Event.class.getName()
						+ " b where b.eventId= '${header.id}'", 5)
				.log("--- 2 select a Event ${body} ---");

		rest().post("event").produces(MediaType.APPLICATION_JSON_VALUE).type(Event.class).route().routeId("postEventRoute")
				.log("--- binded ${body} ---").to("jpa:" + Event.class.getName() + "?usePersist=true")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
				.setBody(constant("saved"));

		rest().delete("event/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.toD("jpa:" + Event.class.getName()
						+ "?nativeQuery=DELETE FROM Event where event_id = '${header.id}'&useExecuteUpdate=true")
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204)).setBody(constant("deleted"));
		
		rest().delete("event/trans-id/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route()
		.toD("jpa:" + Event.class.getName()
				+ "?nativeQuery=DELETE FROM Event where trans_id = '${header.id}'&useExecuteUpdate=true")
		.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(204)).setBody(constant("deleted"));

		rest().put("event/{id}").produces(MediaType.APPLICATION_JSON_VALUE).route().choice().when(new Predicate() {
			@Override
			public boolean matches(Exchange exchange) {
				final String UUID = exchange.getIn().getBody(String.class);
				return ((UUID != null)
						&& UUID.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"));
			}
		}).bean(EventJPARouter.class, "invalidId").otherwise().to("sql:{{sql.update}}")
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200)).setBody(constant("updated"));
		
		
	}

	public void invalidId(final Exchange exchange) {
		exchange.getIn().setBody("{\"msg\":\"event id is invalid\"}");
		exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
	}

}
