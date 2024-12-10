--liquibase formatted sql

-- changeset Sadip_Khadka:1
CREATE TABLE IF NOT EXISTS BOOK(
    id bigInt PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255)
    )