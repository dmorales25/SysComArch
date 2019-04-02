package com.lextersoft.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Student entity.
 */
public class StudentDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String enrollment;

    @NotNull
    private Integer age;

    @NotNull
    private String email;

    private Long technologistId;

    private String technologistName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTechnologistId() {
        return technologistId;
    }

    public void setTechnologistId(Long technologistId) {
        this.technologistId = technologistId;
    }

    public String getTechnologistName() {
        return technologistName;
    }

    public void setTechnologistName(String technologistName) {
        this.technologistName = technologistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentDTO studentDTO = (StudentDTO) o;
        if (studentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", enrollment='" + getEnrollment() + "'" +
            ", age=" + getAge() +
            ", email='" + getEmail() + "'" +
            ", technologist=" + getTechnologistId() +
            ", technologist='" + getTechnologistName() + "'" +
            "}";
    }
}
