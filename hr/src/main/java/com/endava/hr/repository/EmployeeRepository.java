package com.endava.hr.repository;

import com.endava.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAll();

    Optional<Employee> findByEmployeeId(Long id);

    Employee save(Employee employee);

    void deleteByEmployeeId(Long id);
}
