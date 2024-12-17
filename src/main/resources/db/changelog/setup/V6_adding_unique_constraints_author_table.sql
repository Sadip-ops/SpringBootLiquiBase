--liquibase formatted sql

-- changeset Sadip_Khadka:1_add_unique_constraint_to_author_name
-- preconditions:
--    onFail:MARK_RAN
--    onError:HALT
--    - tableExists tableName=AUTHOR
--    - columnExists tableName=AUTHOR columnName=name
--    - sqlCheck expectedResult=0:
--        SELECT COUNT(*)
--        FROM information_schema.statistics
--        WHERE table_schema = DATABASE()
--          AND table_name = 'author'
--          AND index_name = 'uq_author_name'
ALTER TABLE AUTHOR ADD CONSTRAINT uq_author_name UNIQUE (name);
