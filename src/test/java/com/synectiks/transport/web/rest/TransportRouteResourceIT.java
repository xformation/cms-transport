package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.TransportRoute;
import com.synectiks.transport.repository.TransportRouteRepository;
import com.synectiks.transport.service.TransportRouteService;
import com.synectiks.transport.service.dto.TransportRouteDTO;
import com.synectiks.transport.service.mapper.TransportRouteMapper;
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
 * Integration tests for the {@link TransportRouteResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class TransportRouteResourceIT {

    private static final String DEFAULT_ROUTE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_MAP_URL = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_MAP_URL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NO_OF_STOPS = 1;
    private static final Integer UPDATED_NO_OF_STOPS = 2;

    private static final String DEFAULT_ROUTE_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_FREQUENCY = "BBBBBBBBBB";

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
    private TransportRouteRepository transportRouteRepository;

    @Autowired
    private TransportRouteMapper transportRouteMapper;

    @Autowired
    private TransportRouteService transportRouteService;

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

    private MockMvc restTransportRouteMockMvc;

    private TransportRoute transportRoute;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransportRouteResource transportRouteResource = new TransportRouteResource(transportRouteService);
        this.restTransportRouteMockMvc = MockMvcBuilders.standaloneSetup(transportRouteResource)
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
    public static TransportRoute createEntity(EntityManager em) {
        TransportRoute transportRoute = new TransportRoute()
            .routeName(DEFAULT_ROUTE_NAME)
            .routeDetails(DEFAULT_ROUTE_DETAILS)
            .routeMapUrl(DEFAULT_ROUTE_MAP_URL)
            .noOfStops(DEFAULT_NO_OF_STOPS)
            .routeFrequency(DEFAULT_ROUTE_FREQUENCY)
            .status(DEFAULT_STATUS)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .updatedBy(DEFAULT_UPDATED_BY)
            .updatedOn(DEFAULT_UPDATED_ON)
            .branchId(DEFAULT_BRANCH_ID);
        return transportRoute;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransportRoute createUpdatedEntity(EntityManager em) {
        TransportRoute transportRoute = new TransportRoute()
            .routeName(UPDATED_ROUTE_NAME)
            .routeDetails(UPDATED_ROUTE_DETAILS)
            .routeMapUrl(UPDATED_ROUTE_MAP_URL)
            .noOfStops(UPDATED_NO_OF_STOPS)
            .routeFrequency(UPDATED_ROUTE_FREQUENCY)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .branchId(UPDATED_BRANCH_ID);
        return transportRoute;
    }

    @BeforeEach
    public void initTest() {
        transportRoute = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransportRoute() throws Exception {
        int databaseSizeBeforeCreate = transportRouteRepository.findAll().size();

        // Create the TransportRoute
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);
        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isCreated());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeCreate + 1);
        TransportRoute testTransportRoute = transportRouteList.get(transportRouteList.size() - 1);
        assertThat(testTransportRoute.getRouteName()).isEqualTo(DEFAULT_ROUTE_NAME);
        assertThat(testTransportRoute.getRouteDetails()).isEqualTo(DEFAULT_ROUTE_DETAILS);
        assertThat(testTransportRoute.getRouteMapUrl()).isEqualTo(DEFAULT_ROUTE_MAP_URL);
        assertThat(testTransportRoute.getNoOfStops()).isEqualTo(DEFAULT_NO_OF_STOPS);
        assertThat(testTransportRoute.getRouteFrequency()).isEqualTo(DEFAULT_ROUTE_FREQUENCY);
        assertThat(testTransportRoute.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTransportRoute.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTransportRoute.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testTransportRoute.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
        assertThat(testTransportRoute.getUpdatedOn()).isEqualTo(DEFAULT_UPDATED_ON);
        assertThat(testTransportRoute.getBranchId()).isEqualTo(DEFAULT_BRANCH_ID);
    }

    @Test
    @Transactional
    public void createTransportRouteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transportRouteRepository.findAll().size();

        // Create the TransportRoute with an existing ID
        transportRoute.setId(1L);
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportRouteMockMvc.perform(post("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTransportRoutes() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        // Get all the transportRouteList
        restTransportRouteMockMvc.perform(get("/api/transport-routes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportRoute.getId().intValue())))
            .andExpect(jsonPath("$.[*].routeName").value(hasItem(DEFAULT_ROUTE_NAME)))
            .andExpect(jsonPath("$.[*].routeDetails").value(hasItem(DEFAULT_ROUTE_DETAILS)))
            .andExpect(jsonPath("$.[*].routeMapUrl").value(hasItem(DEFAULT_ROUTE_MAP_URL)))
            .andExpect(jsonPath("$.[*].noOfStops").value(hasItem(DEFAULT_NO_OF_STOPS)))
            .andExpect(jsonPath("$.[*].routeFrequency").value(hasItem(DEFAULT_ROUTE_FREQUENCY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)))
            .andExpect(jsonPath("$.[*].updatedOn").value(hasItem(DEFAULT_UPDATED_ON.toString())))
            .andExpect(jsonPath("$.[*].branchId").value(hasItem(DEFAULT_BRANCH_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getTransportRoute() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        // Get the transportRoute
        restTransportRouteMockMvc.perform(get("/api/transport-routes/{id}", transportRoute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transportRoute.getId().intValue()))
            .andExpect(jsonPath("$.routeName").value(DEFAULT_ROUTE_NAME))
            .andExpect(jsonPath("$.routeDetails").value(DEFAULT_ROUTE_DETAILS))
            .andExpect(jsonPath("$.routeMapUrl").value(DEFAULT_ROUTE_MAP_URL))
            .andExpect(jsonPath("$.noOfStops").value(DEFAULT_NO_OF_STOPS))
            .andExpect(jsonPath("$.routeFrequency").value(DEFAULT_ROUTE_FREQUENCY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY))
            .andExpect(jsonPath("$.updatedOn").value(DEFAULT_UPDATED_ON.toString()))
            .andExpect(jsonPath("$.branchId").value(DEFAULT_BRANCH_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTransportRoute() throws Exception {
        // Get the transportRoute
        restTransportRouteMockMvc.perform(get("/api/transport-routes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransportRoute() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        int databaseSizeBeforeUpdate = transportRouteRepository.findAll().size();

        // Update the transportRoute
        TransportRoute updatedTransportRoute = transportRouteRepository.findById(transportRoute.getId()).get();
        // Disconnect from session so that the updates on updatedTransportRoute are not directly saved in db
        em.detach(updatedTransportRoute);
        updatedTransportRoute
            .routeName(UPDATED_ROUTE_NAME)
            .routeDetails(UPDATED_ROUTE_DETAILS)
            .routeMapUrl(UPDATED_ROUTE_MAP_URL)
            .noOfStops(UPDATED_NO_OF_STOPS)
            .routeFrequency(UPDATED_ROUTE_FREQUENCY)
            .status(UPDATED_STATUS)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .updatedBy(UPDATED_UPDATED_BY)
            .updatedOn(UPDATED_UPDATED_ON)
            .branchId(UPDATED_BRANCH_ID);
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(updatedTransportRoute);

        restTransportRouteMockMvc.perform(put("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isOk());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeUpdate);
        TransportRoute testTransportRoute = transportRouteList.get(transportRouteList.size() - 1);
        assertThat(testTransportRoute.getRouteName()).isEqualTo(UPDATED_ROUTE_NAME);
        assertThat(testTransportRoute.getRouteDetails()).isEqualTo(UPDATED_ROUTE_DETAILS);
        assertThat(testTransportRoute.getRouteMapUrl()).isEqualTo(UPDATED_ROUTE_MAP_URL);
        assertThat(testTransportRoute.getNoOfStops()).isEqualTo(UPDATED_NO_OF_STOPS);
        assertThat(testTransportRoute.getRouteFrequency()).isEqualTo(UPDATED_ROUTE_FREQUENCY);
        assertThat(testTransportRoute.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTransportRoute.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTransportRoute.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testTransportRoute.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        assertThat(testTransportRoute.getUpdatedOn()).isEqualTo(UPDATED_UPDATED_ON);
        assertThat(testTransportRoute.getBranchId()).isEqualTo(UPDATED_BRANCH_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingTransportRoute() throws Exception {
        int databaseSizeBeforeUpdate = transportRouteRepository.findAll().size();

        // Create the TransportRoute
        TransportRouteDTO transportRouteDTO = transportRouteMapper.toDto(transportRoute);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportRouteMockMvc.perform(put("/api/transport-routes")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRoute in the database
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransportRoute() throws Exception {
        // Initialize the database
        transportRouteRepository.saveAndFlush(transportRoute);

        int databaseSizeBeforeDelete = transportRouteRepository.findAll().size();

        // Delete the transportRoute
        restTransportRouteMockMvc.perform(delete("/api/transport-routes/{id}", transportRoute.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransportRoute> transportRouteList = transportRouteRepository.findAll();
        assertThat(transportRouteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
