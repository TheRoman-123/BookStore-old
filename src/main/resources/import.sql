-- INSERT INTO author (author_id, first_name, last_name)
-- VALUES (1, 'Ayn', 'Rand'),
--        (2, 'Daniel', 'Kiz'),
--        (3, 'Mihail', 'Labkovski');
--
-- INSERT INTO genre (genre_id, genre_name)
-- VALUES (1, 'novel'),
--        (2, 'psychological realism'),
--        (3, 'philosophical novel'),
--        (4, 'tale');
--
-- INSERT INTO literature (literature_id, title, author_id)
-- VALUES (1, 'Fountainhead', 1),
--        (2, 'Flowers for Algernon', 2),
--        (3, 'Хочу и буду', 3);
--
-- INSERT INTO literature_genre (literature_id, genre_id)
-- VALUES (1, 1),
--        (1, 2),
--        (1, 3),
--        (2, 1),
--        (2, 4);
--
-- INSERT INTO publisher (publisher_id, publisher_name)
-- VALUES (1, 'PocketBook'),
--        (2, 'AmazonShop'),
--        (3, 'Librarius'),
--        (4, 'HelloBook'),
--        (5, 'WhateverYouWant');
--
-- INSERT INTO book (book_id, title, amount, price, publisher_id, image_path)
-- VALUES (1, 'Fountainhead', 200, 22.05, 5, 'https://m.media-amazon.com/images/I/510VwbbHZkL._AC_SY780_.jpg'),
--        (2, 'Flowers for Algernon', 1500, 33.21, 2, 'https://pictures.abebooks.com/isbn/9780156030083-uk.jpg');
--
-- INSERT INTO literature_book (literature_id, book_id)
-- VALUES (1, 1),
--        (2, 2);
--
-- INSERT INTO customer (customer_id, email, first_name, last_name, phone_number)
-- VALUES (1, 'roma.tcheloweck@gmail.com', 'Roman', 'Rudi', '060147036');
--
--
--
-- -- SELECT * FROM customer;
--
--
