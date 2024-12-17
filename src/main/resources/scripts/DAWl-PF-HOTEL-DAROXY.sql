-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS HotelDB;
USE HotelDB;

CREATE TABLE `User` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
	`role` VARCHAR(50) NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar registros con datos reales
INSERT INTO `User` (username, password, email, first_name, last_name, role)
VALUES
('john.doe', '$2a$12$3AmSNJug8GgSWGDnhVok5uQdTmzZml0wVX0oD6zJWrFLmTVuv.mhy', 'john.doe@mail.com', 'John', 'Doe', 'ADMIN'),
('jane.smith', '$2a$12$huxJ5GYadPSR1iTi2OO63.WdteU7ZrL6.LUVButbTobSmWRRgNH2m', 'jane.smith@mail.com', 'Jane', 'Smith', 'ADMIN'),
('michael.brown', '$2a$12$yvP6mnzq4wdIszUyCDn/UefRtzzr01qOXF7pA6vA8efq3ixgsvH9C', 'michael.brown@mail.com', 'Michael', 'Brown', 'OPERATOR'),
('emily.johnson', '$2a$12$rKf5dwuZxVfogVITbbftcOpDZBNdz013jwnzjykfEF6D1zy/wb.Hi', 'emily.johnson@mail.com', 'Emily', 'Johnson', 'OPERATOR'),
('chris.davis', '$2a$12$.LkymDGE1fmO7pb/EBV6lec5BeGMgGCxy9gFssc/YyG9dXSO404YG', 'chris.davis@mail.com', 'Chris', 'Davis', 'OPERATOR'),
('patricia.wilson', '$2a$12$HKL7gsoVFD9ET.93LLevtO.3UhC0douWfsmUPmxs7kkNKKNDDvQxe', 'patricia.wilson@mail.com', 'Patricia', 'Wilson', 'OPERATOR'),
('linda.martinez', '$2a$12$9fVG3/dB7oP4YB9vFLmm8u4RH.bow0ilLW4mv2y5qf5rdMsSoYrDq', 'linda.martinez@mail.com', 'Linda', 'Martinez', 'OPERATOR'),
('barbara.lopez', '$2a$12$XG5opmmz88oQhwzP5AwyVuXY2h7uQS6bDHRiNKfU8gfQojcSCMG/i', 'barbara.lopez@mail.com', 'Barbara', 'Lopez', 'OPERATOR'),
('robert.hernandez', '$2a$12$e6oIJ6ETrOP3Rn1dBa54nu/2YkwgTcdE5Nd3f/6lKpt4tYOeOHbqG', 'robert.hernandez@mail.com', 'Robert', 'Hernandez', 'OPERATOR'),
('james.clark', '$2a$12$U9a/mWq1a//V8eUHZiPoKu0U4mn0GBvqdJfo/p7jyCG14tzXHWM1S', 'james.clark@mail.com', 'James', 'Clark', 'OPERATOR'),
('david.allen', '$2a$12$iypYm9ywaJh32YWwW1sLeu32xD0cpI3GaC6hVIMs1EldpV75vSN6a', 'david.allen@mail.com', 'David', 'Allen', 'OPERATOR'),
('susan.young', '$2a$12$vAGW4nLjHRzl0a00Birq8.rQKn2qKCcrX6RX0jbcNFCO0gStFf4v.', 'susan.young@mail.com', 'Susan', 'Young', 'OPERATOR'),
('karen.king', '$2a$12$PjQa3HcPME/fsbBXFAnRI.JdjAiE44vAp5poo04tey2ClTkh5S3MS', 'karen.king@mail.com', 'Karen', 'King', 'OPERATOR'),
('nancy.lee', '$2a$12$Ytagnm8PNItPnHynLtjw5.hr8yG0s4o9TP2o7woeA1WdOYdutPxRa', 'nancy.lee@mail.com', 'Nancy', 'Lee', 'OPERATOR'),
('betty.garcia', '$2a$12$dSthJZ0MimGXuK8JaVBw5e7ZFXlXbPnoEDa23df3hasq/A1WOn/Tu', 'betty.garcia@mail.com', 'Betty', 'Garcia', 'OPERATOR'),
('ronald.lewis', '$2a$12$Jr6Oy.WBOfdlTjqAWed5pOEImdOwYTXhRnHJbVgNeCvMuPcqCmxcK', 'ronald.lewis@mail.com', 'Ronald', 'Lewis', 'OPERATOR'),
('daniel.robinson', '$2a$12$NkrMrH6hRMZfyX4SFjDwEu8JK20kp4um5KLKLj6wzH/zzhrnAmcfy', 'daniel.robinson@mail.com', 'Daniel', 'Robinson', 'OPERATOR'),
('margaret.walker', '$2a$12$NnEbd3YeYpbIxY8YCj5CfOGcdUamjHgn3vn.LIxUpQH9cY8Nsr3.i', 'margaret.walker@mail.com', 'Margaret', 'Walker', 'OPERATOR'),
('jessica.hall', '$2a$12$s95IlKz1BTe008PUwdQdueJQoSnrfoctD84tStk52mfnC9HiWCqya', 'jessica.hall@mail.com', 'Jessica', 'Hall', 'OPERATOR'),
('sandra.allen', '$2a$12$ubvNSQBoV337R4C/jXyVleBnriiPXkLBcuDlIW4uQJXH6gcXvv5yS', 'sandra.allen@mail.com', 'Sandra', 'Allen', 'OPERATOR');

