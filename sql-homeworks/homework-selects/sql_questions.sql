-- Write a query to display: 
-- 1. the first name, last name, salary, and job grade for all employees.
SELECT first_name, last_name, salary, job_title
FROM employees
         LEFT JOIN jobs USING (job_id);

-- 2. the first and last name, department, city, and state province for each employee.
select FIRST_NAME, LAST_NAME, DEPARTMENT_ID, DEPARTMENT_NAME, CITY
from EMPLOYEES
    join DEPARTMENTS using (DEPARTMENT_ID)
    join LOCATIONS using (LOCATION_ID);


-- 3. the first name, last name, department number and department name, for all employees for departments 80 or 40.
select FIRST_NAME, LAST_NAME, DEPARTMENT_ID, DEPARTMENT_NAME
from EMPLOYEES
    join DEPARTMENTS using(DEPARTMENT_ID)
    where DEPARTMENT_ID = 80 or DEPARTMENT_ID = 40;


-- 4. those employees who contain a letter z to their first name and also display their last name, department, city, and state province.
select FIRST_NAME, LAST_NAME, DEPARTMENT_NAME, CITY, STATE_PROVINCE
from EMPLOYEES
join DEPARTMENTS using (DEPARTMENT_ID)
join LOCATIONS using (LOCATION_ID)
where FIRST_NAME like '%Z%' or FIRST_NAME like '%z%';


-- 5. the first and last name and salary for those employees who earn less than the employee earn whose number is 182.
select FIRST_NAME, LAST_NAME, SALARY
from EMPLOYEES
where SALARY < (select SALARY from EMPLOYEES where EMPLOYEE_ID = 182);


-- 6. the first name of all employees including the first name of their manager.
select e.FIRST_NAME as EMPLOYEE, m.FIRST_NAME as MANAGER
from EMPLOYEES e
    join EMPLOYEES m on e.MANAGER_ID = m.EMPLOYEE_ID
where e.MANAGER_ID = m.EMPLOYEE_ID;


-- 7. the first name of all employees and the first name of their manager including those who does not working under any manager.
select e.FIRST_NAME as EMPLYEE, m.FIRST_NAME as MANAGER
from EMPLOYEES e
    join EMPLOYEES m on e.MANAGER_ID = m.EMPLOYEE_ID
union all
select FIRST_NAME, null
from EMPLOYEES
where MANAGER_ID is null;

-- or
select  e.FIRST_NAME as EMPLYEE, m.FIRST_NAME as MANAGER
from EMPLOYEES e
    left join EMPLOYEES m on e.MANAGER_ID = m.EMPLOYEE_ID;


-- 8. the details of employees who manage a department.
select e.*
from DEPARTMENTS d
    join EMPLOYEES e on d.MANAGER_ID = e.EMPLOYEE_ID;


-- 9. the first name, last name, and department number for those employees who works in the same department as the employee who holds the last name as Taylor.
select e.FIRST_NAME, e.LAST_NAME, e.DEPARTMENT_ID
from EMPLOYEES e
where e.DEPARTMENT_ID in (
    select e1.DEPARTMENT_ID
    from EMPLOYEES e1
    where e1.LAST_NAME = 'Taylor'
);


--10. the department name and number of employees in each of the department.
select DEPARTMENT_NAME, count(EMPLOYEE_ID) as EMPLOYEES
from EMPLOYEES
    left join DEPARTMENTS using(DEPARTMENT_ID)
group by DEPARTMENT_NAME;


--11. the name of the department, average salary and number of employees working in that department who got commission.
select
       d.DEPARTMENT_NAME,
       avg(e.SALARY) as AVERAGE_SALARY,
       (
            select COUNT(*)
            from EMPLOYEES e1
            where e1.DEPARTMENT_ID = d.DEPARTMENT_ID and e1.COMMISSION_PCT is not null
        ) as GOT_COMMISION
from DEPARTMENTS d
    left join EMPLOYEES e on d.DEPARTMENT_ID = e.DEPARTMENT_ID
group by d.DEPARTMENT_NAME, d.DEPARTMENT_ID;

-- or
select
       d.DEPARTMENT_NAME,
       AVG(e.SALARY) as AVERAGE_SALARY,
       sum(case when COMMISSION_PCT is not null then 1 else 0 end) as GOT_COMMISION
from DEPARTMENTS d
    left join EMPLOYEES e on d.DEPARTMENT_ID = e.DEPARTMENT_ID
