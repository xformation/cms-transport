package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.VehicleContractLink;
import com.synectiks.transport.repository.VehicleContractLinkRepository;
import com.synectiks.transport.service.VehicleContractLinkService;
import com.synectiks.transport.service.dto.VehicleContractLinkDTO;
import com.synectiks.transport.service.mapper.VehicleContractLinkMapper;
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
 * Integration tests for the {@link VehicleContractLinkResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class VehicleContractLinkResourceIT {

    @Autowired
    private VehicleContractLinkRepository vehicleContractLinkRepository;

    @Autowired
    private VehicleContractLinkMapper vehicleContractLinkMapper;

    @Autowired
    private VehicleContractLinkService vehicleContractLinkService;

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

    private MockMvc restVehicleContractLinkMockMvc;

    private VehicleContractLink vehicleContractLink;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VehicleContractLinkResource vehicleContractLinkResource = new VehicleContractLinkResource(vehicleContractLinkService);
        this.restVehicleContractLinkMockMvc = MockMvcBuilders.standaloneSetup(vehicleContractLinkResource)
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
    public static VehicleContractLink createEntity(EntityManager em) {
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        return vehicleContractLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VehicleContractLink createUpdatedEntity(EntityManager em) {
        VehicleContractLink vehicleContractLink = new VehicleContractLink();
        return vehicleContractLink;
    }

    @BeforeEach
    public void initTest() {
        vehicleContractLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createVehicleContractLink() throws Exception {
        int databaseSizeBeforeCreate = vehicleContractLinkRepository.findAll().size();

        // Create the VehicleContractLink
        VehicleContractLinkDTO vehicleContractLinkDTO = vehicleContractLinkMapper.toDto(vehicleContractLink);
        restVehicleContractLinkMockMvc.perform(post("/api/vehicle-contract-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleContractLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the VehicleContractLink in the database
        List<VehicleContractLink> vehicleContractLinkList = vehicleContractLinkRepository.findAll();
        assertThat(vehicleContractLinkList).hasSize(databaseSizeBeforeCreate + 1);
        VehicleContractLink testVehicleContractLink = vehicleContractLinkList.get(vehicleContractLinkList.size() - 1);
    }

    @Test
    @Transactional
    public void createVehicleContractLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vehicleContractLinkRepository.findAll().size();

        // Create the VehicleContractLink with an existing ID
        vehicleContractLink.setId(1L);
        VehicleContractLinkDTO vehicleContractLinkDTO = vehicleContractLinkMapper.toDto(vehicleContractLink);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicleContractLinkMockMvc.perform(post("/api/vehicle-contract-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleContractLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleContractLink in the database
        List<VehicleContractLink> vehicleContractLinkList = vehicleContractLinkRepository.findAll();
        assertThat(vehicleContractLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVehicleContractLinks() throws Exception {
        // Initialize the database
        vehicleContractLinkRepository.saveAndFlush(vehicleContractLink);

        // Get all the vehicleContractLinkList
        restVehicleContractLinkMockMvc.perform(get("/api/vehicle-contract-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicleContractLink.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getVehicleContractLink() throws Exception {
        // Initialize the database
        vehicleContractLinkRepository.saveAndFlush(vehicleContractLink);

        // Get the vehicleContractLink
        restVehicleContractLinkMockMvc.perform(get("/api/vehicle-contract-links/{id}", vehicleContractLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehicleContractLink.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVehicleContractLink() throws Exception {
        // Get the vehicleContractLink
        restVehicleContractLinkMockMvc.perform(get("/api/vehicle-contract-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVehicleContractLink() throws Exception {
        // Initialize the database
        vehicleContractLinkRepository.saveAndFlush(vehicleContractLink);

        int databaseSizeBeforeUpdate = vehicleContractLinkRepository.findAll().size();

        // Update the vehicleContractLink
        VehicleContractLink updatedVehicleContractLink = vehicleContractLinkRepository.findById(vehicleContractLink.getId()).get();
        // Disconnect from session so that the updates on updatedVehicleContractLink are not directly saved in db
        em.detach(updatedVehicleContractLink);
        VehicleContractLinkDTO vehicleContractLinkDTO = vehicleContractLinkMapper.toDto(updatedVehicleContractLink);

        restVehicleContractLinkMockMvc.perform(put("/api/vehicle-contract-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleContractLinkDTO)))
            .andExpect(status().isOk());

        // Validate the VehicleContractLink in the database
        List<VehicleContractLink> vehicleContractLinkList = vehicleContractLinkRepository.findAll();
        assertThat(vehicleContractLinkList).hasSize(databaseSizeBeforeUpdate);
        VehicleContractLink testVehicleContractLink = vehicleContractLinkList.get(vehicleContractLinkList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingVehicleContractLink() throws Exception {
        int databaseSizeBeforeUpdate = vehicleContractLinkRepository.findAll().size();

        // Create the VehicleContractLink
        VehicleContractLinkDTO vehicleContractLinkDTO = vehicleContractLinkMapper.toDto(vehicleContractLink);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicleContractLinkMockMvc.perform(put("/api/vehicle-contract-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(vehicleContractLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VehicleContractLink in the database
        List<VehicleContractLink> vehicleContractLinkList = vehicleContractLinkRepository.findAll();
        assertThat(vehicleContractLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVehicleContractLink() throws Exception {
        // Initialize the database
        vehicleContractLinkRepository.saveAndFlush(vehicleContractLink);

        int databaseSizeBeforeDelete = vehicleContractLinkRepository.findAll().size();

        // Delete the vehicleContractLink
        restVehicleContractLinkMockMvc.perform(delete("/api/vehicle-contract-links/{id}", vehicleContractLink.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VehicleContractLink> vehicleContractLinkList = vehicleContractLinkRepository.findAll();
        assertThat(vehicleContractLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
