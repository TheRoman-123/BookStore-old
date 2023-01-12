ALTER TABLE book
    ADD image BYTEA;

ALTER TABLE book
    DROP COLUMN image_path;