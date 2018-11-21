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
	status_id 	NUMERIC     	PRIMARY KEY,
	name  	VARCHAR(20) 	NOT NULL
);

CREATE TABLE Answers
(
    answer_id  		SERIAL 		    PRIMARY KEY,
    description 	VARCHAR(511)	NOT NULL, 
    submitted   	TIMESTAMP 		NOT NULL,
    votes 			INTEGER 		NOT NULL DEFAULT 0,
    status_id 	INTEGER 			NOT NULL REFERENCES AnswerStatus (status_id),
    bounty_id		INTEGER			
);

CREATE TABLE Roles
(
    role_id 	NUMERIC     PRIMARY KEY,
	role_name  VARCHAR(10) NOT NULL
);

CREATE TABLE Wallet
(
	wallet_id		INTEGER     PRIMARY KEY,
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
	wallet_id 	   	INTEGER			NOT NULL DEFAULT 0 REFERENCES Wallet (wallet_id),
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
    submitted   		TIMESTAMP 		NOT NULL,
    amount 	    		INTEGER			NOT NULL DEFAULT 0,
    votes 				INTEGER 		NOT NULL DEFAULT 0,
    timer	 			INTEGER			NOT NULL,
    bounty_status_id 	INTEGER 		NOT NULL REFERENCES BountyStatus (bounty_status_id),
	correct_answer 		INTEGER	    	REFERENCES 	Answers (answer_id),
	bountyPicture 		VARCHAR (150), 
	user_id 		 	INTEGER 		NOT NULL REFERENCES Users (user_id),
	subject_id			INTEGER			NOT NULL REFERENCES Subjects (subject_id)
	);





