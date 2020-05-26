package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.VehicleDriverLink;
import com.synectiks.transport.repository.VehicleDriverLinkRepository;
import com.synectiks.transport.service.VehicleDriverLinkService;
import com.synectiks.transport.service.dto.VehicleDriverLinkDTO;
import com.synectiks.transport.service.mapper.VehicleDriverLinkMapper;
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
import java.util.List;

import static com.synectiks.transport.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link VehicleDriverLinkResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class VehicleDriverLinkResourceIT {

    private static final Long DEFAULT_EMPLOYEE_ID = 1L;
    private static final Long UPDATED_EMPLOYEE_ID = 2L;

    @Autowired
    private VehicleDriverLinkRepository vehicleDriverLinkRepository;

    @Autowired
    private VehicleDriverLinkMapper vehicleDriverLinkMapper;

    @Autowired
    private VehicleDriverLinkService vehicleDriverLinkService;

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

    private MockMvc restVehicleDriverLinkMockMvc;

    private VehicleDriverLink vehicleDriverLink;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VehicleDriverLinkResource vehicleDriverLinkResource = new VehicleDriverLinkResource(vehicleDriverLinkService);
        this.restVehicleDriverLinkMockMvc = MockMvcBuilders.standaloneSetup(vehicleDriverLinkResource)
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
    public static VehicleDriverLink createEntity(EntityManager em) {
        VehicleDriverLink vehicleDriverLink = new VehicleDriverLink()
            .employeeId(DEFAULT_EMPLOYEE_ID);
        return vehicleDriverLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VehicleDriverLink createUpdatedEntity(EntityManager em) {
        VehicleDriverLink vehicleDriverLink = new VehicleDriverLink()
            .employeeId(UPDATED_EMPLOYEE_ID);
        return vehicleDriverLink;
    }

    @BeforeEach
    public void initTest() {
        vehicleDriverLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicleDriverLink() throws Exception {
        int databaseSizeBeforeCreate = vehicleDriverLinkRepository.findAll().size();

        // Create the VehicleDriverLink
        VehicleDriverLinkDTO vehicleDriverLinkDTO = vehicleDriverLinkMapper.toDto(vehicleDriverLink);
        restVehicleDriverLinkMockMvc.perform(post("/api/vehicle-driver-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDriverLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the VehicleDriverLink in the database
        List<VehicleDriverLink> vehicleDriverLinkList = vehicleDriverLinkRepository.findAll();
        assertThat(vehicleDriverLinkList).hasSize(databaseSizeBeforeCreate + 1);
        VehicleDriverLink testVehicleDriverLink = vehicleDriverLinkList.get(vehicleDriverLinkList.size() - 1);
        assertThat(testVehicleDriverLink.getEmployeeId()).isEqualTo(DEFAULT_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void createVehicleDriverLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehicleDriverLinkRepository.findAll().size();

        // Create the VehicleDriverLink with an existing ID
        vehicleDriverLink.setId(1L);
        VehicleDriverLinkDTO vehicleDriverLinkDTO = vehicleDriverLinkMapper.toDto(vehicleDriverLink);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicleDriverLinkMockMvc.perform(post("/api/vehicle-driver-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDriverLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleDriverLink in the database
        List<VehicleDriverLink> vehicleDriverLinkList = vehicleDriverLinkRepository.findAll();
        assertThat(vehicleDriverLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVehicleDriverLinks() throws Exception {
        // Initialize the database
        vehicleDriverLinkRepository.saveAndFlush(vehicleDriverLink);

        // Get all the vehicleDriverLinkList
        restVehicleDriverLinkMockMvc.perform(get("/api/vehicle-driver-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicleDriverLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].employeeId").value(hasItem(DEFAULT_EMPLOYEE_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getVehicleDriverLink() throws Exception {
        // Initialize the database
        vehicleDriverLinkRepository.saveAndFlush(vehicleDriverLink);

        // Get the vehicleDriverLink
        restVehicleDriverLinkMockMvc.perform(get("/api/vehicle-driver-links/{id}", vehicleDriverLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehicleDriverLink.getId().intValue()))
            .andExpect(jsonPath("$.employeeId").value(DEFAULT_EMPLOYEE_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVehicleDriverLink() throws Exception {
        // Get the vehicleDriverLink
        restVehicleDriverLinkMockMvc.perform(get("/api/vehicle-driver-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicleDriverLink() throws Exception {
        // Initialize the database
        vehicleDriverLinkRepository.saveAndFlush(vehicleDriverLink);

        int databaseSizeBeforeUpdate = vehicleDriverLinkRepository.findAll().size();

        // Update the vehicleDriverLink
        VehicleDriverLink updatedVehicleDriverLink = vehicleDriverLinkRepository.findById(vehicleDriverLink.getId()).get();
        // Disconnect from session so that the updates on updatedVehicleDriverLink are not directly saved in db
        em.detach(updatedVehicleDriverLink);
        updatedVehicleDriverLink
            .employeeId(UPDATED_EMPLOYEE_ID);
        VehicleDriverLinkDTO vehicleDriverLinkDTO = vehicleDriverLinkMapper.toDto(updatedVehicleDriverLink);

        restVehicleDriverLinkMockMvc.perform(put("/api/vehicle-driver-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDriverLinkDTO)))
            .andExpect(status().isOk());

        // Validate the VehicleDriverLink in the database
        List<VehicleDriverLink> vehicleDriverLinkList = vehicleDriverLinkRepository.findAll();
        assertThat(vehicleDriverLinkList).hasSize(databaseSizeBeforeUpdate);
        VehicleDriverLink testVehicleDriverLink = vehicleDriverLinkList.get(vehicleDriverLinkList.size() - 1);
        assertThat(testVehicleDriverLink.getEmployeeId()).isEqualTo(UPDATED_EMPLOYEE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicleDriverLink() throws Exception {
        int databaseSizeBeforeUpdate = vehicleDriverLinkRepository.findAll().size();

        // Create the VehicleDriverLink
        VehicleDriverLinkDTO vehicleDriverLinkDTO = vehicleDriverLinkMapper.toDto(vehicleDriverLink);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicleDriverLinkMockMvc.perform(put("/api/vehicle-driver-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleDriverLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleDriverLink in the database
        List<VehicleDriverLink> vehicleDriverLinkList = vehicleDriverLinkRepository.findAll();
        assertThat(vehicleDriverLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVehicleDriverLink() throws Exception {
        // Initialize the database
        vehicleDriverLinkRepository.saveAndFlush(vehicleDriverLink);

        int databaseSizeBeforeDelete = vehicleDriverLinkRepository.findAll().size();

        // Delete the vehicleDriverLink
        restVehicleDriverLinkMockMvc.perform(delete("/api/vehicle-driver-links/{id}", vehicleDriverLink.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VehicleDriverLink> vehicleDriverLinkList = vehicleDriverLinkRepository.findAll();
        assertThat(vehicleDriverLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
