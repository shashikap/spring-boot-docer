package com.randoli.event.consumer.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerRequest {

	private UUID batchId;
	private List<RecordData> records;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	class RecordData {
		private UUID transId;
		private String transTms;
		private String rcNum;
		private String clientId;
		private List<EventData> event;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	class EventData {
		private Integer eventCnt;
		private String locationCd;
		private String locationId1;
		private String locationId2;
		private String addrNbr;

	}
}
