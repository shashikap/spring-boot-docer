package com.randoli.event.consumer.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {

	//private UUID eventId;

	private UUID transId;

	private String transTms;

	private String rcNum;

	private String clientId;

	private Integer eventCnt;

	private String locationCd;

	private String locationId1;

	private String locationId2;

	private String addrNbr;
}
