DROP DATABASE IF EXISTS mbari;
CREATE DATABASE mbari;
USE mbari;

DROP TABLE IF EXISTS ROVDives;
DROP TABLE IF EXISTS Expeditions;
DROP TABLE IF EXISTS Ships;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Roles;

-- Roles table
CREATE TABLE Roles (
                       roleId INT AUTO_INCREMENT PRIMARY KEY,
                       roleName VARCHAR(255) NOT NULL UNIQUE
);

-- Users table
CREATE TABLE Users (
                       userId INT AUTO_INCREMENT PRIMARY KEY,
                       firstName VARCHAR(255) NOT NULL,
                       lastName VARCHAR(255) NOT NULL,
                       emailAddress VARCHAR(255) UNIQUE NOT NULL,
                       roleId INT NOT NULL,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       FOREIGN KEY (roleId) REFERENCES Roles(roleId)
);

-- Ships table
CREATE TABLE Ships (
                       shipId INT AUTO_INCREMENT PRIMARY KEY,
                       shipName VARCHAR(255) NOT NULL,
                       shipDescription VARCHAR(8000)
);

-- Expeditions table
CREATE TABLE Expeditions (
                             expeditionId INT AUTO_INCREMENT PRIMARY KEY,
                             shipId INT,
                             purpose TEXT,
                             chiefScientistId INT,
                             principalInvestigatorId INT,
                             scheduledStartDate DATETIME,
                             scheduledEndDate DATETIME,
                             equipmentDescription TEXT,
                             participants TEXT,
                             regionDescription VARCHAR(2048),
                             plannedTrackDescription VARCHAR(6144),
                             actualStartDate DATETIME,
                             actualEndDate DATETIME,
                             accomplishments TEXT,
                             scientistComments TEXT,
                             sciObjectivesMet BOOLEAN,
                             operatorComments TEXT,
                             allEquipmentFunctioned BOOLEAN,
                             otherComments TEXT,
                             updatedBy INT,
                             FOREIGN KEY (shipId) REFERENCES Ships(shipId),
                             FOREIGN KEY (chiefScientistId) REFERENCES Users(userId),
                             FOREIGN KEY (principalInvestigatorId) REFERENCES Users(userId),
                             FOREIGN KEY (updatedBy) REFERENCES Users(userId)
);

-- ROVDives table
CREATE TABLE ROVDives (
                          diveId INT AUTO_INCREMENT PRIMARY KEY,
                          expeditionId INT,
                          rovName VARCHAR(255),
                          diveNumber VARCHAR(255),
                          diveStartDatetime DATETIME,
                          diveEndDatetime DATETIME,
                          diveChiefScientistId INT,
                          briefAccomplishments TEXT,
                          FOREIGN KEY (expeditionId) REFERENCES Expeditions(expeditionId),
                          FOREIGN KEY (diveChiefScientistId) REFERENCES Users(userId)
);

-- Insert data into Roles table
INSERT INTO Roles (roleName) VALUES
                                 ('Admin'),
                                 ('Logistics Coordinator'),
                                 ('MBARI Employee'),
                                 ('Registered User');

-- Insert data into Users table
INSERT INTO Users (firstName, lastName, emailAddress, roleId, username, password) VALUES
                                                                                      ('Andre', 'Moreno', 'amoreno@csumb.edu', 2, 'andrere', '$2a$10$3qs9BSfaFXsZnBKX93Er2OrsA99.10AUG19J4GqrhKHpdWEBB.Vmi'),
                                                                                      ('Yuki', 'Okamoto', 'yokamoto@csumb.edu', 2, 'yukiok', '$2a$10$/HMtFh/4pN0KfIbJrZ8GkeD/EJCzbVk9UnfVlyOWE.q1g/XkE9j1a'),
                                                                                      ('test', 'test', 'test@csumb.edu', 1, 'test', '$2a$10$/HMtFh/4pN0KfIbJrZ8GkeD/EJCzbVk9UnfVlyOWE.q1g/XkE9j1a');

-- Insert data into Ships table
INSERT INTO Ships (shipName, shipDescription) VALUES
                                                  ('R/V David Packard', 'first ship'),
                                                  ('R/V Rachel Carson', 'second ship'),
                                                  ('R/V Paragon', NULL);

-- Insert data into Expeditions table
INSERT INTO Expeditions (shipId, purpose, chiefScientistId, principalInvestigatorId, scheduledStartDate, scheduledEndDate, equipmentDescription, participants, regionDescription, plannedTrackDescription) VALUES
                                                                                                                                                                                                               (1, 'Deep sea exploration', 2, 1, '2023-11-01 00:00:00', '2023-11-15 00:00:00', 'ROV, Sonar, Deep-sea camera', 'John Doe, Jane Smith, Richard Roe', 'Pacific Ocean, near Mariana Trench', 'Starting from point A, moving towards point B, then deep diving at point C'),
                                                                                                                                                                                                               (2, 'Mar
