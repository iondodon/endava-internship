CREATE TABLE locations
(
    location_id    NUMBER(4) PRIMARY KEY,
    street_address VARCHAR2(40),
    postal_code    VARCHAR2(12),
    city           VARCHAR2(30),
    state_province VARCHAR2(25),
    country_id     CHAR(2) NOT NULL
);

CREATE SEQUENCE locations_seq MINVALUE 1 MAXVALUE 9900 INCREMENT BY 100 START WITH 3300;

ALTER TABLE locations
    MODIFY location_id DEFAULT locations_seq.nextval;

CREATE INDEX loc_country_ix ON locations (country_id);

COMMENT ON TABLE locations IS 'Locations table that contains specific address of a specific office,
warehouse, and/or production site of a company. Does not store addresses /
locations of customers. references with the departments and countries tables. ';
COMMENT ON COLUMN locations.location_id IS 'Primary key of locations table';
COMMENT ON COLUMN locations.street_address IS 'Street address of an office, warehouse, or production site of a company.
Contains building number and street name';
COMMENT ON COLUMN locations.postal_code IS 'Postal code of the location of an office, warehouse, or production site
of a company. ';
COMMENT ON COLUMN locations.city IS 'A not null column that shows city where an office, warehouse, or
production site of a company is located. ';
COMMENT ON COLUMN locations.state_province IS 'State or Province where an office, warehouse, or production site of a
company is located.';
COMMENT ON COLUMN locations.country_id IS 'Country where an office, warehouse, or production site of a company is
located. Foreign key to country_id column of the countries table.';
