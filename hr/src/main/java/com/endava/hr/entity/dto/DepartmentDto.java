package com.endava.hr.entity.dto;

import com.endava.hr.entity.Employee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDto {
    private Long departmentId;

    private String departmentName;

    private Long managerId;

    private Long locationId;
}
