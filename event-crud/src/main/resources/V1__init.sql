
CREATE TABLE IF NOT EXISTS event (
  	eventId uuid PRIMARY KEY,
	transId uuid,
	transTms TIMESTAMP,
	rcNum VARCHAR(25),
	clientId VARCHAR(25),
	eventCnt int,
	locationCd VARCHAR(25),
	locationId1 VARCHAR(25),
	locationId2 VARCHAR(25),
  	addrNbr VARCHAR(25)
)
