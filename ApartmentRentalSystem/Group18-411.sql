CREATE DATABASE IF NOT EXISTS apartment_rental;
USE apartment_rental;

CREATE TABLE Users(
 user_id INTEGER AUTO_INCREMENT,
 first_name VARCHAR(255) NOT NULL,
 last_name VARCHAR(255) NOT NULL,
 email VARCHAR(255) NOT NULL UNIQUE,
 birth_date DATE,
 hometown VARCHAR(255),
 gender VARCHAR(100),
 password VARCHAR(255) NOT NULL,
 PRIMARY KEY (user_id)
 );
 
 CREATE TABLE Manager(
 manager_id INTEGER AUTO_INCREMENT,
 first_name VARCHAR(255) NOT NULL,
 last_name VARCHAR(255) NOT NULL,
 email VARCHAR(255) NOT NULL UNIQUE,
 password VARCHAR(255) NOT NULL,
 PRIMARY KEY (manager_id)
 );
 
CREATE TABLE Apartment(
 apartment_id INTEGER AUTO_INCREMENT,
 apt_description VARCHAR(100),
 data LONGBLOB,
 user_id INTEGER NOT NULL,
 PRIMARY KEY (apartment_id),
 FOREIGN KEY (user_id) REFERENCES Users (user_id)
); 

CREATE TABLE Tags(
 tag_id INTEGER AUTO_INCREMENT,
 name VARCHAR(100),
 PRIMARY KEY (tag_id)
);

CREATE TABLE Tagged(
 apartment_id INTEGER,
 tag_id INTEGER,
 PRIMARY KEY (apartment_id, tag_id),
 FOREIGN KEY(apartment_id)
 REFERENCES Apartment (apartment_id),
 FOREIGN KEY(tag_id)
 REFERENCES Tags (tag_id)
);

INSERT INTO Users (email, password, first_Name, last_Name, hometown, gender, birth_date) VALUES ('test1@bu.edu', 'test', 'Mike', 'Smith', 'Boston', 'M', '2000-1-1');
INSERT INTO Users (email, password, first_Name, last_Name, hometown, gender, birth_date) VALUES ('test2@bu.edu', 'test', 'Sarah', 'Black', 'Boston', 'F', '2000-1-1');
INSERT INTO Users (email, password, first_Name, last_Name, hometown, gender, birth_date) VALUES ('test3@bu.edu', 'test', 'Jhon', 'Black', 'New York', 'M', '2000-1-1');
INSERT INTO Users (email, password, first_Name, last_Name, hometown, gender, birth_date) VALUES ('test4@bu.edu', 'test', 'Lisa', 'White',  'Santa Monica', 'F', '2000-1-1');
