CREATE TABLE Classroom (
    ClassroomID INT AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(50) NOT NULL UNIQUE,
    Type        VARCHAR(50) NOT NULL,
    Location    VARCHAR(100) NOT NULL
);

CREATE TABLE GroupName (
    GroupNameID INT AUTO_INCREMENT PRIMARY KEY,
    Name        VARCHAR(50) NOT NULL UNIQUE,
    Semester    INT NOT NULL,
    ClassroomID INT NULL,
    FOREIGN KEY (ClassroomID) REFERENCES Classroom(ClassroomID)
        ON UPDATE CASCADE
        ON DELETE SET NULL
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
    GroupNameID  INT NOT NULL,
    FOREIGN KEY (GroupNameID) REFERENCES GroupName(GroupNameID)
        ON UPDATE CASCADE
);

CREATE TABLE Signature (
    SignatureID  INT AUTO_INCREMENT PRIMARY KEY,
    Name         VARCHAR(100) NOT NULL UNIQUE,
    WorkerNumber VARCHAR(20) NULL,
    FOREIGN KEY (WorkerNumber) REFERENCES Professor(WorkerNumber)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE Admin (
    AdminID    INT AUTO_INCREMENT PRIMARY KEY,
    Username   VARCHAR(50) NOT NULL UNIQUE,
    Passphrase VARCHAR(255) NOT NULL
);
