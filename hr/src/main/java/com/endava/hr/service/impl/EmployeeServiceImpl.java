package com.endava.hr.service.impl;

import com.endava.hr.entity.Employee;
import com.endava.hr.entity.dto.EmployeeDto;
import com.endava.hr.event.Event;
import com.endava.hr.exception.BadClientInputException;
import com.endava.hr.exception.DatabaseException;
import com.endava.hr.exception.ResourceNotFoundException;
import com.endava.hr.repository.EmployeeRepository;
import com.endava.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ApplicationEventPublisher eventPublisher;


    @Override
    @Transactional(readOnly = true)
    public Collection<EmployeeDto> getAll(Boolean extend) {
        Collection<Employee> employees = employeeRepository.findAll();

        Event event = new Event();
        eventPublisher.publishEvent(event);

        return employees.stream()
                .map(this::getEmployeeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public EmployeeDto get(Long id, Boolean extend) {
        if(isNull(id)) {
            throw new BadClientInputException("ID should not be null");
        }

        EmployeeDto employeeDto;
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with such ID not found."));

        return getEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = getEmployeeFrom(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return getEmployeeDto(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = getEmployeeFrom(employeeDto);

        employeeRepository.findByEmployeeId(employeeDto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee with such ID not found."));
        Employee updatedEmployee = employeeRepository.save(employee);

        return getEmployeeDto(updatedEmployee);
    }

    @Override
    @Transactional
    public EmployeeDto delete(Long id) {
        Employee employee = employeeRepository.findByEmployeeId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with such ID not found."));

        employeeRepository.deleteByEmployeeId(id);

        return getEmployeeDto(employee);
    }



    private EmployeeDto getEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .jobId(employee.getJobId())
                .salary(employee.getSalary())
                .commissionPct(employee.getCommissionPct())
                .managerId(nonNull(employee.getManager()) ? employee.getManager().getEmployeeId() : null)
                .departmentId(employee.getDepartmentId())
                .build();
    }

    private Employee getEmployeeFrom(EmployeeDto employeeDto) {
        return Employee.builder()
                .employeeId(employeeDto.getEmployeeId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .hireDate(employeeDto.getHireDate())
                .jobId(employeeDto.getJobId())
                .salary(employeeDto.getSalary())
                .commissionPct(employeeDto.getCommissionPct())
                .manager(null)
                .departmentId(employeeDto.getDepartmentId())
                .build();
    }
}
