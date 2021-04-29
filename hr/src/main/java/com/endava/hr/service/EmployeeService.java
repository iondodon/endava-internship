package com.endava.hr.service;

import com.endava.hr.entity.dto.EmployeeDto;

import java.util.Collection;

public interface EmployeeService {
    Collection<EmployeeDto> getAll(Boolean extend);

    EmployeeDto get(Long id, Boolean extend);

    EmployeeDto create(EmployeeDto employeeDto);

    EmployeeDto update(EmployeeDto employeeDto);

    EmployeeDto delete(Long id);
}
