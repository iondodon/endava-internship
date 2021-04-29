package com.endava.internship.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collections;
import java.util.List;

public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private List<Privilege> privileges;

    public User(final Long id,
                final String firstName,
                final String lastName,
                final Integer age,
                final List<Privilege> privileges) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.privileges = Collections.unmodifiableList(privileges);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User that = (User) obj;
            return new EqualsBuilder()
                    .append(this.id, that.getId())
                    .append(this.age, that.getAge())
                    .append(this.firstName, that.getFirstName())
                    .append(this.lastName, that.getLastName())
                    .append(this.privileges, that.getPrivileges())
                    .build();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.age)
                .append(this.firstName)
                .append(this.lastName)
                .append(this.privileges)
                .build();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", privileges=" + privileges +
                '}';
    }
}
