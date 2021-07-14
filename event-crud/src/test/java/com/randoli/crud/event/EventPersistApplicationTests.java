package com.randoli.crud.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.UUID;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.randoli.crud.event.dao.model.Event;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventPersistApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private static Event testPayloadEvent;

	private static Event persistPayload;

	private static final UUID DUMMY_UUID = UUID.randomUUID();

	@Test
	public void contextLoads() {

	}

	@Before
	public void buildDummyEvent() {
		testPayloadEvent = buildEvent();
	}

	@Test
	public void saveEventTest() {
		HttpEntity<Event> request = new HttpEntity<>(testPayloadEvent, getJsonHeaders());
		ResponseEntity<Event> response = restTemplate.exchange("/api/event", HttpMethod.POST, request, Event.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		persistPayload = response.getBody();
	}

	@Test
	public void listEventTest() {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/events", String.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void updateEventTest() {
		HttpEntity<Event> request = new HttpEntity<>(persistPayload, getJsonHeaders());
		ResponseEntity<Event> response = restTemplate.exchange("/api/event", HttpMethod.PUT, request, Event.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void getEventTest() {
		ResponseEntity<Event> response = restTemplate.getForEntity("/api/event/" + persistPayload.getEventId(),
				Event.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void deleteEventTest() {
		restTemplate.delete("/api/event/" + persistPayload.getEventId());
		//assertDoesNotThrow(() -> RuntimeException());
	}

	private static Event buildEvent() {
		return Event.builder().transId(DUMMY_UUID).transTms("20151022102011927EDT").rcNum("10002").clientId("RPS-00001")
				.eventCnt(4).locationCd("DESTINATION").locationId1("T8C").locationId2("1J7").addrNbr("0000000001")
				.build();

	}

	private HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}
}
