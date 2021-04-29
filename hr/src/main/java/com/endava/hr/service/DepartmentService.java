package com.endava.hr.service;

import com.endava.hr.entity.dto.DepartmentDto;

import java.util.Collection;

public interface DepartmentService {

    Collection<DepartmentDto> getAll(Boolean extend);

    DepartmentDto get(Long id, Boolean extend);

    DepartmentDto create(DepartmentDto departmentDto);

    DepartmentDto update(DepartmentDto departmentDto);

    DepartmentDto delete(Long id);
}
