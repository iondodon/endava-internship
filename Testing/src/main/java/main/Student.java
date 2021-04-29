package main;

import java.time.LocalDate;

public class Student {
    private String name;
    private LocalDate dateOfBirth;
    private String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name; this.dateOfBirth = dateOfBirth; this.details = details;
    }

    public String getName() { return name; }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDetails() {
        return details;
    }
}
