insert into REGIONS (REGION_ID, REGION_NAME)
    values (1, 'EST Europe');

insert into COUNTRIES (COUNTRY_ID, COUNTRY_NAME, REGION_ID)
    values (1, 'Moldova', 1);

insert into LOCATIONS (LOCATION_ID, STREET_ADDRESS, POSTAL_CODE, CITY, STATE_PROVINCE, COUNTRY_ID)
    values (1, 'Alba-Iulia, 80', 'MD-2071', 'Chisinau', 'Moldova', 1);


insert into DEPARTMENTS (DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID)
    values (1, 'First Dep', null, 1);

insert into JOBS (JOB_ID, JOB_TITLE, MIN_SALARY, MAX_SALARY)
    values (1, 'Developer', 1000, 3000);


insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
values (1, 'Ion', 'Dodon', 'iondodon@gmail.com', '068686686', to_date('2021/04/01', 'yyyy/mm/dd'), 1, 123, null, null, 1);

insert into EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
values (2, 'Vasile', 'Boaghi', 'vasileboaghi@gmail.com', '074758657', to_date('2021/03/09', 'yyyy/mm/dd'), 1, 123, null, 1, 1);


insert into JOB_HISTORY
values (1, to_date('2021/04/01', 'yyyy/mm/dd'), current_date, 1, 1);
