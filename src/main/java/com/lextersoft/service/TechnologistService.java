package com.lextersoft.service;

import com.lextersoft.service.dto.TechnologistDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Technologist.
 */
public interface TechnologistService {

    /**
     * Save a technologist.
     *
     * @param technologistDTO the entity to save
     * @return the persisted entity
     */
    TechnologistDTO save(TechnologistDTO technologistDTO);

    /**
     * Get all the technologists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TechnologistDTO> findAll(Pageable pageable);


    /**
     * Get the "id" technologist.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TechnologistDTO> findOne(Long id);

    /**
     * Delete the "id" technologist.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
