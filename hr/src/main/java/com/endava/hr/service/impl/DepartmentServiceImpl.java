package com.endava.hr.service.impl;

import com.endava.hr.entity.Department;
import com.endava.hr.entity.dto.DepartmentDto;
import com.endava.hr.exception.BadClientInputException;
import com.endava.hr.exception.ResourceNotFoundException;
import com.endava.hr.repository.DepartmentRepository;
import com.endava.hr.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<DepartmentDto> getAll(Boolean extend) {
        Collection<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(this::getDepartmentDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentDto get(Long id, Boolean extend) {
        if(isNull(id)) {
            throw new BadClientInputException("ID should not be null.");
        }

        Department department = departmentRepository.findByDepartmentId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with such ID not found."));

        return getDepartmentDto(department);
    }

    @Override
    @Transactional
    public DepartmentDto create(DepartmentDto departmentDto) {
        Department department = getDepartmentFrom(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return getDepartmentDto(savedDepartment);
    }


    @Override
    @Transactional
    public DepartmentDto update(DepartmentDto departmentDto) {
        Department department = getDepartmentFrom(departmentDto);

        departmentRepository.findByDepartmentId(departmentDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department with such ID not found."));

        Department updatedDepartment = departmentRepository.save(department);

        return getDepartmentDto(updatedDepartment);
    }

    @Override
    @Transactional
    public DepartmentDto delete(Long id) {
        Department department = departmentRepository.findByDepartmentId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department with such ID not found."));

        departmentRepository.deleteById(id);

        return getDepartmentDto(department);
    }



    private DepartmentDto getDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .managerId(nonNull(department.getManager()) ? department.getManager().getEmployeeId() : null)
                .locationId(department.getLocationId())
                .build();
    }

    private Department getDepartmentFrom(DepartmentDto departmentDto) {
        return Department.builder()
                .departmentId(departmentDto.getDepartmentId())
                .departmentName(departmentDto.getDepartmentName())
                .manager(null)
                .locationId(departmentDto.getLocationId())
                .build();
    }
}
