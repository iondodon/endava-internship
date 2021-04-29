CREATE TABLE departments
(
    department_id   NUMBER(4) PRIMARY KEY,
    department_name VARCHAR2(30) NOT NULL,
    manager_id      NUMBER(6),
    location_id     NUMBER(4)    NOT NULL
);

CREATE SEQUENCE departments_seq INCREMENT BY 10 MAXVALUE 9990 NOCACHE;

ALTER TABLE departments MODIFY department_id DEFAULT departments_seq.nextval;

COMMENT ON TABLE departments IS 'Departments table that shows details of departments where employees
work. references with locations, employees, and job_history tables.';
COMMENT ON COLUMN departments.department_id IS 'Primary key column of departments table.';
COMMENT ON COLUMN departments.department_name IS 'A not null column that shows name of a department. Administration,
Marketing, Purchasing, Human Resources, Shipping, IT, Executive, Public
Relations, Sales, Finance, and Accounting. ';
COMMENT ON COLUMN departments.manager_id IS 'Manager_id of a department. Foreign key to employee_id column of employees table. The manager_id column of the employee table references this column.';
COMMENT ON COLUMN departments.location_id IS 'Location id where a department is located. Foreign key to location_id column of locations table.';
