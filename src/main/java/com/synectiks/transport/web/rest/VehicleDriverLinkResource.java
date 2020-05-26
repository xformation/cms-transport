package com.synectiks.transport.web.rest;

import com.synectiks.transport.service.VehicleDriverLinkService;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.service.dto.VehicleDriverLinkDTO;

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
 * REST controller for managing {@link com.synectiks.transport.domain.VehicleDriverLink}.
 */
@RestController
@RequestMapping("/api")
public class VehicleDriverLinkResource {

    private final Logger log = LoggerFactory.getLogger(VehicleDriverLinkResource.class);

    private static final String ENTITY_NAME = "transportVehicleDriverLink";

    private String applicationName;

    private final VehicleDriverLinkService vehicleDriverLinkService;

    public VehicleDriverLinkResource(VehicleDriverLinkService vehicleDriverLinkService) {
        this.vehicleDriverLinkService = vehicleDriverLinkService;
    }

    /**
     * {@code POST  /vehicle-driver-links} : Create a new vehicleDriverLink.
     *
     * @param vehicleDriverLinkDTO the vehicleDriverLinkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehicleDriverLinkDTO, or with status {@code 400 (Bad Request)} if the vehicleDriverLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vehicle-driver-links")
    public ResponseEntity<VehicleDriverLinkDTO> createVehicleDriverLink(@RequestBody VehicleDriverLinkDTO vehicleDriverLinkDTO) throws URISyntaxException {
        log.debug("REST request to save VehicleDriverLink : {}", vehicleDriverLinkDTO);
        if (vehicleDriverLinkDTO.getId() != null) {
            throw new BadRequestAlertException("A new vehicleDriverLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleDriverLinkDTO result = vehicleDriverLinkService.save(vehicleDriverLinkDTO);
        return ResponseEntity.created(new URI("/api/vehicle-driver-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vehicle-driver-links} : Updates an existing vehicleDriverLink.
     *
     * @param vehicleDriverLinkDTO the vehicleDriverLinkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehicleDriverLinkDTO,
     * or with status {@code 400 (Bad Request)} if the vehicleDriverLinkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehicleDriverLinkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vehicle-driver-links")
    public ResponseEntity<VehicleDriverLinkDTO> updateVehicleDriverLink(@RequestBody VehicleDriverLinkDTO vehicleDriverLinkDTO) throws URISyntaxException {
        log.debug("REST request to update VehicleDriverLink : {}", vehicleDriverLinkDTO);
        if (vehicleDriverLinkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleDriverLinkDTO result = vehicleDriverLinkService.save(vehicleDriverLinkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehicleDriverLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vehicle-driver-links} : get all the vehicleDriverLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehicleDriverLinks in body.
     */
    @GetMapping("/vehicle-driver-links")
    public List<VehicleDriverLinkDTO> getAllVehicleDriverLinks() {
        log.debug("REST request to get all VehicleDriverLinks");
        return vehicleDriverLinkService.findAll();
    }

    /**
     * {@code GET  /vehicle-driver-links/:id} : get the "id" vehicleDriverLink.
     *
     * @param id the id of the vehicleDriverLinkDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehicleDriverLinkDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vehicle-driver-links/{id}")
    public ResponseEntity<VehicleDriverLinkDTO> getVehicleDriverLink(@PathVariable Long id) {
        log.debug("REST request to get VehicleDriverLink : {}", id);
        Optional<VehicleDriverLinkDTO> vehicleDriverLinkDTO = vehicleDriverLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehicleDriverLinkDTO);
    }

    /**
     * {@code DELETE  /vehicle-driver-links/:id} : delete the "id" vehicleDriverLink.
     *
     * @param id the id of the vehicleDriverLinkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vehicle-driver-links/{id}")
    public ResponseEntity<Void> deleteVehicleDriverLink(@PathVariable Long id) {
        log.debug("REST request to delete VehicleDriverLink : {}", id);
        vehicleDriverLinkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
