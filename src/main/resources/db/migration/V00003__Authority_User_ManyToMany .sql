CREATE TABLE customer
(
    customer_id  BIGINT      NOT NULL,
    first_name   VARCHAR(20) NOT NULL,
    last_name    VARCHAR(20),
    birth_date   date,
    phone_number VARCHAR(9)  NOT NULL,
    user_id      BIGINT,
    CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

CREATE TABLE user_authority
(
    authority_id SMALLINT NOT NULL,
    user_id      BIGINT   NOT NULL,
    CONSTRAINT pk_user_authority PRIMARY KEY (authority_id, user_id)
);

CREATE TABLE users
(
    user_id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    email    VARCHAR(254)                            NOT NULL,
    password VARCHAR(50)                             NOT NULL,
    enabled  BOOLEAN                                 NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE customer
    ADD CONSTRAINT uc_customer_phone_number UNIQUE (phone_number);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE sale
    ADD CONSTRAINT FK_SALE_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customer (customer_id);

ALTER TABLE user_authority
    ADD CONSTRAINT fk_usaut_on_authority FOREIGN KEY (authority_id) REFERENCES authority (authority_id);

ALTER TABLE user_authority
    ADD CONSTRAINT fk_usaut_on_user FOREIGN KEY (user_id) REFERENCES users (user_id);

INSERT INTO authority (authority_id, authority)
VALUES (1, 'USER'),
       (2, 'CONTENT_MANAGER'),
       (3, 'ORDER_PROCESSOR'),
       (4, 'ADMIN');