group by d.DEPARTMENT_NAME;


--12. job title and average salary of employees.
select
       j.JOB_TITLE,
       avg(e.SALARY) as AVERAGE_SALARY
from JOBS j
    left join EMPLOYEES e using(JOB_ID)
group by j.JOB_TITLE;


--13. the country name, city, and number of those departments where at least 2 employees are working.
select c.COUNTRY_NAME, l.CITY, d.DEPARTMENT_ID
from DEPARTMENTS d
    join EMPLOYEES e on e.DEPARTMENT_ID = d.DEPARTMENT_ID
    join LOCATIONS l on d.LOCATION_ID = l.LOCATION_ID
    join COUNTRIES c using (COUNTRY_ID)
group by c.COUNTRY_NAME, l.CITY, d.DEPARTMENT_ID
having count(e.DEPARTMENT_ID) >= 2;


--14. the employee ID, job name, number of days worked in for all those jobs in department 80.
-- only previous jobs
select jh.EMPLOYEE_ID, j.JOB_TITLE, (jh.END_DATE - jh.START_DATE) DAYS_WORKED
from JOB_HISTORY jh
    join JOBS j using (JOB_ID)
where jh.DEPARTMENT_ID = 80;



--15. the name ( first name and last name ) for those employees who gets more salary than the employee whose ID is 163.
select e.FIRST_NAME, e.LAST_NAME
from EMPLOYEES e
where e.SALARY > (select e1.SALARY from EMPLOYEES e1 where e1.EMPLOYEE_ID = 163);



--16. the employee id, employee name (first name and last name ) for all employees who earn more than the average salary.
select EMPLOYEE_ID, (FIRST_NAME || ' ' || LAST_NAME) as NAME
from EMPLOYEES
where SALARY > (select avg(s.SALARY) from EMPLOYEES s);


--17. the employee name ( first name and last name ), employee id and salary of all employees who report to Payam.
select (s0.FIRST_NAME || ' ' || s0.LAST_NAME) as NAME, s0.SALARY
from EMPLOYEES s0
    join EMPLOYEES s1 on s0.MANAGER_ID = s1.EMPLOYEE_ID
where s1.FIRST_NAME = 'Payam';

--18. the department number, name ( first name and last name ), job and department name for all employees in the Finance department.
select (e.FIRST_NAME || ' ' || e.LAST_NAME) as NAME, d.DEPARTMENT_NAME, j.JOB_TITLE
from EMPLOYEES e
    join JOBS j using (JOB_ID)
    join DEPARTMENTS d using (DEPARTMENT_ID)
where d.DEPARTMENT_NAME = 'Finance';


--19. all the information of an employee whose id is any of the number 134, 159 and 183.
select e.*
from EMPLOYEES e
where EMPLOYEE_ID in (134, 159, 183);


--20. all the information of the employees whose salary is within the range of smallest salary and 2500.
select e0.*
from EMPLOYEES e0
where e0.SALARY between (select min(e1.SALARY) from EMPLOYEES e1) and 2500;


--21. all the information of the employees who does not work in those departments where some employees works whose id within the range 100 and 200.
select e0.*
from EMPLOYEES e0
where e0.DEPARTMENT_ID not in (
    select e1.DEPARTMENT_ID
    from EMPLOYEES e1
    where e1.EMPLOYEE_ID between 100 and 200
) or e0.DEPARTMENT_ID is null;


--22. all the information for those employees whose id is any id who earn the second highest salary.
select e0.*
from EMPLOYEES e0
where e0.SALARY = (
    select max(e1.SALARY)
    from EMPLOYEES e1
    where e1.SALARY <> (select max(e2.SALARY) from EMPLOYEES e2)
);


-- or
select e0.*
from (
    select e1.*, rank() over (order by e1.SALARY desc) as row_rank
    from EMPLOYEES e1
) e0
where row_rank = 2;


