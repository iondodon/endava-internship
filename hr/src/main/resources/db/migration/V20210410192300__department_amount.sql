ALTER TABLE LOCATIONS
ADD department_amount NUMBER(3) DEFAULT 0 NOT NULL;

COMMENT ON COLUMN LOCATIONS.department_amount IS 'Contains the amount of departments in the location';


CREATE OR REPLACE TRIGGER update_department_amount_trg
    AFTER INSERT OR DELETE ON DEPARTMENTS FOR EACH ROW
BEGIN
    IF INSERTING THEN
        UPDATE LOCATIONS
        SET LOCATIONS.department_amount = LOCATIONS.department_amount + 1
        WHERE LOCATION_ID = :NEW.location_id;
    ELSIF DELETING THEN
        UPDATE LOCATIONS
        SET LOCATIONS.department_amount = LOCATIONS.department_amount - 1
        WHERE LOCATION_ID = :OLD.location_id;
    END IF;
END;
