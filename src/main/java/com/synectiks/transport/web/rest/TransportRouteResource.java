package com.synectiks.transport.web.rest;

import com.synectiks.transport.service.TransportRouteService;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.service.dto.TransportRouteDTO;

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
 * REST controller for managing {@link com.synectiks.transport.domain.TransportRoute}.
 */
@RestController
@RequestMapping("/api")
public class TransportRouteResource {

    private final Logger log = LoggerFactory.getLogger(TransportRouteResource.class);

    private static final String ENTITY_NAME = "transportTransportRoute";

    private String applicationName;

    private final TransportRouteService transportRouteService;

    public TransportRouteResource(TransportRouteService transportRouteService) {
        this.transportRouteService = transportRouteService;
    }

    /**
     * {@code POST  /transport-routes} : Create a new transportRoute.
     *
     * @param transportRouteDTO the transportRouteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transportRouteDTO, or with status {@code 400 (Bad Request)} if the transportRoute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transport-routes")
    public ResponseEntity<TransportRouteDTO> createTransportRoute(@RequestBody TransportRouteDTO transportRouteDTO) throws URISyntaxException {
        log.debug("REST request to save TransportRoute : {}", transportRouteDTO);
        if (transportRouteDTO.getId() != null) {
            throw new BadRequestAlertException("A new transportRoute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransportRouteDTO result = transportRouteService.save(transportRouteDTO);
        return ResponseEntity.created(new URI("/api/transport-routes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transport-routes} : Updates an existing transportRoute.
     *
     * @param transportRouteDTO the transportRouteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transportRouteDTO,
     * or with status {@code 400 (Bad Request)} if the transportRouteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transportRouteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transport-routes")
    public ResponseEntity<TransportRouteDTO> updateTransportRoute(@RequestBody TransportRouteDTO transportRouteDTO) throws URISyntaxException {
        log.debug("REST request to update TransportRoute : {}", transportRouteDTO);
        if (transportRouteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransportRouteDTO result = transportRouteService.save(transportRouteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transportRouteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transport-routes} : get all the transportRoutes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transportRoutes in body.
     */
    @GetMapping("/transport-routes")
    public List<TransportRouteDTO> getAllTransportRoutes() {
        log.debug("REST request to get all TransportRoutes");
        return transportRouteService.findAll();
    }

    /**
     * {@code GET  /transport-routes/:id} : get the "id" transportRoute.
     *
     * @param id the id of the transportRouteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transportRouteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transport-routes/{id}")
    public ResponseEntity<TransportRouteDTO> getTransportRoute(@PathVariable Long id) {
        log.debug("REST request to get TransportRoute : {}", id);
        Optional<TransportRouteDTO> transportRouteDTO = transportRouteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transportRouteDTO);
    }

    /**
     * {@code DELETE  /transport-routes/:id} : delete the "id" transportRoute.
     *
     * @param id the id of the transportRouteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transport-routes/{id}")
    public ResponseEntity<Void> deleteTransportRoute(@PathVariable Long id) {
        log.debug("REST request to delete TransportRoute : {}", id);
        transportRouteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