--23. the employee name( first name and last name ) and hiredate for all employees in the same department as Clara. Exclude Clara.
select (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME, e0.HIRE_DATE
from EMPLOYEES e0
where e0.FIRST_NAME <> 'Clara' and e0.DEPARTMENT_ID = (
    select e1.DEPARTMENT_ID
    from EMPLOYEES e1
    where e1.FIRST_NAME = 'Clara'
);

--24. the employee number and name( first name and last name ) for all employees who work in a department with any employee whose name contains a T.
select e0.EMPLOYEE_ID, (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME
from EMPLOYEES e0
where e0.DEPARTMENT_ID in (
    select distinct e1.DEPARTMENT_ID
    from EMPLOYEES e1
    where upper(e1.FIRST_NAME) like '%T%' or upper(e1.LAST_NAME) like '%T%'
);


--25. full name(first and last name), job title, starting and ending date of last jobs for those employees with worked without a commission percentage.
select (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME, j.JOB_TITLE, jh0.START_DATE, jh0.END_DATE
from EMPLOYEES e0
         join JOB_HISTORY jh0 on e0.EMPLOYEE_ID = jh0.EMPLOYEE_ID
         join JOBS j on jh0.JOB_ID = j.JOB_ID
where e0.COMMISSION_PCT is null;


-- only last job
select (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME, j.JOB_TITLE, jh0.START_DATE, jh0.END_DATE
from EMPLOYEES e0
    join JOB_HISTORY jh0 on e0.EMPLOYEE_ID = jh0.EMPLOYEE_ID
    join JOBS j on jh0.JOB_ID = j.JOB_ID
where e0.COMMISSION_PCT is null and jh0.END_DATE = (
    select max(jh1.END_DATE)
    from JOB_HISTORY jh1
    where jh0.EMPLOYEE_ID = jh1.EMPLOYEE_ID
);


--26. the employee number, name( first name and last name ), and salary for all employees who earn more than the average salary and who work in a department with any employee with a J in their name.
select (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME, e0.SALARY
from EMPLOYEES e0
where e0.SALARY > (
    select avg(e1.SALARY)
    from EMPLOYEES e1
) and e0.DEPARTMENT_ID in (
    select distinct e2.DEPARTMENT_ID
    from EMPLOYEES e2
    where upper(e2.FIRST_NAME) like '%J%' or upper(e2.LAST_NAME) like '%J%'
);


--27. the employee number, name( first name and last name ) and job title for all employees whose salary is smaller than any salary of those employees whose job title is MK_MAN.
select e0.EMPLOYEE_ID, (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME, j0.JOB_TITLE
from EMPLOYEES e0
    join JOBS j0 on j0.JOB_ID = e0.JOB_ID
where e0.SALARY < any (
    select e1.SALARY
    from EMPLOYEES e1
    join JOBS j on j.JOB_ID = e1.JOB_ID
    where j.JOB_ID = 'MK_MAN'
);


--28. the employee number, name( first name and last name ) and job title for all employees whose salary is smaller than any salary of those employees whose job title is MK_MAN. Exclude Job title MK_MAN.
select e0.EMPLOYEE_ID, (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME, j0.JOB_TITLE
from EMPLOYEES e0
         join JOBS j0 on j0.JOB_ID = e0.JOB_ID
where j0.JOB_TITLE <> 'MK_MAN' and e0.SALARY < any (
    select e1.SALARY
    from EMPLOYEES e1
             join JOBS j on j.JOB_ID = e1.JOB_ID
    where j.JOB_ID = 'MK_MAN'
);



--29. all the information of those employees who did not have any job in the past.
select distinct e.*
from EMPLOYEES e
where not exists(
    select jh.JOB_ID
    from JOB_HISTORY jh
    where jh.EMPLOYEE_ID = e.EMPLOYEE_ID
);


--30. the employee number, name( first name and last name ) and job title for all employees whose salary is more than any average salary of any department.

select e0.EMPLOYEE_ID, (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME
from EMPLOYEES e0
where e0.SALARY > any (
    select avg(e1.SALARY)
    from DEPARTMENTS d0
        join EMPLOYEES e1 on d0.DEPARTMENT_ID = e1.DEPARTMENT_ID
    group by d0.DEPARTMENT_ID
);


--31. the employee id, name ( first name and last name ) and the job id column with a modified title SALESMAN for those employees whose job title is ST_MAN and DEVELOPER for whose job title is IT_PROG.
select
    EMPLOYEE_ID,
    (FIRST_NAME || ' ' || EMPLOYEES.LAST_NAME) as NAME,
    (case when JOB_ID = 'ST_MAN' then 'SALESMAN' else
        (case when JOB_ID = 'IT_PROG' then 'DEVELOPER' else JOB_ID end) end) as JOB_ID
from EMPLOYEES
join JOBS using (JOB_ID);


-- or
select
    EMPLOYEE_ID,
    (FIRST_NAME || ' ' || EMPLOYEES.LAST_NAME) as NAME,
    (case
        when JOB_ID = 'ST_MAN' then 'SALESMAN'
        when JOB_ID = 'IT_PROG' then 'DEVELOPER'
        else JOB_ID
    end) as JOB_ID
from EMPLOYEES
         join JOBS using (JOB_ID);


--32. the employee id, name ( first name and last name ), salary and the SalaryStatus column with a title HIGH and LOW respectively
-- for those employees whose salary is more than and less than the average salary of all employees.
select
    e.EMPLOYEE_ID,
    (e.FIRST_NAME || ' ' || e.LAST_NAME) as NAME,
    (case
        when e.SALARY > (select avg(e1.SALARY) from EMPLOYEES e1) then 'HIGH'
        else 'LOW'
    end) as SalaryStatus
from EMPLOYEES e;


--33. the employee id, name ( first name and last name ), SalaryDrawn, AvgCompare (salary - the average salary of all employees)
    -- and the SalaryStatus column with a title HIGH and LOW respectively for those employees whose salary is more than and less than
    -- the average salary of all employees.
select
    e.EMPLOYEE_ID,
    (e.FIRST_NAME || ' ' || e.LAST_NAME) as NAME,
    e.SALARY as SalaryDrawn,
    (e.SALARY - (select avg(e0.SALARY) from EMPLOYEES e0)) as AvgCompare,
    (case
         when e.SALARY > (select avg(e1.SALARY) from EMPLOYEES e1) then 'HIGH'
         else 'LOW'
        end) as SalaryStatus
from EMPLOYEES e;


--34. all the employees who earn more than the average and who work in any of the IT departments.
select e.*
from EMPLOYEES e
    join DEPARTMENTS d on e.DEPARTMENT_ID = d.DEPARTMENT_ID
where d.DEPARTMENT_NAME like '%IT%'
      and e.SALARY > (select avg(e1.SALARY) from EMPLOYEES e1);


--35. who earns more than Mr. Ozer.
select *
from EMPLOYEES
where SALARY > (select e0.SALARY from EMPLOYEES e0 where e0.LAST_NAME = 'Ozer');


--36. which employees have a manager who works for a department based in the US.
select e0.*
from EMPLOYEES e0
    join EMPLOYEES e1 on e0.MANAGER_ID = e1.EMPLOYEE_ID
    join DEPARTMENTS d on e1.DEPARTMENT_ID = d.DEPARTMENT_ID
    join LOCATIONS l on d.LOCATION_ID = l.LOCATION_ID
where l.COUNTRY_ID = 'US';


--37. the names of all employees whose salary is greater than 50% of their department’s total salary bill.
select (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME
from EMPLOYEES e0
where e0.SALARY > (
    select sum(e1.SALARY)
    from EMPLOYEES e1
    where e1.DEPARTMENT_ID = e0.DEPARTMENT_ID
) * 0.5;


--38. the employee id, name ( first name and last name ), salary, department name and city for all
--the employees who gets the salary as the salary earn by the employee which is maximum within the joining date January 1st, 2002 and December 31st, 2003.
select
       e0.EMPLOYEE_ID,
       (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as NAME,
       e0.SALARY,
       d.DEPARTMENT_NAME,
       l.CITY
from EMPLOYEES e0
    join DEPARTMENTS d on e0.DEPARTMENT_ID = d.DEPARTMENT_ID
    join LOCATIONS l on d.LOCATION_ID = l.LOCATION_ID
where e0.SALARY = (
    select max(e1.SALARY)
    from EMPLOYEES e1
    where e1.HIRE_DATE between to_date('2002/01/01', 'yyyy/mm/dd') and to_date('2003/12/31', 'yyyy/mm/dd')
);



--39. the first and last name, salary, and department ID for all those employees who earn more than the average salary and arrange the list in descending order on salary.
select e0.FIRST_NAME, e0.LAST_NAME, e0.SALARY, e0.DEPARTMENT_ID
from EMPLOYEES e0
where e0.SALARY > (select avg(e1.SALARY) from EMPLOYEES e1)
order by e0.SALARY desc;


--40. the first and last name, salary, and department ID for those employees who earn more than the maximum salary of a department which ID is 40.
select e0.FIRST_NAME, e0.LAST_NAME, e0.SALARY, e0.DEPARTMENT_ID
from EMPLOYEES e0
where e0.SALARY > (
    select max(e1.SALARY)
    from EMPLOYEES e1
    where e1.DEPARTMENT_ID = 40
);


--41. the department name and Id for all departments where they located, that Id is equal to the Id for the location where department number 30 is located.
select d0.DEPARTMENT_NAME, d0.DEPARTMENT_ID
from DEPARTMENTS d0
where d0.LOCATION_ID = (
    select d1.LOCATION_ID
    from DEPARTMENTS d1
    where d1.DEPARTMENT_ID = 30
);


--42. the first and last name, salary, and department ID for all those employees who work in that department where the employee works who hold the ID 201.
select e0.FIRST_NAME, e0.LAST_NAME, e0.SALARY, e0.DEPARTMENT_ID
from EMPLOYEES e0
where e0.DEPARTMENT_ID = (
    select e1.DEPARTMENT_ID
    from EMPLOYEES e1
    where e1.EMPLOYEE_ID = 201
);

--43. the first and last name, salary, and department ID for those employees whose salary is equal to the salary of the employee who works in that department which ID is 40.
select e0.FIRST_NAME, e0.LAST_NAME, e0.SALARY, e0.DEPARTMENT_ID
from EMPLOYEES e0
where e0.SALARY = (
    select e1.SALARY
    from EMPLOYEES e1
    where e1.DEPARTMENT_ID = 40
);


--44. the first and last name, salary, and department ID for those employees who earn more than the minimum salary of a department which ID is 40.
select e0.FIRST_NAME, e0.LAST_NAME, e0.DEPARTMENT_ID
from EMPLOYEES e0
where e0.SALARY > (
    select min(e1.SALARY)
    from EMPLOYEES e1
    where e1.DEPARTMENT_ID = 40
);


--45. the first and last name, salary, and department ID for those employees who earn less than the minimum salary of a department which ID is 70.
select e0.FIRST_NAME, e0.LAST_NAME, e0.DEPARTMENT_ID
from EMPLOYEES e0
where e0.SALARY < (
    select min(e1.SALARY)
    from EMPLOYEES e1
    where e1.DEPARTMENT_ID = 70
);


--46. the first and last name, salary, and department ID for those employees who earn less than the average salary, and also work at the department
-- where the employee Laura is working as a first name holder.
select e0.FIRST_NAME, e0.LAST_NAME, e0.SALARY, e0.DEPARTMENT_ID
from EMPLOYEES e0
where e0.DEPARTMENT_ID in (select e1.DEPARTMENT_ID from EMPLOYEES e1 where e1.FIRST_NAME = 'Laura')
  and e0.SALARY < (select avg(e2.SALARY) from EMPLOYEES e2);


--47. the full name (first and last name) of manager who is supervising 4 or more employees.
select (e.FIRST_NAME || ' ' || e.LAST_NAME) as FULL_NAME
from EMPLOYEES e
    join (
        select m.MANAGER_ID, count(m.MANAGER_ID)
        from EMPLOYEES m
        group by m.MANAGER_ID
        having count(m.MANAGER_ID) >= 4
) emp on e.EMPLOYEE_ID = emp.MANAGER_ID;


--48. the details of the current job for those employees who worked as a Sales Representative in the past.
select j.*
from EMPLOYEES e
    join (
        select distinct jh.EMPLOYEE_ID
        from JOB_HISTORY jh
        join JOBS j on jh.JOB_ID = j.JOB_ID
        where j.JOB_TITLE = 'Sales Representative'
    ) emp_past on e.EMPLOYEE_ID = emp_past.EMPLOYEE_ID
    join JOBS j on e.JOB_ID = j.JOB_ID;


--49. all the information about those employees who earn second lowest salary of all the employees.
select e0.*
from EMPLOYEES e0 join (
    select e1.EMPLOYEE_ID, rank() over (order by e1.SALARY) as raw_rank
    from EMPLOYEES e1
) ranks on e0.EMPLOYEE_ID = ranks.EMPLOYEE_ID
where raw_rank = 2;


--50. the department ID, full name (first and last name), salary for those employees who is highest salary drawn in a department.
select e0.DEPARTMENT_ID, (e0.FIRST_NAME || ' ' || e0.LAST_NAME) as FULL_NAME, e0.SALARY
from EMPLOYEES e0
where e0.SALARY = (
    select max(e1.SALARY)
    from EMPLOYEES e1
    where e0.DEPARTMENT_ID = e1.DEPARTMENT_ID
);
