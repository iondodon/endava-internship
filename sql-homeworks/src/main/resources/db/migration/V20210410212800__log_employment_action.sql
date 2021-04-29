create or replace PROCEDURE log_employment_action(
    emp_first_name VARCHAR,
    emp_last_name VARCHAR,
    action VARCHAR
) AS
BEGIN
    INSERT INTO EMPLOYMENT_LOGS(FIRST_NAME, LAST_NAME, EMPLOYMENT_ACTION, EMPLOYMENT_STATUS_UPDTD_TMSTMP)
    VALUES(emp_first_name, emp_last_name, action, CURRENT_TIMESTAMP);
END;
