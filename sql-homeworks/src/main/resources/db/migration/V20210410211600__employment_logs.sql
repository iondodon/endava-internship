CREATE TABLE employment_logs (
    employment_log_id NUMBER(20) PRIMARY KEY,
    first_name VARCHAR2(20),
    last_name VARCHAR2(25) NOT NULL,
    employment_action VARCHAR2(5) NOT NULL,
    employment_status_updtd_tmstmp timestamp NOT NULL,
    CONSTRAINT action_constraint
        CHECK (employment_action = 'HIRED' OR employment_action = 'FIRED')
);

CREATE SEQUENCE employment_logs_seq NOCACHE;

ALTER TABLE employment_logs
    MODIFY employment_log_id DEFAULT employment_logs_seq.nextval;


COMMENT ON COLUMN employment_logs.employment_log_id IS 'Log ID';
COMMENT ON COLUMN employment_logs.first_name IS 'Employee first name';
COMMENT ON COLUMN employment_logs.last_name IS 'Employee last name';
COMMENT ON  COLUMN employment_logs.employment_action IS 'Employee fired or hired';
COMMENT ON COLUMN employment_logs.employment_status_updtd_tmstmp IS 'Action timestamp';