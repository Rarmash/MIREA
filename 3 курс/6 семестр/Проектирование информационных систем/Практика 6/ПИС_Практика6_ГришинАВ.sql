CREATE TABLE User (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE Laptop (
    laptop_id INT PRIMARY KEY AUTO_INCREMENT,
    model VARCHAR(100),
    specification TEXT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE DiagnosticSession (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    created_at DATE,
    status VARCHAR(50),
    user_id INT,
    laptop_id INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (laptop_id) REFERENCES Laptop(laptop_id)
);

CREATE TABLE Symptom (
    symptom_id INT PRIMARY KEY AUTO_INCREMENT,
    description TEXT,
    session_id INT,
    FOREIGN KEY (session_id) REFERENCES DiagnosticSession(session_id)
);

CREATE TABLE Diagnosis (
    diagnosis_id INT PRIMARY KEY AUTO_INCREMENT,
    preliminary_diagnosis TEXT,
    confirmed_diagnosis TEXT,
    date DATE,
    session_id INT,
    FOREIGN KEY (session_id) REFERENCES DiagnosticSession(session_id)
);

CREATE TABLE FixMethod (
    method_id INT PRIMARY KEY AUTO_INCREMENT,
    description TEXT,
    diagnosis_id INT,
    FOREIGN KEY (diagnosis_id) REFERENCES Diagnosis(diagnosis_id)
);

CREATE TABLE Report (
    report_id INT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(50),
    date DATE,
    content TEXT,
    session_id INT,
    FOREIGN KEY (session_id) REFERENCES DiagnosticSession(session_id)
);
