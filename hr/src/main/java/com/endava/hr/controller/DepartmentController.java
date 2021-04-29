package com.endava.hr.controller;

import com.endava.hr.entity.dto.DepartmentDto;
import com.endava.hr.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    ResponseEntity<Collection<DepartmentDto>> getAll(@RequestParam Boolean extend) {
        Collection<DepartmentDto> departmentDtos = departmentService.getAll(extend);
        return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
    }

    @GetMapping("{id}")
    ResponseEntity<DepartmentDto> get(@PathVariable Long id, @RequestParam Boolean extend) {
        DepartmentDto departmentDto = departmentService.get(id, extend);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<DepartmentDto> create(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartmentsDtos = departmentService.create(departmentDto);
        return new ResponseEntity<>(savedDepartmentsDtos, HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<DepartmentDto> update(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartmentDto = departmentService.update(departmentDto);
        return new ResponseEntity<>(updatedDepartmentDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    ResponseEntity<DepartmentDto> deleteById(@PathVariable Long id) {
        DepartmentDto deletedDepartmentDto = departmentService.delete(id);
        return new ResponseEntity<>(deletedDepartmentDto, HttpStatus.OK);
    }
}
