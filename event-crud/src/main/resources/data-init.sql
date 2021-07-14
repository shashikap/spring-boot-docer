CREATE TABLE IF NOT EXISTS event (
  	event_id uuid PRIMARY KEY,
	trans_id uuid,
	trans_time VARCHAR(50),
	rc_number VARCHAR(25),
	client_id VARCHAR(25),
	event_count int,
	location_code VARCHAR(25),
	location_id1 VARCHAR(25),
	location_id2 VARCHAR(25),
  	addr_number VARCHAR(25)
);

CREATE TABLE IF NOT EXISTS event22 (
  	eventId uuid PRIMARY KEY,
	transId uuid,
	transTms VARCHAR(50),
	rcNum VARCHAR(25),
	clientId VARCHAR(25),
	eventCnt int,
	locationCd VARCHAR(25),
	locationId1 VARCHAR(25),
	locationId2 VARCHAR(25),
  	addrNbr VARCHAR(25)
)
