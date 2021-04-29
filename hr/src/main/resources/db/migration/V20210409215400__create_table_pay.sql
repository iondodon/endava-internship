CREATE TABLE pay (
    cardNr VARCHAR2(20) PRIMARY KEY,
    salary NUMBER(8,2),
    commission_pct NUMBER(2,2),
    employee_id NUMBER(6),
    CONSTRAINT PAY_EMPLOYEE_ID foreign key (EMPLOYEE_ID)
        references EMPLOYEES (EMPLOYEE_ID)
);
