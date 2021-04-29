CREATE TABLE jobs
(
    job_id     VARCHAR2(10) PRIMARY KEY,
    job_title  VARCHAR2(35) NOT NULL,
    min_salary NUMBER(6),
    max_salary NUMBER(6),
    CONSTRAINT min_max_salary_ck CHECK ( min_salary < max_salary )
);

COMMENT ON TABLE jobs IS 'jobs table with job titles and salary ranges. Contains 19 rows.
References with employees and job_history table.';
COMMENT ON COLUMN jobs.job_id IS 'Primary key of jobs table.';
COMMENT ON COLUMN jobs.job_title IS 'A not null column that shows job title, e.g. AD_VP, FI_ACCOUNTANT';
COMMENT ON COLUMN jobs.min_salary IS 'Minimum salary for a job title.';
COMMENT ON COLUMN jobs.max_salary IS 'Maximum salary for a job title';
