--liquibase formatted sql

-- changeset Sadip_Khadka:add_author_table
CREATE TABLE IF NOT EXISTS AUTHOR (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(255) NOT NULL
    );

-- preconditions: onFail:CONTINUE
--    onError:HALT
--    - and :
--    - tableExists tableName=author
--    - tableExists tableName=book
-- changeset Sadip_Khadka:add_author_id_to_book
ALTER TABLE BOOK ADD COLUMN author_id BIGINT;


-- preconditions: onFail:CONTINUE
--    onError:HALT
--    - and :
--    - tableExists tableName=author
--    - tableExists tableName=book
-- changeset Sadip_Khadka:add_foreign_key_to_book
ALTER TABLE BOOK
    ADD CONSTRAINT fk_author
        FOREIGN KEY (author_id) REFERENCES AUTHOR(id)
            ON DELETE CASCADE;