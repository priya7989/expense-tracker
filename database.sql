CREATE DATABASE expense_tracker;
USE expense_tracker;

CREATE TABLE users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50),
                    password VARCHAR(50)
);

CREATE TABLE expenses (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    user_id INT,
                    amount DOUBLE,
                    category VARCHAR(50),
                    description VARCHAR(100),
                    date DATE,
                    FOREIGN KEY (user_id) REFERENCES users(id)
);