/*******************************************************************************
   Create Tables
********************************************************************************/

CREATE TABLE Answers
(
    answerId  SERIAL 		    PRIMARY KEY,
    description VARCHAR(511)	NOT NULL, 
    submitted   TIMESTAMP 		NOT NULL,
    votes 		INTEGER 		NOT NULL DEFAULT 0,
    statusId 	INTEGER 		NOT NULL
);


CREATE TABLE Bounties
(
    bountyId  SERIAL 		    PRIMARY KEY,
    description VARCHAR(511)	NOT NULL, 
    submitted   TIMESTAMP 		NOT NULL,
    amount 	    INTEGER			NOT NULL DEFAULT 0,
    votes 		INTEGER 		NOT NULL DEFAULT 0,
    timer	 	INTEGER			NOT NULL,
    statusId 	INTEGER 		NOT NULL,
	correctAnswer INTEGER	    REFERENCES Answers (answerId),
	bountyPicture VARCHAR (150) 
);


CREATE TABLE Users
(
    userId     SERIAL        PRIMARY KEY,
    username   VARCHAR (50)  NOT NULL UNIQUE,
    password   VARCHAR (50)  NOT NULL,
	email      VARCHAR (150) NOT NULL UNIQUE,
	picture	   VARCHAR (150),
	roleId     INTEGER 		NOT NULL,
	rating	   NUMERIC		NOT NULL DEFAULT 0,
	wallet 	   INTEGER		NOT NULL DEFAULT 0	
);

CREATE TABLE BountyStatus
(
    statusId NUMERIC     PRIMARY KEY,
	status   VARCHAR(20) NOT NULL
);   

CREATE TABLE Subjects
(
    subjectId NUMERIC     PRIMARY KEY,
	subjectType   VARCHAR(20) NOT NULL
);

CREATE TABLE UserRoles
(
    userRoleId NUMERIC     PRIMARY KEY,
	userRole   VARCHAR(10) NOT NULL
);

CREATE TABLE Analytics
(
	totalAmount NUMERIC     PRIMARY KEY,
	usersWithMoney  INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE BountyToAnswers
(
	bountyToAnswerId  SERIAL    PRIMARY KEY,
    bountyId 		  INTEGER   REFERENCES Bounties (bountyId),
	answerId 		  INTEGER   REFERENCES Answers (answerId)
);

CREATE TABLE BountyToSubjects
(
	bountyToSubjectId  SERIAL    PRIMARY KEY,
    bountyId 		  INTEGER   REFERENCES Bounties (bountyId),
	subjectId 		  INTEGER   REFERENCES Subjects (subjectId)
);

CREATE TABLE UserToBounties
(
	userToBountyId	  SERIAL    PRIMARY KEY,
	userId 		 	  INTEGER   REFERENCES Users (userId),
    bountyId 		  INTEGER   REFERENCES Bounties (bountyId)
);
