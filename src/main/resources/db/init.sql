-- CREATE DATABASE mbari;
USE mbari;

DROP TABLE IF EXISTS ROVDives;
DROP TABLE IF EXISTS Expeditions;
DROP TABLE IF EXISTS Persons;
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

-- Persons table
CREATE TABLE Persons (
                         personId INT AUTO_INCREMENT PRIMARY KEY,
                         firstName VARCHAR(255) NOT NULL,
                         lastName VARCHAR(255) NOT NULL,
                         emailAddress VARCHAR(255) UNIQUE NOT NULL
);

-- Expeditions table
CREATE TABLE Expeditions (
                             expeditionId INT AUTO_INCREMENT PRIMARY KEY,
                             shipId INT,
                             purpose TEXT,
                             chiefScientistId INT,
                             principalInvestigatorId INT,
                             scheduledStartDatetime DATETIME,
                             scheduledEndDatetime DATETIME,
                             equipmentDescription TEXT,
                             participants TEXT,
                             regionDescription VARCHAR(2048),
                             plannedTrackDescription VARCHAR(6144),
                             actualStartDatetime DATETIME,
                             actualEndDatetime DATETIME,
                             accomplishments TEXT,
                             scientistComments TEXT,
                             sciObjectivesMet BOOLEAN,
                             operatorComments TEXT,
                             allEquipmentFunctioned BOOLEAN,
                             otherComments TEXT,
                             updatedBy INT,
                             FOREIGN KEY (shipId) REFERENCES Ships(shipId),
                             FOREIGN KEY (chiefScientistId) REFERENCES Persons(personId),
                             FOREIGN KEY (principalInvestigatorId) REFERENCES Persons(personId),
                             FOREIGN KEY (updatedBy) REFERENCES Users(userId)
);

-- ROVDives(Remotely Operated Vehicle Dives) table
CREATE TABLE ROVDives (
                          diveId INT AUTO_INCREMENT PRIMARY KEY,
                          expeditionId INT,
                          rovName VARCHAR(255),
                          diveNumber INT,
                          diveStartDatetime DATETIME,
                          diveEndDatetime DATETIME,
                          diveChiefScientistId INT,
                          briefAccomplishments TEXT,
                          FOREIGN KEY (expeditionId) REFERENCES Expeditions(expeditionId),
                          FOREIGN KEY (diveChiefScientistId) REFERENCES Persons(personId)
);
