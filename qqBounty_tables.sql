/*******************************************************************************
   Create Tables
********************************************************************************/
--DROP TABLE ANSWERS CASCADE;
--DROP TABLE ANSWERSTATUS CASCADE;
--DROP TABLE BOUNTIES CASCADE;
--DROP TABLE BOUNTYSTATUS CASCADE;
--DROP TABLE SUBJECTS CASCADE;
--DROP TABLE USERROLES CASCADE;
--DROP TABLE USERS CASCADE;
--DROP TABLE WALLET CASCADE;

CREATE TABLE AnswerStatus
(
	status_id 	NUMERIC     PRIMARY KEY,
	name  	VARCHAR(20) 	NOT NULL
);

CREATE TABLE Answers
(
    answer_id  		SERIAL 		    PRIMARY KEY,
    description 	VARCHAR(511)	NOT NULL, 
    submitted   	TIMESTAMP 		NOT NULL,
    votes 			INTEGER 		NOT NULL DEFAULT 0,
    status_id 		INTEGER 		NOT NULL REFERENCES AnswerStatus (status_id)		
);

CREATE TABLE Roles
(
    role_id 	NUMERIC     PRIMARY KEY,
	role_name  VARCHAR(10) NOT NULL
);

CREATE TABLE Wallet
(
	wallet_id		SERIAL     PRIMARY KEY,
	wallet_balance	INTEGER 	NOT NULL DEFAULT 0
	
);


CREATE TABLE Users
(
    user_id    	 	SERIAL        	PRIMARY KEY,
    username   		VARCHAR (50)  	NOT NULL UNIQUE,
    password   		VARCHAR (50)  	NOT NULL,
	email      		VARCHAR (150) 	NOT NULL UNIQUE,
	picture	   		VARCHAR (150),
	rating	   		NUMERIC			NOT NULL DEFAULT 0,
	wallet_id 	   	INTEGER			NOT NULL DEFAULT 1 REFERENCES Wallet (wallet_id),
	role_id 		INTEGER 		NOT NULL REFERENCES Roles (role_id)
);

CREATE TABLE BountyStatus
(
    bounty_status_id 	NUMERIC     	PRIMARY KEY,
	status    			VARCHAR(20) 	NOT NULL
);

CREATE TABLE Subjects
(
    subject_id 		NUMERIC     PRIMARY KEY,
	subject_type   VARCHAR(20) 	NOT NULL
);

CREATE TABLE Bounties
(
    bounty_id  			SERIAL 		    PRIMARY KEY,
    description 		VARCHAR(511)	NOT NULL, 
    submitted   		TIMESTAMP,
    amount 	    		INTEGER			NOT NULL DEFAULT 0,
    votes 				INTEGER 		NOT NULL DEFAULT 0,
    timer	 			INTEGER			NOT NULL,
    bounty_status_id 	INTEGER 		NOT NULL REFERENCES BountyStatus (bounty_status_id),
	correct_answer 		INTEGER	    	REFERENCES 	Answers (answer_id),
	bounty_picture 		VARCHAR (150), 
	user_id 		 	INTEGER 		NOT NULL REFERENCES Users (user_id),
	subject_id			INTEGER			NOT NULL REFERENCES Subjects (subject_id)
	);

--when user users are first generated they get the default wallet 1 with balance 0
-- wallet 1 is kinda like /dev/null

INSERT INTO qqbounty.users
(username, "password", email, picture, rating, wallet_id, role_id)
VALUES('pluto', 'goldmine', 'pluto@mail.com', '', 5, 1, 1);

--initialize bounty status table

INSERT INTO qqbounty.bountystatus
(bounty_status_id, status)
VALUES (1, 'open'),(2, 'frozen');

--initialize subjects table
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(1, 'math');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(2, 'physics');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(3, 'economics');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(4, 'politics');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(5, 'chemisty');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(6, 'cryptography');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(7, 'software');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(8, 'hardware');
INSERT INTO qqbounty.subjects
(subject_id, subject_type)
VALUES(9, 'philosophy');


--initialize answer status 
INSERT INTO qqbounty.answerstatus
(status_id, "name")
VALUES(1, 'review');
INSERT INTO qqbounty.answerstatus
(status_id, "name")
VALUES(2, 'reject');
INSERT INTO qqbounty.answerstatus
(status_id, "name")
VALUES(3, 'approve');


--initialize answer table
INSERT INTO qqbounty.answers
(description, submitted, votes, status_id)
VALUES('42 is the answer to all things', '2018-11-22', 0, 1);


SELECT * FROM USERS;
SELECT * FROM WALLET;
SELECT * FROM BOUNTYSTATUS;
SELECT * FROM BOUNTIES;
SELECT * FROM SUBJECTS;
SELECT * FROM ANSWERSTATUS;
SELECT * FROM ANSWERS;
