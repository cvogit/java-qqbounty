CREATE TABLE AnswerStatus
(
	answer_status_id	NUMERIC			PRIMARY KEY,
	answer_status		VARCHAR(20)		NOT NULL
);

CREATE TABLE Answers
(
	answer_id		SERIAL			PRIMARY KEY,
	description		VARCHAR(511)	NOT NULL, 
	submitted		TIMESTAMP 		NOT NULL,
	votes 			INTEGER 		NOT NULL DEFAULT 0,
	status_id 		INTEGER 		NOT NULL REFERENCES AnswerStatus (answer_status_id)		
);

CREATE TABLE Roles
(
	role_id 	NUMERIC		PRIMARY KEY,
	role 	 	VARCHAR(10) NOT NULL
);

CREATE TABLE Wallets
(
	wallet_id		SERIAL		PRIMARY KEY,
	balance			INTEGER 	NOT NULL DEFAULT 0
);

CREATE TABLE Users
(
	user_id			SERIAL			PRIMARY KEY,
	username		VARCHAR (50)  	NOT NULL UNIQUE,
	password		VARCHAR (50)  	NOT NULL,
	email			VARCHAR (150) 	NOT NULL UNIQUE,
	picture			VARCHAR (150),
	rating			NUMERIC			NOT NULL DEFAULT 0,
	wallet_id		INTEGER			NOT NULL REFERENCES Wallets (wallet_id),
	role_id 		INTEGER 		NOT NULL REFERENCES Roles (role_id)
	wallet_id		SERIAL			PRIMARY KEY,
	balance			INTEGER			NOT NULL DEFAULT 0
);

CREATE TABLE Users
{
	user_id			SERIAL			PRIMARY KEY,
	username		VARCHAR (50)	NOT NULL UNIQUE,
	password		VARCHAR (64)	NOT NULL,
	email			VARCHAR (150)	NOT NULL UNIQUE,
	picture			VARCHAR (150),
	rating			NUMERIC			NOT NULL DEFAULT 0,
	wallet_id		INTEGER			NOT NULL REFERENCES Wallets (wallet_id),
	role_id			INTEGER			NOT NULL REFERENCES Roles (role_id)
);

CREATE TABLE BountyStatus
(
	bounty_status_id 	NUMERIC		PRIMARY KEY,
	stats				VARCHAR(20) 	NOT NULL
);

CREATE TABLE Subjects
(
	subject_id 		NUMERIC			PRIMARY KEY,
	subject   		VARCHAR(20)		NOT NULL
);

CREATE TABLE Bounties
(
	bounty_id  			SERIAL			PRIMARY KEY,
	description 		VARCHAR(511)	NOT NULL, 
	submitted			TIMESTAMP,
	amount				INTEGER			NOT NULL DEFAULT 0,
	votes 				INTEGER 		NOT NULL DEFAULT 0,
	timer	 			INTEGER			NOT NULL,
	status_id 			INTEGER 		NOT NULL REFERENCES BountyStatus (bounty_status_id),
	answer_id 			INTEGER			REFERENCES 	Answers (answer_id),
	picture 			VARCHAR (150), 
	user_id 		 	INTEGER			NOT NULL REFERENCES Users (user_id),
	subject_id			INTEGER			NOT NULL REFERENCES Subjects (subject_id)
);

INSERT INTO qqbounty.roles
(role_id, role)
VALUES(1, 'user');