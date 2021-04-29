CREATE TABLE projects (
    project_id NUMBER(4) PRIMARY KEY,
    project_description VARCHAR2(200) NOT NULL,
    project_investment NUMBER(6,-3),
    project_revenue NUMBER(6),
    CONSTRAINT projects_proj_desc_min_len
        CHECK (length(project_description) > 10),
    CONSTRAINT projects_proj_investm_min_len
        CHECK (project_investment > 1)
);


CREATE TABLE projects_employees (
    project_id NUMBER(4) NOT NULL,
    employee_id NUMBER(6) NOT NULL,
    worked_hours NUMBER(3),
    CONSTRAINT projects_employees_projects_fk foreign key (project_id)
        references projects (project_id),
    CONSTRAINT  projects_employees_employees_fk foreign key (employee_id)
        references employees (EMPLOYEE_ID),
    CONSTRAINT proj_emp_pk primary key (project_id, employee_id)
);