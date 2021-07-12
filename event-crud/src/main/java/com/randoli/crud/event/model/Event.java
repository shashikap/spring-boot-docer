package com.randoli.crud.event.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonProperty;

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
@Entity(name = "event")
@Table(name = "event")
@NamedQuery(name = "findAll", query = "select b from event b")
public class Event {

	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@Column(name="event_id")
	private UUID eventId;

	@JsonProperty
	@Column(name="trans_id")
	private UUID transId;

	@JsonProperty
	@Column(name="trans_time")
	private String transTms;
	
	@JsonProperty
	@Column(name="rc_number")
	private String rcNum;

	@JsonProperty
	@Column(name="client_id")
	private String clientId;
	
	@JsonProperty
	@Column(name="event_count")
	private Integer eventCnt;
	
	@JsonProperty
	@Column(name="location_code")
	private String locationCd;
	
	@JsonProperty
	@Column(name="location_id1")
	private String locationId1;
	
	@JsonProperty
	@Column(name="location_id2")
	private String locationId2;
	
	@JsonProperty
	@Column(name="addr_number")
	private String addrNbr;
}
