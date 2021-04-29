package com.endava.internship;

import com.sun.istack.internal.NotNull;

import java.time.LocalDate;
import java.util.Objects;


public class Student implements Comparable<Student> {
    private String name;
    private LocalDate dateOfBirth;
    private String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public int compareTo(@NotNull Student o) {
        if (o == null) {
            throw new NullPointerException();
        }

        if(this.equals(o)) {
            return 0;
        }

        return !name.equals(o.name) ?
                name.compareTo(o.name) :
                dateOfBirth.compareTo(o.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", details='" + details + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) && dateOfBirth.equals(student.dateOfBirth);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 21 * result + (name == null ? 0 : name.hashCode());
        result = 21 * result + (dateOfBirth == null ? 0 : dateOfBirth.hashCode());

        return result;
    }
}
