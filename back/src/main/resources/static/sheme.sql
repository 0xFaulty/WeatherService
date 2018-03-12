-- CREATE DATABASE weather
-- WITH
-- OWNER = "postgres"
-- TEMPLATE = template0
-- ENCODING = 'UTF8'
-- LC_COLLATE = 'Russian_Russia.1251'
-- LC_CTYPE = 'Russian_Russia.1251'
-- TABLESPACE = pg_default
-- CONNECTION LIMIT = -1;
--
-- GRANT ALL ON DATABASE weather TO "postgres";

DROP TABLE requests_by_city, users;

CREATE TABLE users (
  id       SERIAL    NOT NULL PRIMARY KEY,
  username TEXT      NOT NULL,
  password TEXT      NOT NULL,
  active   BOOLEAN   NOT NULL DEFAULT TRUE,
  created  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE requests_by_city (
  id          SERIAL    NOT NULL PRIMARY KEY,
  user_id     INTEGER   NOT NULL REFERENCES users (id),
  finished    BOOLEAN   NOT NULL DEFAULT FALSE,
  city        TEXT      NOT NULL,
  date        TIMESTAMP NOT NULL DEFAULT NOW(),
  temp        REAL,
  pressure    REAL,
  description TEXT,
  lon         REAL,
  lat         REAL
);

INSERT INTO users (username, password, active) VALUES
  ('admin', 'admin', TRUE),
  ('guest', 'guest', TRUE);



