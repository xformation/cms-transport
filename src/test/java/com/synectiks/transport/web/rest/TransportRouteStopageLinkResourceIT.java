package com.synectiks.transport.web.rest;

import com.synectiks.transport.TransportApp;
import com.synectiks.transport.domain.TransportRouteStopageLink;
import com.synectiks.transport.repository.TransportRouteStopageLinkRepository;
import com.synectiks.transport.service.TransportRouteStopageLinkService;
import com.synectiks.transport.service.dto.TransportRouteStopageLinkDTO;
import com.synectiks.transport.service.mapper.TransportRouteStopageLinkMapper;
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
 * Integration tests for the {@link TransportRouteStopageLinkResource} REST controller.
 */
@SpringBootTest(classes = TransportApp.class)
public class TransportRouteStopageLinkResourceIT {

    @Autowired
    private TransportRouteStopageLinkRepository transportRouteStopageLinkRepository;

    @Autowired
    private TransportRouteStopageLinkMapper transportRouteStopageLinkMapper;

    @Autowired
    private TransportRouteStopageLinkService transportRouteStopageLinkService;

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

    private MockMvc restTransportRouteStopageLinkMockMvc;

    private TransportRouteStopageLink transportRouteStopageLink;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TransportRouteStopageLinkResource transportRouteStopageLinkResource = new TransportRouteStopageLinkResource(transportRouteStopageLinkService);
        this.restTransportRouteStopageLinkMockMvc = MockMvcBuilders.standaloneSetup(transportRouteStopageLinkResource)
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
    public static TransportRouteStopageLink createEntity(EntityManager em) {
        TransportRouteStopageLink transportRouteStopageLink = new TransportRouteStopageLink();
        return transportRouteStopageLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransportRouteStopageLink createUpdatedEntity(EntityManager em) {
        TransportRouteStopageLink transportRouteStopageLink = new TransportRouteStopageLink();
        return transportRouteStopageLink;
    }

    @BeforeEach
    public void initTest() {
        transportRouteStopageLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransportRouteStopageLink() throws Exception {
        int databaseSizeBeforeCreate = transportRouteStopageLinkRepository.findAll().size();

        // Create the TransportRouteStopageLink
        TransportRouteStopageLinkDTO transportRouteStopageLinkDTO = transportRouteStopageLinkMapper.toDto(transportRouteStopageLink);
        restTransportRouteStopageLinkMockMvc.perform(post("/api/transport-route-stopage-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteStopageLinkDTO)))
            .andExpect(status().isCreated());

        // Validate the TransportRouteStopageLink in the database
        List<TransportRouteStopageLink> transportRouteStopageLinkList = transportRouteStopageLinkRepository.findAll();
        assertThat(transportRouteStopageLinkList).hasSize(databaseSizeBeforeCreate + 1);
        TransportRouteStopageLink testTransportRouteStopageLink = transportRouteStopageLinkList.get(transportRouteStopageLinkList.size() - 1);
    }

    @Test
    @Transactional
    public void createTransportRouteStopageLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transportRouteStopageLinkRepository.findAll().size();

        // Create the TransportRouteStopageLink with an existing ID
        transportRouteStopageLink.setId(1L);
        TransportRouteStopageLinkDTO transportRouteStopageLinkDTO = transportRouteStopageLinkMapper.toDto(transportRouteStopageLink);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransportRouteStopageLinkMockMvc.perform(post("/api/transport-route-stopage-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteStopageLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRouteStopageLink in the database
        List<TransportRouteStopageLink> transportRouteStopageLinkList = transportRouteStopageLinkRepository.findAll();
        assertThat(transportRouteStopageLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTransportRouteStopageLinks() throws Exception {
        // Initialize the database
        transportRouteStopageLinkRepository.saveAndFlush(transportRouteStopageLink);

        // Get all the transportRouteStopageLinkList
        restTransportRouteStopageLinkMockMvc.perform(get("/api/transport-route-stopage-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transportRouteStopageLink.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getTransportRouteStopageLink() throws Exception {
        // Initialize the database
        transportRouteStopageLinkRepository.saveAndFlush(transportRouteStopageLink);

        // Get the transportRouteStopageLink
        restTransportRouteStopageLinkMockMvc.perform(get("/api/transport-route-stopage-links/{id}", transportRouteStopageLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transportRouteStopageLink.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTransportRouteStopageLink() throws Exception {
        // Get the transportRouteStopageLink
        restTransportRouteStopageLinkMockMvc.perform(get("/api/transport-route-stopage-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransportRouteStopageLink() throws Exception {
        // Initialize the database
        transportRouteStopageLinkRepository.saveAndFlush(transportRouteStopageLink);

        int databaseSizeBeforeUpdate = transportRouteStopageLinkRepository.findAll().size();

        // Update the transportRouteStopageLink
        TransportRouteStopageLink updatedTransportRouteStopageLink = transportRouteStopageLinkRepository.findById(transportRouteStopageLink.getId()).get();
        // Disconnect from session so that the updates on updatedTransportRouteStopageLink are not directly saved in db
        em.detach(updatedTransportRouteStopageLink);
        TransportRouteStopageLinkDTO transportRouteStopageLinkDTO = transportRouteStopageLinkMapper.toDto(updatedTransportRouteStopageLink);

        restTransportRouteStopageLinkMockMvc.perform(put("/api/transport-route-stopage-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteStopageLinkDTO)))
            .andExpect(status().isOk());

        // Validate the TransportRouteStopageLink in the database
        List<TransportRouteStopageLink> transportRouteStopageLinkList = transportRouteStopageLinkRepository.findAll();
        assertThat(transportRouteStopageLinkList).hasSize(databaseSizeBeforeUpdate);
        TransportRouteStopageLink testTransportRouteStopageLink = transportRouteStopageLinkList.get(transportRouteStopageLinkList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingTransportRouteStopageLink() throws Exception {
        int databaseSizeBeforeUpdate = transportRouteStopageLinkRepository.findAll().size();

        // Create the TransportRouteStopageLink
        TransportRouteStopageLinkDTO transportRouteStopageLinkDTO = transportRouteStopageLinkMapper.toDto(transportRouteStopageLink);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransportRouteStopageLinkMockMvc.perform(put("/api/transport-route-stopage-links")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transportRouteStopageLinkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TransportRouteStopageLink in the database
        List<TransportRouteStopageLink> transportRouteStopageLinkList = transportRouteStopageLinkRepository.findAll();
        assertThat(transportRouteStopageLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransportRouteStopageLink() throws Exception {
        // Initialize the database
        transportRouteStopageLinkRepository.saveAndFlush(transportRouteStopageLink);

        int databaseSizeBeforeDelete = transportRouteStopageLinkRepository.findAll().size();

        // Delete the transportRouteStopageLink
        restTransportRouteStopageLinkMockMvc.perform(delete("/api/transport-route-stopage-links/{id}", transportRouteStopageLink.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TransportRouteStopageLink> transportRouteStopageLinkList = transportRouteStopageLinkRepository.findAll();
        assertThat(transportRouteStopageLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
