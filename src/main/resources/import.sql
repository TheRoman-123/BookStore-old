INSERT INTO author (author_id, first_name, last_name)
VALUES (1, 'Ayn', 'Rand'),
       (2, 'Daniel', 'Kiz');

INSERT INTO book (book_id, title)
VALUES (1, 'Fountainhead'),
       (2, 'Flowers for Algernon');

INSERT INTO book_author (book_id, author_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO publisher (publisher_id, publisher_name)
VALUES (1, 'PocketBook'),
       (2, 'AmazonShop'),
       (3, 'Librarius'),
       (4, 'HelloBook'),
       (5, 'WhateverYouWant');

INSERT INTO warehouse (warehouse_id, amount, price, book_id, publisher_id, image_path)
VALUES (1, 200, 22.05, 1, 5, 'https://m.media-amazon.com/images/I/510VwbbHZkL._AC_SY780_.jpg'),
       (2, 1500, 33.21, 2, 2, 'https://pictures.abebooks.com/isbn/9780156030083-uk.jpg');

INSERT INTO customer (customer_id, email, first_name, last_name, phone_number)
VALUES (1, 'roma.tcheloweck@gmail.com', 'Roman', 'Rudi', '060147036');



-- SELECT * FROM customer;


