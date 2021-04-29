CREATE TABLE countries
(
    country_id   CHAR(2),
    country_name VARCHAR(40),
    region_id    NUMBER NOT NULL,
    CONSTRAINT country_c_id_pk PRIMARY KEY (country_id)
);

COMMENT ON TABLE countries IS 'Country table. References with locations table.';
COMMENT ON COLUMN countries.country_id IS 'Primary key of countries table.';
COMMENT ON COLUMN countries.country_name IS 'Country name';
COMMENT ON COLUMN countries.region_id IS 'Region ID for the country. Foreign key to region_id column in the Regions table.';
