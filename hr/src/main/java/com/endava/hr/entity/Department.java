package com.endava.hr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hr", name = "departments")
@SequenceGenerator(name = "departments_seq", initialValue = 100, schema = "hr", sequenceName = "departments_seq", allocationSize = 1)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_seq")
    private Long departmentId;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    private Employee manager;

    @NotNull
    @Column(name = "location_id", nullable = false)
    private Long locationId;
}
