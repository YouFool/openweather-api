CREATE SEQUENCE city_seq;

CREATE TABLE city (
  id BIGINT default city_seq.nextval PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  country_code VARCHAR(2) NOT NULL
);
