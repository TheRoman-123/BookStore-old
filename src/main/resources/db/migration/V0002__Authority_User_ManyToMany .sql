CREATE TABLE IF NOT EXISTS authority
(
    authority_id SMALLINT NOT NULL,
    authority    VARCHAR(255),
    CONSTRAINT pk_authority PRIMARY KEY (authority_id)
);

CREATE TABLE IF NOT EXISTS user_authority
(
    authority_id SMALLINT NOT NULL,
    user_id      BIGINT   NOT NULL,
    CONSTRAINT pk_user_authority PRIMARY KEY (authority_id, user_id)
);

ALTER TABLE users
    ADD email VARCHAR(254);

ALTER TABLE users
    ADD enabled BOOLEAN DEFAULT FALSE;

ALTER TABLE users
    ADD password VARCHAR(60);

ALTER TABLE users
    ALTER COLUMN email SET NOT NULL;

ALTER TABLE users
    ALTER COLUMN enabled SET NOT NULL;

ALTER TABLE users
    ALTER COLUMN password SET NOT NULL;

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE user_authority
    ADD CONSTRAINT fk_usaut_on_authority FOREIGN KEY (authority_id) REFERENCES authority (authority_id);

ALTER TABLE user_authority
    ADD CONSTRAINT fk_usaut_on_user FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE customer
    DROP COLUMN email;

INSERT INTO authority (authority_id, authority)
VALUES (1, 'USER'),
       (2, 'CONTENT_MANAGER'),
       (3, 'ORDER_PROCESSOR'),
       (4, 'ADMIN');