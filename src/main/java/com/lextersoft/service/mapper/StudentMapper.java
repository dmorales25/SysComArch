package com.lextersoft.service.mapper;

import com.lextersoft.domain.*;
import com.lextersoft.service.dto.StudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Student and its DTO StudentDTO.
 */
@Mapper(componentModel = "spring", uses = {TechnologistMapper.class})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

    @Mapping(source = "technologist.id", target = "technologistId")
    @Mapping(source = "technologist.name", target = "technologistName")
    StudentDTO toDto(Student student);

    @Mapping(source = "technologistId", target = "technologist")
    Student toEntity(StudentDTO studentDTO);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
