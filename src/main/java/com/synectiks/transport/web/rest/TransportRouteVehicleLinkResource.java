package com.synectiks.transport.web.rest;

import com.synectiks.transport.service.TransportRouteVehicleLinkService;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.service.dto.TransportRouteVehicleLinkDTO;

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
 * REST controller for managing {@link com.synectiks.transport.domain.TransportRouteVehicleLink}.
 */
@RestController
@RequestMapping("/api")
public class TransportRouteVehicleLinkResource {

    private final Logger log = LoggerFactory.getLogger(TransportRouteVehicleLinkResource.class);

    private static final String ENTITY_NAME = "transportTransportRouteVehicleLink";

    private String applicationName;

    private final TransportRouteVehicleLinkService transportRouteVehicleLinkService;

    public TransportRouteVehicleLinkResource(TransportRouteVehicleLinkService transportRouteVehicleLinkService) {
        this.transportRouteVehicleLinkService = transportRouteVehicleLinkService;
    }

    /**
     * {@code POST  /transport-route-vehicle-links} : Create a new transportRouteVehicleLink.
     *
     * @param transportRouteVehicleLinkDTO the transportRouteVehicleLinkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transportRouteVehicleLinkDTO, or with status {@code 400 (Bad Request)} if the transportRouteVehicleLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transport-route-vehicle-links")
    public ResponseEntity<TransportRouteVehicleLinkDTO> createTransportRouteVehicleLink(@RequestBody TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO) throws URISyntaxException {
        log.debug("REST request to save TransportRouteVehicleLink : {}", transportRouteVehicleLinkDTO);
        if (transportRouteVehicleLinkDTO.getId() != null) {
            throw new BadRequestAlertException("A new transportRouteVehicleLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransportRouteVehicleLinkDTO result = transportRouteVehicleLinkService.save(transportRouteVehicleLinkDTO);
        return ResponseEntity.created(new URI("/api/transport-route-vehicle-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transport-route-vehicle-links} : Updates an existing transportRouteVehicleLink.
     *
     * @param transportRouteVehicleLinkDTO the transportRouteVehicleLinkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transportRouteVehicleLinkDTO,
     * or with status {@code 400 (Bad Request)} if the transportRouteVehicleLinkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transportRouteVehicleLinkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transport-route-vehicle-links")
    public ResponseEntity<TransportRouteVehicleLinkDTO> updateTransportRouteVehicleLink(@RequestBody TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO) throws URISyntaxException {
        log.debug("REST request to update TransportRouteVehicleLink : {}", transportRouteVehicleLinkDTO);
        if (transportRouteVehicleLinkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransportRouteVehicleLinkDTO result = transportRouteVehicleLinkService.save(transportRouteVehicleLinkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transportRouteVehicleLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transport-route-vehicle-links} : get all the transportRouteVehicleLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transportRouteVehicleLinks in body.
     */
    @GetMapping("/transport-route-vehicle-links")
    public List<TransportRouteVehicleLinkDTO> getAllTransportRouteVehicleLinks() {
        log.debug("REST request to get all TransportRouteVehicleLinks");
        return transportRouteVehicleLinkService.findAll();
    }

    /**
     * {@code GET  /transport-route-vehicle-links/:id} : get the "id" transportRouteVehicleLink.
     *
     * @param id the id of the transportRouteVehicleLinkDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transportRouteVehicleLinkDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transport-route-vehicle-links/{id}")
    public ResponseEntity<TransportRouteVehicleLinkDTO> getTransportRouteVehicleLink(@PathVariable Long id) {
        log.debug("REST request to get TransportRouteVehicleLink : {}", id);
        Optional<TransportRouteVehicleLinkDTO> transportRouteVehicleLinkDTO = transportRouteVehicleLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transportRouteVehicleLinkDTO);
    }

    /**
     * {@code DELETE  /transport-route-vehicle-links/:id} : delete the "id" transportRouteVehicleLink.
     *
     * @param id the id of the transportRouteVehicleLinkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transport-route-vehicle-links/{id}")
    public ResponseEntity<Void> deleteTransportRouteVehicleLink(@PathVariable Long id) {
        log.debug("REST request to delete TransportRouteVehicleLink : {}", id);
        transportRouteVehicleLinkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
