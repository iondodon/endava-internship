package com.endava.hr.repository;

import com.endava.hr.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAll();

    Optional<Department> findByDepartmentId(Long id);

    Department save(Department department);

    void deleteByDepartmentId(Long id);
}
