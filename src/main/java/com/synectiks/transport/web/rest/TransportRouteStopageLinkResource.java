package com.synectiks.transport.web.rest;

import com.synectiks.transport.service.TransportRouteStopageLinkService;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.service.dto.TransportRouteStopageLinkDTO;

import com.synectiks.transport.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.synectiks.transport.domain.TransportRouteStopageLink}.
 */
@RestController
@RequestMapping("/api")
public class TransportRouteStopageLinkResource {

    private final Logger log = LoggerFactory.getLogger(TransportRouteStopageLinkResource.class);

    private static final String ENTITY_NAME = "transportTransportRouteStopageLink";

    private String applicationName;

    private final TransportRouteStopageLinkService transportRouteStopageLinkService;

    public TransportRouteStopageLinkResource(TransportRouteStopageLinkService transportRouteStopageLinkService) {
        this.transportRouteStopageLinkService = transportRouteStopageLinkService;
    }

    /**
     * {@code POST  /transport-route-stopage-links} : Create a new transportRouteStopageLink.
     *
     * @param transportRouteStopageLinkDTO the transportRouteStopageLinkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transportRouteStopageLinkDTO, or with status {@code 400 (Bad Request)} if the transportRouteStopageLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transport-route-stopage-links")
    public ResponseEntity<TransportRouteStopageLinkDTO> createTransportRouteStopageLink(@RequestBody TransportRouteStopageLinkDTO transportRouteStopageLinkDTO) throws URISyntaxException {
        log.debug("REST request to save TransportRouteStopageLink : {}", transportRouteStopageLinkDTO);
        if (transportRouteStopageLinkDTO.getId() != null) {
            throw new BadRequestAlertException("A new transportRouteStopageLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransportRouteStopageLinkDTO result = transportRouteStopageLinkService.save(transportRouteStopageLinkDTO);
        return ResponseEntity.created(new URI("/api/transport-route-stopage-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transport-route-stopage-links} : Updates an existing transportRouteStopageLink.
     *
     * @param transportRouteStopageLinkDTO the transportRouteStopageLinkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transportRouteStopageLinkDTO,
     * or with status {@code 400 (Bad Request)} if the transportRouteStopageLinkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transportRouteStopageLinkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transport-route-stopage-links")
    public ResponseEntity<TransportRouteStopageLinkDTO> updateTransportRouteStopageLink(@RequestBody TransportRouteStopageLinkDTO transportRouteStopageLinkDTO) throws URISyntaxException {
        log.debug("REST request to update TransportRouteStopageLink : {}", transportRouteStopageLinkDTO);
        if (transportRouteStopageLinkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransportRouteStopageLinkDTO result = transportRouteStopageLinkService.save(transportRouteStopageLinkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transportRouteStopageLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transport-route-stopage-links} : get all the transportRouteStopageLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transportRouteStopageLinks in body.
     */
    @GetMapping("/transport-route-stopage-links")
    public List<TransportRouteStopageLinkDTO> getAllTransportRouteStopageLinks() {
        log.debug("REST request to get all TransportRouteStopageLinks");
        return transportRouteStopageLinkService.findAll();
    }

    /**
     * {@code GET  /transport-route-stopage-links/:id} : get the "id" transportRouteStopageLink.
     *
     * @param id the id of the transportRouteStopageLinkDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transportRouteStopageLinkDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transport-route-stopage-links/{id}")
    public ResponseEntity<TransportRouteStopageLinkDTO> getTransportRouteStopageLink(@PathVariable Long id) {
        log.debug("REST request to get TransportRouteStopageLink : {}", id);
        Optional<TransportRouteStopageLinkDTO> transportRouteStopageLinkDTO = transportRouteStopageLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transportRouteStopageLinkDTO);
    }

    /**
     * {@code DELETE  /transport-route-stopage-links/:id} : delete the "id" transportRouteStopageLink.
     *
     * @param id the id of the transportRouteStopageLinkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transport-route-stopage-links/{id}")
    public ResponseEntity<Void> deleteTransportRouteStopageLink(@PathVariable Long id) {
        log.debug("REST request to delete TransportRouteStopageLink : {}", id);
        transportRouteStopageLinkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
