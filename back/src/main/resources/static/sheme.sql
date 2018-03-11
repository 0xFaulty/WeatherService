-- CREATE DATABASE socify
-- WITH
-- OWNER = "Valentin3d"
-- TEMPLATE = template0
-- ENCODING = 'UTF8'
-- LC_COLLATE = 'Russian_Russia.1251'
-- LC_CTYPE = 'Russian_Russia.1251'
-- TABLESPACE = pg_default
-- CONNECTION LIMIT = -1;
--
-- GRANT ALL ON DATABASE socify TO "Valentin3d";

DROP TABLE subscriptions, channels, channel_types, active_sources, sources, user_roles, roles, users;

CREATE TABLE users (
  id           SERIAL    NOT NULL PRIMARY KEY,
  username     TEXT      NOT NULL,
  password     TEXT      NOT NULL,
  email        TEXT      NOT NULL,
  active       BOOLEAN   NOT NULL,
  created      TIMESTAMP NOT NULL DEFAULT NOW(),
  last_session TIMESTAMP NOT NULL
);

CREATE TABLE roles (
  id   SERIAL NOT NULL PRIMARY KEY,
  name TEXT   NOT NULL
);

CREATE TABLE user_roles (
  id      SERIAL  NOT NULL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users (id),
  role_id INTEGER NOT NULL REFERENCES roles (id)
);

CREATE TABLE sources (
  id      SERIAL  NOT NULL PRIMARY KEY,
  name    TEXT    NOT NULL,
  address TEXT    NOT NULL,
  active  BOOLEAN NOT NULL
);

CREATE TABLE active_sources (
  id        SERIAL  NOT NULL PRIMARY KEY,
  user_id   INTEGER NOT NULL REFERENCES users (id),
  source_id INTEGER NOT NULL REFERENCES sources (id),
  active    BOOLEAN NOT NULL,
  token     TEXT    NOT NULL
);

CREATE TABLE channel_types (
  id   SERIAL NOT NULL PRIMARY KEY,
  name TEXT   NOT NULL
);

CREATE TABLE channels (
  id         SERIAL  NOT NULL PRIMARY KEY,
  source_id  INTEGER NOT NULL REFERENCES sources (id),
  type_id    INTEGER NOT NULL REFERENCES channel_types (id),
  identifier TEXT    NOT NULL
);

CREATE TABLE subscriptions (
  id         SERIAL  NOT NULL PRIMARY KEY,
  user_id    INTEGER NOT NULL REFERENCES users (id),
  channel_id INTEGER NOT NULL REFERENCES channels (id)
);

INSERT INTO users (username, password, email, active, last_session) VALUES
  ('admin', 'testpw', 'admin@mail.ru', TRUE, current_timestamp),
  ('guest', 'test', 'guest@mail.ru', TRUE, current_timestamp);

INSERT INTO roles (name) VALUES
  ('ROLE_USER'),
  ('ROLE_ADMIN');

INSERT INTO user_roles (user_id, role_id) VALUES
  (1, 1),
  (2, 2);

INSERT INTO sources (name, address, active) VALUES
  ('vk', 'https://vk.com/', TRUE),
  ('facebook', 'https://www.facebook.com/', FALSE),
  ('twitter', 'https://twitter.com/', FALSE),
  ('instagram', 'https://www.instagram.com/', FALSE),
  ('ok', 'https://ok.ru/', FALSE);

INSERT INTO active_sources (user_id, source_id, active, token) VALUES
  (1, 1, TRUE, '76a68e747c9d2c6eed648bc9a6c8b11a48124c3b8071e752ab3e46e27f1404deef5cbf5947581e2f4078b'),
  (2, 1, TRUE, '76a68e747c9d2c6eed648bc9a6c8b11a48124c3b8071e752ab3e46e27f1404deef5cbf5947581e2f4078b');

INSERT INTO channel_types (name) VALUES
  ('GROUP'),
  ('PERSONAL');

INSERT INTO channels (source_id, type_id, identifier) VALUES
  (1, 1, 'pikabu'),
  (1, 1, 'tproger');

INSERT INTO subscriptions (user_id, channel_id) VALUES
  (1, 2),
  (2, 1),
  (2, 2);



