CREATE DATABASE IF NOT EXISTS ai_medical_assistant;
USE ai_medical_assistant;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(120) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS symptoms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(120) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS diseases (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(120) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    precautions TEXT,
    severity ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS disease_symptoms (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    disease_id BIGINT NOT NULL,
    symptom_id BIGINT NOT NULL,
    weight DECIMAL(5,2) DEFAULT 1.0,
    CONSTRAINT fk_ds_disease FOREIGN KEY (disease_id) REFERENCES diseases(id) ON DELETE CASCADE,
    CONSTRAINT fk_ds_symptom FOREIGN KEY (symptom_id) REFERENCES symptoms(id) ON DELETE CASCADE,
    UNIQUE KEY uq_disease_symptom (disease_id, symptom_id)
);

CREATE TABLE IF NOT EXISTS predictions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    symptom_payload JSON NOT NULL,
    predicted_disease VARCHAR(120) NOT NULL,
    confidence_score DECIMAL(5,4) NOT NULL,
    recommendation TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_prediction_user FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO symptoms(name, description) VALUES
('fever', 'Elevated body temperature'),
('cough', 'Persistent coughing'),
('headache', 'Pain in head region'),
('fatigue', 'General tiredness'),
('sore_throat', 'Throat irritation')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO diseases(name, description, precautions, severity) VALUES
('Common Cold', 'Viral upper respiratory tract infection.', 'Hydration, rest, warm fluids.', 'LOW'),
('Flu', 'Influenza viral infection.', 'Antiviral evaluation, rest, fluids, monitor fever.', 'MEDIUM'),
('Migraine', 'Neurological condition causing severe headaches.', 'Dark room rest, hydration, trigger avoidance.', 'MEDIUM')
ON DUPLICATE KEY UPDATE name=name;
