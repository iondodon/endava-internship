package com.endava.hr.controller;

import com.endava.hr.entity.dto.EmployeeDto;
import com.endava.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    ResponseEntity<Collection<EmployeeDto>> getAll(@RequestParam Boolean extend) {
        Collection<EmployeeDto> employeeDtos = employeeService.getAll(extend);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<EmployeeDto> get(@PathVariable Long id, @RequestParam Boolean extend) {
        EmployeeDto employeeDto = employeeService.get(id, extend);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployeeDto = employeeService.create(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployeeDto = employeeService.update(employeeDto);
        return new ResponseEntity<>(updatedEmployeeDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<EmployeeDto> deleteById(@PathVariable Long id) {
        EmployeeDto deletedEmployeeDto = employeeService.delete(id);
        return new ResponseEntity<>(deletedEmployeeDto, HttpStatus.OK);
    }
}
