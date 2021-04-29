--------------------------------------------------------
--  Ref Constraints for Table COUNTRIES
--------------------------------------------------------

ALTER TABLE COUNTRIES
    ADD CONSTRAINT COUNTR_REG_FK FOREIGN KEY (REGION_ID)
        REFERENCES REGIONS (REGION_ID);
--------------------------------------------------------
--  Ref Constraints for Table DEPARTMENTS
--------------------------------------------------------

ALTER TABLE DEPARTMENTS
    ADD CONSTRAINT DEPT_LOC_FK FOREIGN KEY (LOCATION_ID)
        REFERENCES LOCATIONS (LOCATION_ID);
ALTER TABLE DEPARTMENTS
    ADD CONSTRAINT DEPT_MGR_FK FOREIGN KEY (MANAGER_ID)
        REFERENCES EMPLOYEES (EMPLOYEE_ID);
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYEES
--------------------------------------------------------

ALTER TABLE EMPLOYEES
    ADD CONSTRAINT EMP_DEPT_FK FOREIGN KEY (DEPARTMENT_ID)
        REFERENCES DEPARTMENTS (DEPARTMENT_ID);
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT EMP_JOB_FK FOREIGN KEY (JOB_ID)
        REFERENCES JOBS (JOB_ID);
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT EMP_MANAGER_FK FOREIGN KEY (MANAGER_ID)
        REFERENCES EMPLOYEES (EMPLOYEE_ID);
--------------------------------------------------------
--  Ref Constraints for Table JOB_HISTORY
--------------------------------------------------------

ALTER TABLE JOB_HISTORY
    ADD CONSTRAINT JHIST_JOB_FK FOREIGN KEY (JOB_ID)
        REFERENCES JOBS (JOB_ID);
ALTER TABLE JOB_HISTORY
    ADD CONSTRAINT JHIST_EMP_FK FOREIGN KEY (EMPLOYEE_ID)
        REFERENCES EMPLOYEES (EMPLOYEE_ID);
ALTER TABLE JOB_HISTORY
    ADD CONSTRAINT JHIST_DEPT_FK FOREIGN KEY (DEPARTMENT_ID)
        REFERENCES DEPARTMENTS (DEPARTMENT_ID);
--------------------------------------------------------
--  Ref Constraints for Table LOCATIONS
--------------------------------------------------------

ALTER TABLE LOCATIONS
    ADD CONSTRAINT LOC_C_ID_FK FOREIGN KEY (COUNTRY_ID)
        REFERENCES COUNTRIES (COUNTRY_ID);
