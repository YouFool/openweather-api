CREATE SEQUENCE city_seq;

CREATE TABLE city
(
    id              BIGINT DEFAULT city_seq.nextval PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    country_code    VARCHAR(2)   NOT NULL,
    open_weather_id VARCHAR(255) NOT NULL
);
