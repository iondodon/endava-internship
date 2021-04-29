package com.endava.hr.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "hr", name = "employees")
@SequenceGenerator(name = "employees_seq", initialValue = 100, schema = "hr", sequenceName = "employees_seq", allocationSize = 1)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq")
    @Column(name = "employee_id")
    private Long employeeId;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;


    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Length(min = 9, max = 9, message = "Phone number should contains 9 digits.")
    @Pattern(regexp = "^0\\d{8}$", message = "Phone number should start with 0.")
    private String phoneNumber;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "job_id")
    private String jobId;

    @Min(value = 1, message = "Min value for salary is 1.0.")
    @Column(name = "salary")
    private Float salary;

    @Column(name = "commission_pct")
    private Float commissionPct;

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    @JsonManagedReference
    private Employee manager;

    private Long departmentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId)
                && firstName.equals(employee.firstName)
                && lastName.equals(employee.lastName)
                && email.equals(employee.email)
                && phoneNumber.equals(employee.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, email, phoneNumber);
    }
}
