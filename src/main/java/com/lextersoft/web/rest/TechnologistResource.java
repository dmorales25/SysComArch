package com.lextersoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lextersoft.service.TechnologistService;
import com.lextersoft.web.rest.errors.BadRequestAlertException;
import com.lextersoft.web.rest.util.HeaderUtil;
import com.lextersoft.web.rest.util.PaginationUtil;
import com.lextersoft.service.dto.TechnologistDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Technologist.
 */
@RestController
@RequestMapping("/api")
public class TechnologistResource {

    private final Logger log = LoggerFactory.getLogger(TechnologistResource.class);

    private static final String ENTITY_NAME = "technologist";

    private final TechnologistService technologistService;

    public TechnologistResource(TechnologistService technologistService) {
        this.technologistService = technologistService;
    }

    /**
     * POST  /technologists : Create a new technologist.
     *
     * @param technologistDTO the technologistDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new technologistDTO, or with status 400 (Bad Request) if the technologist has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/technologists")
    @Timed
    public ResponseEntity<TechnologistDTO> createTechnologist(@Valid @RequestBody TechnologistDTO technologistDTO) throws URISyntaxException {
        log.debug("REST request to save Technologist : {}", technologistDTO);
        if (technologistDTO.getId() != null) {
            throw new BadRequestAlertException("A new technologist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TechnologistDTO result = technologistService.save(technologistDTO);
        return ResponseEntity.created(new URI("/api/technologists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /technologists : Updates an existing technologist.
     *
     * @param technologistDTO the technologistDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated technologistDTO,
     * or with status 400 (Bad Request) if the technologistDTO is not valid,
     * or with status 500 (Internal Server Error) if the technologistDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/technologists")
    @Timed
    public ResponseEntity<TechnologistDTO> updateTechnologist(@Valid @RequestBody TechnologistDTO technologistDTO) throws URISyntaxException {
        log.debug("REST request to update Technologist : {}", technologistDTO);
        if (technologistDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TechnologistDTO result = technologistService.save(technologistDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, technologistDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /technologists : get all the technologists.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of technologists in body
     */
    @GetMapping("/technologists")
    @Timed
    public ResponseEntity<List<TechnologistDTO>> getAllTechnologists(Pageable pageable) {
        log.debug("REST request to get a page of Technologists");
        Page<TechnologistDTO> page = technologistService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/technologists");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /technologists/:id : get the "id" technologist.
     *
     * @param id the id of the technologistDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the technologistDTO, or with status 404 (Not Found)
     */
    @GetMapping("/technologists/{id}")
    @Timed
    public ResponseEntity<TechnologistDTO> getTechnologist(@PathVariable Long id) {
        log.debug("REST request to get Technologist : {}", id);
        Optional<TechnologistDTO> technologistDTO = technologistService.findOne(id);
        return ResponseUtil.wrapOrNotFound(technologistDTO);
    }

    /**
     * DELETE  /technologists/:id : delete the "id" technologist.
     *
     * @param id the id of the technologistDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/technologists/{id}")
    @Timed
    public ResponseEntity<Void> deleteTechnologist(@PathVariable Long id) {
        log.debug("REST request to delete Technologist : {}", id);
        technologistService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
