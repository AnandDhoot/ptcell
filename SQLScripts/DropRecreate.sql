drop table application;
drop table student;
drop table jaf;
drop table company;
drop table coordinator;

DROP TYPE Gender;
DROP TYPE CompanyCategory;
DROP TYPE DepartmentList;

CREATE TYPE DepartmentList AS ENUM ('AE', 'CS', 'EE', 'ME', 'PH');
CREATE TYPE CompanyCategory AS ENUM('1', '2', '3');
CREATE TYPE Gender AS ENUM('F', 'M');

CREATE TABLE Coordinator
(
  CoordinatorID CHAR(6) NOT NULL,
  Department DepartmentList NOT NULL,
  Name VARCHAR(50) NOT NULL,
  EmailID VARCHAR(50) NOT NULL,
  MobileNumber INT NOT NULL,
  Address VARCHAR(50) NOT NULL,
  Password VARCHAR(20) NOT NULL,
  PRIMARY KEY (CoordinatorID)
);

CREATE TABLE Student
(
  RollNumber CHAR(9) NOT NULL,
  EmailID VARCHAR(50) NOT NULL,
  MobileNumber INT NOT NULL,
  Address VARCHAR(50) NOT NULL,
  Password VARCHAR(20) NOT NULL,
  Sex Gender NOT NULL,
  Department DepartmentList NOT NULL,
  Year INT NOT NULL,
  Name VARCHAR(50) NOT NULL,
  CPI FLOAT NOT NULL,
  CoordinatorID CHAR(6) NOT NULL,
  Approved INT NOT NULL DEFAULT 0,
  Resume BYTEA, 
  PRIMARY KEY (RollNumber),
  FOREIGN KEY (CoordinatorID) REFERENCES Coordinator(CoordinatorID)
);

CREATE TABLE Company
(
  CompanyID CHAR(5) NOT NULL,
  CoordinatorID CHAR(6) NOT NULL,
  Name VARCHAR(100) NOT NULL,
  Category CompanyCategory NOT NULL,
  Password VARCHAR(20) NOT NULL,
  PRIMARY KEY (CompanyID),
  FOREIGN KEY (CoordinatorID) REFERENCES Coordinator(CoordinatorID)
);

CREATE TABLE JAF
(
  CompanyID CHAR(5) NOT NULL,
  JAFNumber INT NOT NULL,
  EndTime TIMESTAMP NOT NULL,
  CPICutoff FLOAT NOT NULL,
  DeptEligible INT NOT NULL,
  Location VARCHAR(20) NOT NULL,
  Salary INT NOT NULL,
  Description VARCHAR(1000) NOT NULL,
  Stage INT NOT NULL,
  Profile VARCHAR(25) NOT NULL,
  PRIMARY KEY (JAFNumber, CompanyID),
  FOREIGN KEY (CompanyID) REFERENCES Company(CompanyID)
);

CREATE TABLE Application
(
  RollNumber CHAR(9) NOT NULL,
  JAFNumber INT NOT NULL,
  CompanyID CHAR(5) NOT NULL,
  Status INT NOT NULL,
  ApplnTime TIMESTAMP NOT NULL,
  PRIMARY KEY (CompanyID, JAFNumber, RollNumber),
  FOREIGN KEY (JAFNumber, CompanyID) REFERENCES JAF(JAFNumber, CompanyID),
  FOREIGN KEY (RollNumber) REFERENCES Student(RollNumber)
);