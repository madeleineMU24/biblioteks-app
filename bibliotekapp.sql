CREATE DATABASE bibliotekapp;
USE bibliotekapp;

CREATE TABLE book(id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR (100),
author VARCHAR (100), 
available BOOLEAN);

CREATE TABLE loans(id INT AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(100),
book_id INT,
loan_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
return_date DATE,
FOREIGN KEY (book_id) REFERENCES book(id));


INSERT INTO book (title, author, available) VALUES ('Dark Matter', 'Blake Crouch', 1),
('The Philosophers Stone', 'J.K Rowling', 1),
('Chambers of Secret', 'J.K Rowling', 1),
('Prisoner of Azkaban', 'J.K Rowling', 1),
('A Little Life', 'Hanya Yanagihara', 0),
('Seven Sisters', 'Lucinda Riley', 0),
('Goblet of Fire', 'J.K Rowling', 1),
('Storm Sister', 'Lucinda Riley', 0);

