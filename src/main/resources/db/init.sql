DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Ships;
DROP TABLE IF EXISTS Persons;
DROP TABLE IF EXISTS Expeditions;
DROP TABLE IF EXISTS ROVDives;

-- Users table
CREATE TABLE Users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email_address VARCHAR(255) UNIQUE NOT NULL,
                       role ENUM('MBARI Employee', 'Registered User', 'Logistics Coordinator') NOT NULL,
                       password VARCHAR(255) NOT NULL
);

-- Ships table
CREATE TABLE Ships (
                       ship_id INT AUTO_INCREMENT PRIMARY KEY,
                       ship_name VARCHAR(255) NOT NULL,
                       ship_description VARCHAR(8000)
);

-- Persons table
CREATE TABLE Persons (
                         person_id INT AUTO_INCREMENT PRIMARY KEY,
                         first_name VARCHAR(255) NOT NULL,
                         last_name VARCHAR(255) NOT NULL,
                         email_address VARCHAR(255) UNIQUE NOT NULL
);

-- Expeditions table
CREATE TABLE Expeditions (
                             expedition_id INT AUTO_INCREMENT PRIMARY KEY,
                             ship_id INT,
                             purpose VARCHAR(8000),
                             chief_scientist_id INT,
                             principal_investigator_id INT,
                             scheduled_start_datetime DATETIME,
                             scheduled_end_datetime DATETIME,
                             equipment_description VARCHAR(8000),
                             participants VARCHAR(8000),
                             region_description VARCHAR(2048),
                             planned_track_description VARCHAR(6144),
                             actual_start_datetime DATETIME,
                             actual_end_datetime DATETIME,
                             accomplishments VARCHAR(8000),
                             scientist_comments VARCHAR(8000),
                             sci_objectives_met BOOLEAN,
                             operator_comments VARCHAR(8000),
                             all_equipment_functioned BOOLEAN,
                             other_comments VARCHAR(8000),
                             updated_by INT,
                             FOREIGN KEY (ship_id) REFERENCES Ships(ship_id),
                             FOREIGN KEY (chief_scientist_id) REFERENCES Persons(person_id),
                             FOREIGN KEY (principal_investigator_id) REFERENCES Persons(person_id),
                             FOREIGN KEY (updated_by) REFERENCES Users(user_id)
);

-- ROVDives(Remotely Operated Vehicle Dives) table
CREATE TABLE ROVDives (
                          dive_id INT AUTO_INCREMENT PRIMARY KEY,
                          expedition_id INT,
                          rov_name VARCHAR(255),
                          dive_number INT,
                          dive_start_datetime DATETIME,
                          dive_end_datetime DATETIME,
                          dive_chief_scientist_id INT,
                          brief_accomplishments VARCHAR(8000),
                          FOREIGN KEY (expedition_id) REFERENCES Expeditions(expedition_id),
                          FOREIGN KEY (dive_chief_scientist_id) REFERENCES Persons(person_id)
);


-- Users sample data
INSERT INTO Users (first_name, last_name, email_address, role, password) VALUES
                                                                             ('John', 'Doe', 'john.doe@mbari.org', 'MBARI Employee', 'hashed_password1'),
                                                                             ('Jane', 'Smith', 'jane.smith@mbari.org', 'Logistics Coordinator', 'hashed_password2'),
                                                                             ('Alice', 'Johnson', 'alice.johnson@mbari.org', 'Registered User', 'hashed_password3');

-- Ships sample data
INSERT INTO Ships (ship_name, ship_description) VALUES
                                                    ('R/V David Packard', 'Description about R/V David Packard'),
                                                    ('R/V Rachel Carson', 'Description about R/V Rachel Carson'),
                                                    ('R/V Paragon', 'Description about R/V Paragon');

-- Persons sample data
INSERT INTO Persons (first_name, last_name, email_address) VALUES
                                                               ('Robert', 'Brown', 'robert.brown@mbari.org'),
                                                               ('Emily', 'Clark', 'emily.clark@mbari.org'),
                                                               ('Michael', 'White', 'michael.white@mbari.org');

-- Expeditions sample data
INSERT INTO Expeditions (ship_id, purpose, chief_scientist_id, principal_investigator_id, scheduled_start_datetime, scheduled_end_datetime, equipment_description, participants, region_description, planned_track_description, updated_by) VALUES
    (1, 'Research on deep sea creatures', 1, 2, '2023-10-01 09:00:00', '2023-10-15 18:00:00', 'Deep sea cameras, sonar', 'John Doe, Jane Smith', 'Pacific Ocean', 'Route from A to B', 1);

-- ROVDives sample data
INSERT INTO ROVDives (expedition_id, rov_name, dive_number, dive_start_datetime, dive_end_datetime, dive_chief_scientist_id, brief_accomplishments) VALUES
    (1, 'Ventana', 101, '2023-10-02 10:00:00', '2023-10-02 14:00:00', 3, 'Discovered a new species of fish');

