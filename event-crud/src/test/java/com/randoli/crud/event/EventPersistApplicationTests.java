package com.randoli.crud.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.randoli.crud.event.model.Event;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class EventPersistApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private static Event dummyEvent;

	private static final UUID DUMMY_UUID = UUID.randomUUID();

	@Test
	void contextLoads() {

	}

	@BeforeAll
	public static void buildDummyEvent() {
		dummyEvent = buildEvent();
	}

	@Test
	public void saveEventTest() throws JsonMappingException, JsonProcessingException {
		HttpEntity<Event> request = new HttpEntity<>(dummyEvent, getJsonHeaders());

		ResponseEntity<String> response = restTemplate.exchange("/api/event", HttpMethod.POST, request, String.class);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

	@Test
	public void listEventTest() throws JsonMappingException, JsonProcessingException {
		ResponseEntity<String> response = restTemplate.getForEntity("/api/event", String.class);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void deleteEventTest() throws JsonMappingException, JsonProcessingException {

		ResponseEntity<String> response = restTemplate.exchange("/api/event/trans-id/" + DUMMY_UUID, HttpMethod.DELETE,
				null, String.class);
		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
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
