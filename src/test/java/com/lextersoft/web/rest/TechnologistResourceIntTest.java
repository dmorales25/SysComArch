package com.lextersoft.web.rest;

import com.lextersoft.SysComArchApp;

import com.lextersoft.domain.Technologist;
import com.lextersoft.repository.TechnologistRepository;
import com.lextersoft.service.TechnologistService;
import com.lextersoft.service.dto.TechnologistDTO;
import com.lextersoft.service.mapper.TechnologistMapper;
import com.lextersoft.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.lextersoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TechnologistResource REST controller.
 *
 * @see TechnologistResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysComArchApp.class)
public class TechnologistResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private TechnologistRepository technologistRepository;

    @Autowired
    private TechnologistMapper technologistMapper;
    
    @Autowired
    private TechnologistService technologistService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTechnologistMockMvc;

    private Technologist technologist;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TechnologistResource technologistResource = new TechnologistResource(technologistService);
        this.restTechnologistMockMvc = MockMvcBuilders.standaloneSetup(technologistResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Technologist createEntity(EntityManager em) {
        Technologist technologist = new Technologist()
            .name(DEFAULT_NAME);
        return technologist;
    }

    @Before
    public void initTest() {
        technologist = createEntity(em);
    }

    @Test
    @Transactional
    public void createTechnologist() throws Exception {
        int databaseSizeBeforeCreate = technologistRepository.findAll().size();

        // Create the Technologist
        TechnologistDTO technologistDTO = technologistMapper.toDto(technologist);
        restTechnologistMockMvc.perform(post("/api/technologists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(technologistDTO)))
            .andExpect(status().isCreated());

        // Validate the Technologist in the database
        List<Technologist> technologistList = technologistRepository.findAll();
        assertThat(technologistList).hasSize(databaseSizeBeforeCreate + 1);
        Technologist testTechnologist = technologistList.get(technologistList.size() - 1);
        assertThat(testTechnologist.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createTechnologistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = technologistRepository.findAll().size();

        // Create the Technologist with an existing ID
        technologist.setId(1L);
        TechnologistDTO technologistDTO = technologistMapper.toDto(technologist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTechnologistMockMvc.perform(post("/api/technologists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(technologistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Technologist in the database
        List<Technologist> technologistList = technologistRepository.findAll();
        assertThat(technologistList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = technologistRepository.findAll().size();
        // set the field null
        technologist.setName(null);

        // Create the Technologist, which fails.
        TechnologistDTO technologistDTO = technologistMapper.toDto(technologist);

        restTechnologistMockMvc.perform(post("/api/technologists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(technologistDTO)))
            .andExpect(status().isBadRequest());

        List<Technologist> technologistList = technologistRepository.findAll();
        assertThat(technologistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTechnologists() throws Exception {
        // Initialize the database
        technologistRepository.saveAndFlush(technologist);

        // Get all the technologistList
        restTechnologistMockMvc.perform(get("/api/technologists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(technologist.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getTechnologist() throws Exception {
        // Initialize the database
        technologistRepository.saveAndFlush(technologist);

        // Get the technologist
        restTechnologistMockMvc.perform(get("/api/technologists/{id}", technologist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(technologist.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTechnologist() throws Exception {
        // Get the technologist
        restTechnologistMockMvc.perform(get("/api/technologists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTechnologist() throws Exception {
        // Initialize the database
        technologistRepository.saveAndFlush(technologist);

        int databaseSizeBeforeUpdate = technologistRepository.findAll().size();

        // Update the technologist
        Technologist updatedTechnologist = technologistRepository.findById(technologist.getId()).get();
        // Disconnect from session so that the updates on updatedTechnologist are not directly saved in db
        em.detach(updatedTechnologist);
        updatedTechnologist
            .name(UPDATED_NAME);
        TechnologistDTO technologistDTO = technologistMapper.toDto(updatedTechnologist);

        restTechnologistMockMvc.perform(put("/api/technologists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(technologistDTO)))
            .andExpect(status().isOk());

        // Validate the Technologist in the database
        List<Technologist> technologistList = technologistRepository.findAll();
        assertThat(technologistList).hasSize(databaseSizeBeforeUpdate);
        Technologist testTechnologist = technologistList.get(technologistList.size() - 1);
        assertThat(testTechnologist.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingTechnologist() throws Exception {
        int databaseSizeBeforeUpdate = technologistRepository.findAll().size();

        // Create the Technologist
        TechnologistDTO technologistDTO = technologistMapper.toDto(technologist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnologistMockMvc.perform(put("/api/technologists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(technologistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Technologist in the database
        List<Technologist> technologistList = technologistRepository.findAll();
        assertThat(technologistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTechnologist() throws Exception {
        // Initialize the database
        technologistRepository.saveAndFlush(technologist);

        int databaseSizeBeforeDelete = technologistRepository.findAll().size();

        // Get the technologist
        restTechnologistMockMvc.perform(delete("/api/technologists/{id}", technologist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Technologist> technologistList = technologistRepository.findAll();
        assertThat(technologistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Technologist.class);
        Technologist technologist1 = new Technologist();
        technologist1.setId(1L);
        Technologist technologist2 = new Technologist();
        technologist2.setId(technologist1.getId());
        assertThat(technologist1).isEqualTo(technologist2);
        technologist2.setId(2L);
        assertThat(technologist1).isNotEqualTo(technologist2);
        technologist1.setId(null);
        assertThat(technologist1).isNotEqualTo(technologist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TechnologistDTO.class);
        TechnologistDTO technologistDTO1 = new TechnologistDTO();
        technologistDTO1.setId(1L);
        TechnologistDTO technologistDTO2 = new TechnologistDTO();
        assertThat(technologistDTO1).isNotEqualTo(technologistDTO2);
        technologistDTO2.setId(technologistDTO1.getId());
        assertThat(technologistDTO1).isEqualTo(technologistDTO2);
        technologistDTO2.setId(2L);
        assertThat(technologistDTO1).isNotEqualTo(technologistDTO2);
        technologistDTO1.setId(null);
        assertThat(technologistDTO1).isNotEqualTo(technologistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(technologistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(technologistMapper.fromId(null)).isNull();
    }
}
