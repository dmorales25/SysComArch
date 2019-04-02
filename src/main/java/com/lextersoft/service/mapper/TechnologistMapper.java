package com.lextersoft.service.mapper;

import com.lextersoft.domain.*;
import com.lextersoft.service.dto.TechnologistDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Technologist and its DTO TechnologistDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TechnologistMapper extends EntityMapper<TechnologistDTO, Technologist> {



    default Technologist fromId(Long id) {
        if (id == null) {
            return null;
        }
        Technologist technologist = new Technologist();
        technologist.setId(id);
        return technologist;
    }
}
