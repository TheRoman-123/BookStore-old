ALTER TABLE book
    ADD image_path VARCHAR(100);

ALTER TABLE book
    ADD CONSTRAINT uc_book_imagepath UNIQUE (image_path);

ALTER TABLE book
    DROP COLUMN image;
