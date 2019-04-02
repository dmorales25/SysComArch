package com.lextersoft.service.impl;

import com.lextersoft.service.TechnologistService;
import com.lextersoft.domain.Technologist;
import com.lextersoft.repository.TechnologistRepository;
import com.lextersoft.service.dto.TechnologistDTO;
import com.lextersoft.service.mapper.TechnologistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Technologist.
 */
@Service
@Transactional
public class TechnologistServiceImpl implements TechnologistService {

    private final Logger log = LoggerFactory.getLogger(TechnologistServiceImpl.class);

    private final TechnologistRepository technologistRepository;

    private final TechnologistMapper technologistMapper;

    public TechnologistServiceImpl(TechnologistRepository technologistRepository, TechnologistMapper technologistMapper) {
        this.technologistRepository = technologistRepository;
        this.technologistMapper = technologistMapper;
    }

    /**
     * Save a technologist.
     *
     * @param technologistDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TechnologistDTO save(TechnologistDTO technologistDTO) {
        log.debug("Request to save Technologist : {}", technologistDTO);

        Technologist technologist = technologistMapper.toEntity(technologistDTO);
        technologist = technologistRepository.save(technologist);
        return technologistMapper.toDto(technologist);
    }

    /**
     * Get all the technologists.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TechnologistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Technologists");
        return technologistRepository.findAll(pageable)
            .map(technologistMapper::toDto);
    }


    /**
     * Get one technologist by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TechnologistDTO> findOne(Long id) {
        log.debug("Request to get Technologist : {}", id);
        return technologistRepository.findById(id)
            .map(technologistMapper::toDto);
    }

    /**
     * Delete the technologist by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Technologist : {}", id);
        technologistRepository.deleteById(id);
    }
}
