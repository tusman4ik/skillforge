CREATE SEQUENCE IF NOT EXISTS authoity_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS task_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE authoity
(
    id   INTEGER NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_authoity PRIMARY KEY (id)
);

CREATE TABLE authoity_users
(
    authoity_id INTEGER NOT NULL,
    users_id    INTEGER NOT NULL,
    CONSTRAINT pk_authoity_users PRIMARY KEY (authoity_id, users_id)
);

CREATE TABLE task
(
    id             INTEGER NOT NULL,
    taskType           VARCHAR(225),
    created_date   TIMESTAMP WITHOUT TIME ZONE,
    solve          VARCHAR(255),
    answer         VARCHAR(255),
    analytics_list VARCHAR(255),
    CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE TABLE "user"
(
    id       INTEGER NOT NULL,
    password VARCHAR(255),
    username VARCHAR(255),
    email    VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE authoity_users
    ADD CONSTRAINT fk_autuse_on_authoity FOREIGN KEY (authoity_id) REFERENCES authoity (id);

ALTER TABLE authoity_users
    ADD CONSTRAINT fk_autuse_on_user FOREIGN KEY (users_id) REFERENCES "user" (id);