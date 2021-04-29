
CREATE OR REPLACE TRIGGER employment_action_trg
    AFTER INSERT OR DELETE ON EMPLOYEES FOR EACH ROW
BEGIN
    IF INSERTING THEN
        log_employment_action(:NEW.first_name, :NEW.last_name, 'HIRED');
    ELSIF DELETING THEN
        log_employment_action(:OLD.first_name, :OLD.last_name, 'FIRED');
    END IF;
END;