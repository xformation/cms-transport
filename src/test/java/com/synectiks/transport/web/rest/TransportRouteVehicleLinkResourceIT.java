package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.TransportRouteVehicleLink;
import com.synectiks.transport.repository.TransportRouteVehicleLinkRepository;
import com.synectiks.transport.service.TransportRouteVehicleLinkService;
import com.synectiks.transport.service.dto.TransportRouteVehicleLinkDTO;
import com.synectiks.transport.service.mapper.TransportRouteVehicleLinkMapper;
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
 * Integration tests for the {@link TransportRouteVehicleLinkResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class TransportRouteVehicleLinkResourceIT {

    @Autowired
    private TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository;

    @Autowired
    private TransportRouteVehicleLinkMapper transportRouteVehicleLinkMapper;

    @Autowired
    private TransportRouteVehicleLinkService transportRouteVehicleLinkService;

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

    private MockMvc restTransportRouteVehicleLinkMockMvc;

    private TransportRouteVehicleLink transportRouteVehicleLink;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransportRouteVehicleLinkResource transportRouteVehicleLinkResource = new TransportRouteVehicleLinkResource(transportRouteVehicleLinkService);
        this.restTransportRouteVehicleLinkMockMvc = MockMvcBuilders.standaloneSetup(transportRouteVehicleLinkResource)
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
    public static TransportRouteVehicleLink createEntity(EntityManager em) {
        TransportRouteVehicleLink transportRouteVehicleLink = new TransportRouteVehicleLink();
        return transportRouteVehicleLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransportRouteVehicleLink createUpdatedEntity(EntityManager em) {
        TransportRouteVehicleLink transportRouteVehicleLink = new TransportRouteVehicleLink();
        return transportRouteVehicleLink;
    }

    @BeforeEach
    public void initTest() {
        transportRouteVehicleLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransportRouteVehicleLink() throws Exception {
        int databaseSizeBeforeCreate = transportRouteVehicleLinkRepository.findAll().size();

        // Create the TransportRouteVehicleLink
        TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO = transportRouteVehicleLinkMapper.toDto(transportRouteVehicleLink);
        restTransportRouteVehicleLinkMockMvc.perform(post("/api/transport-route-vehicle-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteVehicleLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the TransportRouteVehicleLink in the database
        List<TransportRouteVehicleLink> transportRouteVehicleLinkList = transportRouteVehicleLinkRepository.findAll();
        assertThat(transportRouteVehicleLinkList).hasSize(databaseSizeBeforeCreate + 1);
        TransportRouteVehicleLink testTransportRouteVehicleLink = transportRouteVehicleLinkList.get(transportRouteVehicleLinkList.size() - 1);
    }

    @Test
    @Transactional
    public void createTransportRouteVehicleLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transportRouteVehicleLinkRepository.findAll().size();

        // Create the TransportRouteVehicleLink with an existing ID
        transportRouteVehicleLink.setId(1L);
        TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO = transportRouteVehicleLinkMapper.toDto(transportRouteVehicleLink);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportRouteVehicleLinkMockMvc.perform(post("/api/transport-route-vehicle-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteVehicleLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRouteVehicleLink in the database
        List<TransportRouteVehicleLink> transportRouteVehicleLinkList = transportRouteVehicleLinkRepository.findAll();
        assertThat(transportRouteVehicleLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTransportRouteVehicleLinks() throws Exception {
        // Initialize the database
        transportRouteVehicleLinkRepository.saveAndFlush(transportRouteVehicleLink);

        // Get all the transportRouteVehicleLinkList
        restTransportRouteVehicleLinkMockMvc.perform(get("/api/transport-route-vehicle-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportRouteVehicleLink.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTransportRouteVehicleLink() throws Exception {
        // Initialize the database
        transportRouteVehicleLinkRepository.saveAndFlush(transportRouteVehicleLink);

        // Get the transportRouteVehicleLink
        restTransportRouteVehicleLinkMockMvc.perform(get("/api/transport-route-vehicle-links/{id}", transportRouteVehicleLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transportRouteVehicleLink.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTransportRouteVehicleLink() throws Exception {
        // Get the transportRouteVehicleLink
        restTransportRouteVehicleLinkMockMvc.perform(get("/api/transport-route-vehicle-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransportRouteVehicleLink() throws Exception {
        // Initialize the database
        transportRouteVehicleLinkRepository.saveAndFlush(transportRouteVehicleLink);

        int databaseSizeBeforeUpdate = transportRouteVehicleLinkRepository.findAll().size();

        // Update the transportRouteVehicleLink
        TransportRouteVehicleLink updatedTransportRouteVehicleLink = transportRouteVehicleLinkRepository.findById(transportRouteVehicleLink.getId()).get();
        // Disconnect from session so that the updates on updatedTransportRouteVehicleLink are not directly saved in db
        em.detach(updatedTransportRouteVehicleLink);
        TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO = transportRouteVehicleLinkMapper.toDto(updatedTransportRouteVehicleLink);

        restTransportRouteVehicleLinkMockMvc.perform(put("/api/transport-route-vehicle-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteVehicleLinkDTO)))
            .andExpect(status().isOk());

        // Validate the TransportRouteVehicleLink in the database
        List<TransportRouteVehicleLink> transportRouteVehicleLinkList = transportRouteVehicleLinkRepository.findAll();
        assertThat(transportRouteVehicleLinkList).hasSize(databaseSizeBeforeUpdate);
        TransportRouteVehicleLink testTransportRouteVehicleLink = transportRouteVehicleLinkList.get(transportRouteVehicleLinkList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTransportRouteVehicleLink() throws Exception {
        int databaseSizeBeforeUpdate = transportRouteVehicleLinkRepository.findAll().size();

        // Create the TransportRouteVehicleLink
        TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO = transportRouteVehicleLinkMapper.toDto(transportRouteVehicleLink);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportRouteVehicleLinkMockMvc.perform(put("/api/transport-route-vehicle-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteVehicleLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRouteVehicleLink in the database
        List<TransportRouteVehicleLink> transportRouteVehicleLinkList = transportRouteVehicleLinkRepository.findAll();
        assertThat(transportRouteVehicleLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransportRouteVehicleLink() throws Exception {
        // Initialize the database
        transportRouteVehicleLinkRepository.saveAndFlush(transportRouteVehicleLink);

        int databaseSizeBeforeDelete = transportRouteVehicleLinkRepository.findAll().size();

        // Delete the transportRouteVehicleLink
        restTransportRouteVehicleLinkMockMvc.perform(delete("/api/transport-route-vehicle-links/{id}", transportRouteVehicleLink.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransportRouteVehicleLink> transportRouteVehicleLinkList = transportRouteVehicleLinkRepository.findAll();
        assertThat(transportRouteVehicleLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
