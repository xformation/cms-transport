package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.Stopage;
import com.synectiks.transport.repository.StopageRepository;
import com.synectiks.transport.service.StopageService;
import com.synectiks.transport.service.dto.StopageDTO;
import com.synectiks.transport.service.mapper.StopageMapper;
import com.synectiks.transport.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.synectiks.transport.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StopageResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class StopageResourceIT {

    private static final String DEFAULT_STOPAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STOPAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_BRANCH_ID = 1L;
    private static final Long UPDATED_BRANCH_ID = 2L;

    @Autowired
    private StopageRepository stopageRepository;

    @Autowired
    private StopageMapper stopageMapper;

    @Autowired
    private StopageService stopageService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restStopageMockMvc;

    private Stopage stopage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StopageResource stopageResource = new StopageResource(stopageService);
        this.restStopageMockMvc = MockMvcBuilders.standaloneSetup(stopageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stopage createEntity(EntityManager em) {
        Stopage stopage = new Stopage()
            .stopageName(DEFAULT_STOPAGE_NAME)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON)
            .branchId(DEFAULT_BRANCH_ID);
        return stopage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stopage createUpdatedEntity(EntityManager em) {
        Stopage stopage = new Stopage()
            .stopageName(UPDATED_STOPAGE_NAME)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .branchId(UPDATED_BRANCH_ID);
        return stopage;
    }

    @BeforeEach
    public void initTest() {
        stopage = createEntity(em);
    }

    @Test
    @Transactional
    public void createStopage() throws Exception {
        int databaseSizeBeforeCreate = stopageRepository.findAll().size();

        // Create the Stopage
        StopageDTO stopageDTO = stopageMapper.toDto(stopage);
        restStopageMockMvc.perform(post("/api/stopages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stopageDTO)))
            .andExpect(status().isCreated());

        // Validate the Stopage in the database
        List<Stopage> stopageList = stopageRepository.findAll();
        assertThat(stopageList).hasSize(databaseSizeBeforeCreate + 1);
        Stopage testStopage = stopageList.get(stopageList.size() - 1);
        assertThat(testStopage.getStopageName()).isEqualTo(DEFAULT_STOPAGE_NAME);
        assertThat(testStopage.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStopage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testStopage.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testStopage.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testStopage.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testStopage.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
    }

    @Test
    @Transactional
    public void createStopageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stopageRepository.findAll().size();

        // Create the Stopage with an existing ID
        stopage.setId(1L);
        StopageDTO stopageDTO = stopageMapper.toDto(stopage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopageMockMvc.perform(post("/api/stopages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stopageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Stopage in the database
        List<Stopage> stopageList = stopageRepository.findAll();
        assertThat(stopageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStopages() throws Exception {
        // Initialize the database
        stopageRepository.saveAndFlush(stopage);

        // Get all the stopageList
        restStopageMockMvc.perform(get("/api/stopages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopage.getId().intValue())))
            .andExpect(jsonPath("$.[*].stopageName").value(hasItem(DEFAULT_STOPAGE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getStopage() throws Exception {
        // Initialize the database
        stopageRepository.saveAndFlush(stopage);

        // Get the stopage
        restStopageMockMvc.perform(get("/api/stopages/{id}", stopage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stopage.getId().intValue()))
            .andExpect(jsonPath("$.stopageName").value(DEFAULT_STOPAGE_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingStopage() throws Exception {
        // Get the stopage
        restStopageMockMvc.perform(get("/api/stopages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStopage() throws Exception {
        // Initialize the database
        stopageRepository.saveAndFlush(stopage);

        int databaseSizeBeforeUpdate = stopageRepository.findAll().size();

        // Update the stopage
        Stopage updatedStopage = stopageRepository.findById(stopage.getId()).get();
        // Disconnect from session so that the updates on updatedStopage are not directly saved in db
        em.detach(updatedStopage);
        updatedStopage
            .stopageName(UPDATED_STOPAGE_NAME)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .branchId(UPDATED_BRANCH_ID);
        StopageDTO stopageDTO = stopageMapper.toDto(updatedStopage);

        restStopageMockMvc.perform(put("/api/stopages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stopageDTO)))
            .andExpect(status().isOk());

        // Validate the Stopage in the database
        List<Stopage> stopageList = stopageRepository.findAll();
        assertThat(stopageList).hasSize(databaseSizeBeforeUpdate);
        Stopage testStopage = stopageList.get(stopageList.size() - 1);
        assertThat(testStopage.getStopageName()).isEqualTo(UPDATED_STOPAGE_NAME);
        assertThat(testStopage.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStopage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testStopage.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testStopage.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testStopage.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testStopage.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingStopage() throws Exception {
        int databaseSizeBeforeUpdate = stopageRepository.findAll().size();

        // Create the Stopage
        StopageDTO stopageDTO = stopageMapper.toDto(stopage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStopageMockMvc.perform(put("/api/stopages")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(stopageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Stopage in the database
        List<Stopage> stopageList = stopageRepository.findAll();
        assertThat(stopageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStopage() throws Exception {
        // Initialize the database
        stopageRepository.saveAndFlush(stopage);

        int databaseSizeBeforeDelete = stopageRepository.findAll().size();

        // Delete the stopage
        restStopageMockMvc.perform(delete("/api/stopages/{id}", stopage.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Stopage> stopageList = stopageRepository.findAll();
        assertThat(stopageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
