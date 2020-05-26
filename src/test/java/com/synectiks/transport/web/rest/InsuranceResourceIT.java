package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.Insurance;
import com.synectiks.transport.repository.InsuranceRepository;
import com.synectiks.transport.service.InsuranceService;
import com.synectiks.transport.service.dto.InsuranceDTO;
import com.synectiks.transport.service.mapper.InsuranceMapper;
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
 * Integration tests for the {@link InsuranceResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class InsuranceResourceIT {

    private static final String DEFAULT_INSURANCE_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_INSURANCE_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_OF_INSURANCE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_INSURANCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_INSURANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_INSURANCE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VALID_TILL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VALID_TILL = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuranceService insuranceService;

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

    private MockMvc restInsuranceMockMvc;

    private Insurance insurance;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InsuranceResource insuranceResource = new InsuranceResource(insuranceService);
        this.restInsuranceMockMvc = MockMvcBuilders.standaloneSetup(insuranceResource)
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
    public static Insurance createEntity(EntityManager em) {
        Insurance insurance = new Insurance()
            .insuranceCompany(DEFAULT_INSURANCE_COMPANY)
            .typeOfInsurance(DEFAULT_TYPE_OF_INSURANCE)
            .dateOfInsurance(DEFAULT_DATE_OF_INSURANCE)
            .validTill(DEFAULT_VALID_TILL);
        return insurance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Insurance createUpdatedEntity(EntityManager em) {
        Insurance insurance = new Insurance()
            .insuranceCompany(UPDATED_INSURANCE_COMPANY)
            .typeOfInsurance(UPDATED_TYPE_OF_INSURANCE)
            .dateOfInsurance(UPDATED_DATE_OF_INSURANCE)
            .validTill(UPDATED_VALID_TILL);
        return insurance;
    }

    @BeforeEach
    public void initTest() {
        insurance = createEntity(em);
    }

    @Test
    @Transactional
    public void createInsurance() throws Exception {
        int databaseSizeBeforeCreate = insuranceRepository.findAll().size();

        // Create the Insurance
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);
        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isCreated());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeCreate + 1);
        Insurance testInsurance = insuranceList.get(insuranceList.size() - 1);
        assertThat(testInsurance.getInsuranceCompany()).isEqualTo(DEFAULT_INSURANCE_COMPANY);
        assertThat(testInsurance.getTypeOfInsurance()).isEqualTo(DEFAULT_TYPE_OF_INSURANCE);
        assertThat(testInsurance.getDateOfInsurance()).isEqualTo(DEFAULT_DATE_OF_INSURANCE);
        assertThat(testInsurance.getValidTill()).isEqualTo(DEFAULT_VALID_TILL);
    }

    @Test
    @Transactional
    public void createInsuranceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = insuranceRepository.findAll().size();

        // Create the Insurance with an existing ID
        insurance.setId(1L);
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInsuranceMockMvc.perform(post("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInsurances() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        // Get all the insuranceList
        restInsuranceMockMvc.perform(get("/api/insurances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(insurance.getId().intValue())))
            .andExpect(jsonPath("$.[*].insuranceCompany").value(hasItem(DEFAULT_INSURANCE_COMPANY)))
            .andExpect(jsonPath("$.[*].typeOfInsurance").value(hasItem(DEFAULT_TYPE_OF_INSURANCE)))
            .andExpect(jsonPath("$.[*].dateOfInsurance").value(hasItem(DEFAULT_DATE_OF_INSURANCE.toString())))
            .andExpect(jsonPath("$.[*].validTill").value(hasItem(DEFAULT_VALID_TILL.toString())));
    }
    
    @Test
    @Transactional
    public void getInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        // Get the insurance
        restInsuranceMockMvc.perform(get("/api/insurances/{id}", insurance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(insurance.getId().intValue()))
            .andExpect(jsonPath("$.insuranceCompany").value(DEFAULT_INSURANCE_COMPANY))
            .andExpect(jsonPath("$.typeOfInsurance").value(DEFAULT_TYPE_OF_INSURANCE))
            .andExpect(jsonPath("$.dateOfInsurance").value(DEFAULT_DATE_OF_INSURANCE.toString()))
            .andExpect(jsonPath("$.validTill").value(DEFAULT_VALID_TILL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInsurance() throws Exception {
        // Get the insurance
        restInsuranceMockMvc.perform(get("/api/insurances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        int databaseSizeBeforeUpdate = insuranceRepository.findAll().size();

        // Update the insurance
        Insurance updatedInsurance = insuranceRepository.findById(insurance.getId()).get();
        // Disconnect from session so that the updates on updatedInsurance are not directly saved in db
        em.detach(updatedInsurance);
        updatedInsurance
            .insuranceCompany(UPDATED_INSURANCE_COMPANY)
            .typeOfInsurance(UPDATED_TYPE_OF_INSURANCE)
            .dateOfInsurance(UPDATED_DATE_OF_INSURANCE)
            .validTill(UPDATED_VALID_TILL);
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(updatedInsurance);

        restInsuranceMockMvc.perform(put("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isOk());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeUpdate);
        Insurance testInsurance = insuranceList.get(insuranceList.size() - 1);
        assertThat(testInsurance.getInsuranceCompany()).isEqualTo(UPDATED_INSURANCE_COMPANY);
        assertThat(testInsurance.getTypeOfInsurance()).isEqualTo(UPDATED_TYPE_OF_INSURANCE);
        assertThat(testInsurance.getDateOfInsurance()).isEqualTo(UPDATED_DATE_OF_INSURANCE);
        assertThat(testInsurance.getValidTill()).isEqualTo(UPDATED_VALID_TILL);
    }

    @Test
    @Transactional
    public void updateNonExistingInsurance() throws Exception {
        int databaseSizeBeforeUpdate = insuranceRepository.findAll().size();

        // Create the Insurance
        InsuranceDTO insuranceDTO = insuranceMapper.toDto(insurance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInsuranceMockMvc.perform(put("/api/insurances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(insuranceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Insurance in the database
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInsurance() throws Exception {
        // Initialize the database
        insuranceRepository.saveAndFlush(insurance);

        int databaseSizeBeforeDelete = insuranceRepository.findAll().size();

        // Delete the insurance
        restInsuranceMockMvc.perform(delete("/api/insurances/{id}", insurance.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Insurance> insuranceList = insuranceRepository.findAll();
        assertThat(insuranceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
