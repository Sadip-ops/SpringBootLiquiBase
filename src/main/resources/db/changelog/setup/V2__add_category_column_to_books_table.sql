--liquibase formatted sql

-- changeset Sadip_Khadka:1
-- preconditions:
--    - tableExists tableName=book
ALTER TABLE BOOK ADD COLUMN genre VARCHAR(255);