CREATE TABLE Classroom (
    ClassroomID INT AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(50) NOT NULL UNIQUE,
    Type        VARCHAR(50),
    Location    VARCHAR(100)
);

CREATE TABLE GroupName (
    GroupNameID INT AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(50) UNIQUE NOT NULL,
    Semester    INT NOT NULL,
    ClassroomID INT,
    FOREIGN KEY (ClassroomID) REFERENCES Classroom(ClassroomID) ON UPDATE CASCADE
);

CREATE TABLE Professor (
    WorkerNumber VARCHAR(20) PRIMARY KEY,
    FirstName    VARCHAR(50) NOT NULL,
    LastName     VARCHAR(50) NOT NULL
);

CREATE TABLE Students (
    CountNumber  VARCHAR(20) PRIMARY KEY,
    FirstName    VARCHAR(50) NOT NULL,
    LastName     VARCHAR(50) NOT NULL,
    GroupNameID  INT,
    FOREIGN KEY (GroupNameID) REFERENCES GroupName(GroupNameID) ON UPDATE CASCADE
);

CREATE TABLE Signature (
    SignatureID  INT AUTO_INCREMENT PRIMARY KEY,
    Name         VARCHAR(100) NOT NULL UNIQUE,
    WorkerNumber VARCHAR(20),
    FOREIGN KEY (WorkerNumber) REFERENCES Professor(WorkerNumber) ON UPDATE CASCADE
);

CREATE TABLE Admin (
    AdminID    INT AUTO_INCREMENT PRIMARY KEY,
    Username   VARCHAR(50) NOT NULL UNIQUE,
    Passphrase VARCHAR(255) NOT NULL
);
