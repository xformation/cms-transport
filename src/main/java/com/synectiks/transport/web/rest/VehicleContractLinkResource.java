package com.synectiks.transport.web.rest;

import com.synectiks.transport.service.VehicleContractLinkService;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.service.dto.VehicleContractLinkDTO;


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
 * REST controller for managing {@link com.synectiks.transport.domain.VehicleContractLink}.
 */
@RestController
@RequestMapping("/api")
public class VehicleContractLinkResource {

    private final Logger log = LoggerFactory.getLogger(VehicleContractLinkResource.class);

    private static final String ENTITY_NAME = "transportVehicleContractLink";


    private String applicationName;

    private final VehicleContractLinkService vehicleContractLinkService;

    public VehicleContractLinkResource(VehicleContractLinkService vehicleContractLinkService) {
        this.vehicleContractLinkService = vehicleContractLinkService;
    }

    /**
     * {@code POST  /vehicle-contract-links} : Create a new vehicleContractLink.
     *
     * @param vehicleContractLinkDTO the vehicleContractLinkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehicleContractLinkDTO, or with status {@code 400 (Bad Request)} if the vehicleContractLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vehicle-contract-links")
    public ResponseEntity<VehicleContractLinkDTO> createVehicleContractLink(@RequestBody VehicleContractLinkDTO vehicleContractLinkDTO) throws URISyntaxException {
        log.debug("REST request to save VehicleContractLink : {}", vehicleContractLinkDTO);
        if (vehicleContractLinkDTO.getId() != null) {
            throw new BadRequestAlertException("A new vehicleContractLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VehicleContractLinkDTO result = vehicleContractLinkService.save(vehicleContractLinkDTO);
        return ResponseEntity.created(new URI("/api/vehicle-contract-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vehicle-contract-links} : Updates an existing vehicleContractLink.
     *
     * @param vehicleContractLinkDTO the vehicleContractLinkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehicleContractLinkDTO,
     * or with status {@code 400 (Bad Request)} if the vehicleContractLinkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehicleContractLinkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vehicle-contract-links")
    public ResponseEntity<VehicleContractLinkDTO> updateVehicleContractLink(@RequestBody VehicleContractLinkDTO vehicleContractLinkDTO) throws URISyntaxException {
        log.debug("REST request to update VehicleContractLink : {}", vehicleContractLinkDTO);
        if (vehicleContractLinkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VehicleContractLinkDTO result = vehicleContractLinkService.save(vehicleContractLinkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehicleContractLinkDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vehicle-contract-links} : get all the vehicleContractLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehicleContractLinks in body.
     */
    @GetMapping("/vehicle-contract-links")
    public List<VehicleContractLinkDTO> getAllVehicleContractLinks() {
        log.debug("REST request to get all VehicleContractLinks");
        return vehicleContractLinkService.findAll();
    }

    /**
     * {@code GET  /vehicle-contract-links/:id} : get the "id" vehicleContractLink.
     *
     * @param id the id of the vehicleContractLinkDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehicleContractLinkDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vehicle-contract-links/{id}")
    public ResponseEntity<VehicleContractLinkDTO> getVehicleContractLink(@PathVariable Long id) {
        log.debug("REST request to get VehicleContractLink : {}", id);
        Optional<VehicleContractLinkDTO> vehicleContractLinkDTO = vehicleContractLinkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vehicleContractLinkDTO);
    }

    /**
     * {@code DELETE  /vehicle-contract-links/:id} : delete the "id" vehicleContractLink.
     *
     * @param id the id of the vehicleContractLinkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vehicle-contract-links/{id}")
    public ResponseEntity<Void> deleteVehicleContractLink(@PathVariable Long id) {
        log.debug("REST request to delete VehicleContractLink : {}", id);
        vehicleContractLinkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