-- Crear la tabla Guest (Clientes que se hospedan)
CREATE TABLE `Guest` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `phone_number` VARCHAR(15),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar registros de ejemplo en Guest
INSERT INTO `Guest` (first_name, last_name, email, phone_number)
VALUES
('Alice', 'Williams', 'alice.williams@mail.com', '123456789'),
('Bob', 'Johnson', 'bob.johnson@mail.com', '987654321');

-- Crear la tabla Room (Habitaciones del hotel)
CREATE TABLE `Room` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `room_number` INT NOT NULL UNIQUE,
    `room_type` VARCHAR(50) NOT NULL,
    `price_per_night` DECIMAL(10,2) NOT NULL,
    `status` VARCHAR(20) DEFAULT 'Available', -- Ejemplo de estados: Available, Occupied, Maintenance
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar registros de ejemplo en Room
INSERT INTO `Room` (room_number, room_type, price_per_night)
VALUES
(101, 'Single', 50.00),
(102, 'Double', 80.00),
(103, 'Suite', 150.00);

-- Crear la tabla Reservation (Reservas de habitaciones)
CREATE TABLE `Reservation` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `guest_id` INT,
    `room_id` INT,
    `check_in_date` DATE NOT NULL,
    `check_out_date` DATE NOT NULL,
    `status` VARCHAR(20) DEFAULT 'Pending', -- Ejemplo de estados: Pending, Confirmed, Checked-out
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `user_id` INT,  -- Relación con el usuario que realizó la reserva
    FOREIGN KEY (`guest_id`) REFERENCES `Guest` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`room_id`) REFERENCES `Room` (`id`) ON DELETE SET NULL,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar registros de ejemplo en Reservation
INSERT INTO `Reservation` (guest_id, room_id, check_in_date, check_out_date, user_id)
VALUES
(1, 3, '2024-12-01', '2024-12-05', 2),  -- Jane Smith (Receptionist) realizó la reserva
(2, 2, '2024-12-10', '2024-12-12', 3);  -- Michael Brown (Housekeeper) realizó la reserva

-- Crear la tabla Employee_Assignment (Relación de asignación de empleados a tareas)
CREATE TABLE `User_Assignment` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT,
    `task` VARCHAR(255) NOT NULL,
    `assigned_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar registros de ejemplo en Employee_Assignment
INSERT INTO `User_Assignment` (user_id, task)
VALUES
(1, 'Managing Reservations'),
(2, 'Checking In Guests'),
(3, 'Cleaning Rooms'),
(4, 'Preparing Meals');
