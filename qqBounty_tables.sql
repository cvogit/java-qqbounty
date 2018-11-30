 DROP TABLE answers CASCADE;
 DROP TABLE answerstatus CASCADE;
 DROP TABLE bounties CASCADE;
 DROP TABLE bountystatus CASCADE;
 DROP TABLE roles CASCADE;
 DROP TABLE subjects CASCADE;
 DROP TABLE users CASCADE;
 DROP TABLE wallets CASCADE;
 DROP TABLE SubjectsToBounties;
 DROP TABLE AnswersToUsers;
 DROP TABLE Products CASCADE;
 DROP TABLE UserProducts;


CREATE TABLE Roles
(
    role_id 		NUMERIC     PRIMARY KEY,
    role 			VARCHAR(10) NOT NULL
);

CREATE TABLE Wallets
(
    wallet_id		SERIAL     PRIMARY KEY,
    balance			INTEGER    NOT NULL DEFAULT 0
);

CREATE TABLE Users
(
	user_id    	 	SERIAL        	PRIMARY KEY,
	username   		VARCHAR (50)  	NOT NULL UNIQUE,
	password   		VARCHAR (64)  	NOT NULL,
	email      		VARCHAR (150) 	NOT NULL UNIQUE,
	picture	   		VARCHAR (150),
	rating	   		NUMERIC			NOT NULL DEFAULT 0,
	wallet_id		INTEGER			NOT NULL REFERENCES Wallets (wallet_id),
	role_id			INTEGER 		NOT NULL REFERENCES Roles (role_id)
);

CREATE TABLE AnswerStatus
(
    answer_status_id 	NUMERIC     	PRIMARY KEY,
    answer_status  		VARCHAR(20) 	NOT NULL
);

CREATE TABLE Answers
(
    answer_id  		SERIAL 		    PRIMARY KEY,
	user_id         INTEGER         NOT NULL REFERENCES Users(user_id),
    description 	VARCHAR(511)	NOT NULL, 
    submitted   	TIMESTAMP 	    NOT NULL,
    votes			INTEGER 	    NOT NULL DEFAULT 0,
    status_id 		INTEGER 	    NOT NULL REFERENCES AnswerStatus (answer_status_id),
    bounty_id		INTEGER			NOT NULL 
);

CREATE TABLE BountyStatus
(
	bounty_status_id 	NUMERIC     	PRIMARY KEY,
	bounty_status    	VARCHAR(20) 	NOT NULL
);

CREATE TABLE Subjects
(
    subject_id		NUMERIC			PRIMARY KEY,
	subject   		VARCHAR(20)		NOT NULL
);

CREATE TABLE Bounties
(
	bounty_id  			SERIAL 		    PRIMARY KEY,
	description 		VARCHAR(511)	NOT NULL, 
	submitted   		TIMESTAMP,
	amount 	    		INTEGER			NOT NULL DEFAULT 0,
	votes 				INTEGER 		NOT NULL DEFAULT 0,
	timer	 			INTEGER			NOT NULL,
	status_id 			INTEGER 		NOT NULL REFERENCES BountyStatus (bounty_status_id),
	correct_answer_id 	INTEGER	    	REFERENCES 	Answers (answer_id),
	picture 			VARCHAR (150), 
	user_id 		 	INTEGER 		NOT NULL REFERENCES Users (user_id)
);

CREATE TABLE SubjectsToBounties
(    
    subjects_to_bounties_id	SERIAL 		PRIMARY KEY,
    bounty_id				INTEGER		NOT NULL REFERENCES Bounties (bounty_id),
    subject_id				INTEGER		NOT NULL REFERENCES Subjects (subject_id)
);


CREATE TABLE AnswersToUsers
(    
    vote_id				SERIAL 		PRIMARY KEY,
    user_id		    	INTEGER		NOT NULL REFERENCES Users (user_id),
    answer_id			INTEGER		NOT NULL REFERENCES Answers (answer_id)
);

CREATE TABLE Products 

(
	product_id			SERIAL	    	PRIMARY KEY,
	product_name 		VARCHAR (50)	NOT NULL,
	product_credit		INTEGER			NOT NULL,
	product_cost		INTEGER			NOT NULL
);

CREATE TABLE UserProducts
(
	user_product_id		SERIAL 		PRIMARY KEY,
	user_id				INTEGER		NOT NULL REFERENCES Users (user_id),
	product_id			INTEGER		NOT NULL REFERENCES Products (product_id),
	purchase_date		TIMESTAMP	
);


ALTER TABLE Answers ADD CONSTRAINT bounty_id_fk
	FOREIGN KEY (bounty_id) REFERENCES Bounties (bounty_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

INSERT INTO qqbounty.roles (role_id, role) VALUES(1, 'user');

INSERT INTO qqbounty.wallets
(balance)
VALUES(100);

INSERT INTO qqbounty.users
(username, "password", email, picture, rating, wallet_id, role_id)
VALUES('testuser', 'password', 'email@mail.com', '', 0, 1, 1);


INSERT INTO qqbounty.bountystatus (bounty_status_id , bounty_status) VALUES(1, 'posted');
INSERT INTO qqbounty.bountystatus (bounty_status_id , bounty_status) VALUES(2, 'answered');
INSERT INTO qqbounty.bountystatus (bounty_status_id , bounty_status) VALUES(3, 'expired');

INSERT INTO qqbounty.subjects (subject_id , subject) VALUES(1, 'Math');
INSERT INTO qqbounty.subjects (subject_id , subject) VALUES(2, 'Programming');

INSERT INTO qqbounty.answerstatus (answer_status_id,answer_status ) VALUES(1, 'submitted');
INSERT INTO qqbounty.answerstatus (answer_status_id,answer_status ) VALUES(2, 'reported');
INSERT INTO qqbounty.answerstatus (answer_status_id,answer_status ) VALUES(3, 'best');


INSERT INTO qqbounty.bounties (description, submitted, amount, votes, timer, status_id, correct_answer_id, picture, user_id)VALUES('test bounty', CURRENT_TIMESTAMP, 100, 0, 7000000, 1, null, '', 1);

INSERT INTO qqbounty.answers (answer_id, user_id,description,submitted,votes,status_id, bounty_id) VALUES(1,1, 'No answer given yet',CURRENT_TIMESTAMP,0,1,1);

INSERT INTO qqbounty.products
(product_id, product_name, product_credit, product_cost)
VALUES(1, 'basic', 100, 1);
INSERT INTO qqbounty.products
(product_id, product_name, product_credit, product_cost)
VALUES(2, 'deluxe', 1000, 10);
INSERT INTO qqbounty.products
(product_id, product_name, product_credit, product_cost)
VALUES(3, 'wombo combo', 2000, 20);


SELECT * FROM PRODUCTS;