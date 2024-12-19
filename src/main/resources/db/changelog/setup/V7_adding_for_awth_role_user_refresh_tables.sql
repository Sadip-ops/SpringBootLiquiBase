--liquibase formatted sql

-- changeset Sadip_Khadka:1_create_user_info_table
CREATE TABLE user_info (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(255) NOT NULL,
                           password VARCHAR(255),
                           UNIQUE (username)
);

-- changeset Sadip_Khadka:2_create_user_role_table
CREATE TABLE user_role (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

-- changeset Sadip_Khadka:3_create_user_info_role_join_table
CREATE TABLE user_info_role (
                                user_id BIGINT NOT NULL,
                                role_id BIGINT NOT NULL,
                                PRIMARY KEY (user_id, role_id),
                                CONSTRAINT fk_user_info_role_user FOREIGN KEY (user_id) REFERENCES user_info (id) ON DELETE CASCADE,
                                CONSTRAINT fk_user_info_role_role FOREIGN KEY (role_id) REFERENCES user_role (id) ON DELETE CASCADE
);

-- changeset Sadip_Khadka:4_create_refresh_token_table
CREATE TABLE refresh_token (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               token VARCHAR(255) NOT NULL,
                               expiry_date TIMESTAMP NOT NULL,
                               user_id BIGINT UNIQUE,
                               CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES user_info (id) ON DELETE CASCADE
);
