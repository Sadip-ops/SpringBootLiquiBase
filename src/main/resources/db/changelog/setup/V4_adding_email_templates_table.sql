--liquibase formatted sql

-- Changeset Sadip_Khadka:1
CREATE TABLE email_templates (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 template_name VARCHAR(255) NOT NULL,
                                 template_content TEXT NOT NULL,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--changeset Sadip_Khadka:2
INSERT INTO email_templates (template_name, template_content)
VALUES ('welcomeEmail', 'Hello ${name}, welcome to our service!');
