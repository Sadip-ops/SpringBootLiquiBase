--liquibase formatted sql

-- changeset Sadip_Khadka:1
-- preconditions:
--    - tableExists tableName=book
ALTER TABLE BOOK ADD COLUMN imageUrl TEXT;

-- changeset Sadip_Khadka:2
-- preconditions:
-- - columnExists tableName=book columnName=imageUrl
ALTER TABLE BOOK RENAME COLUMN imageUrl to image_url;

