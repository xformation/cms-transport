package com.synectiks.transport.web.rest;

import com.synectiks.transport.service.StopageService;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.service.dto.StopageDTO;

import com.synectiks.transport.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.synectiks.transport.domain.Stopage}.
 */
@RestController
@RequestMapping("/api")
public class StopageResource {

    private final Logger log = LoggerFactory.getLogger(StopageResource.class);

    private static final String ENTITY_NAME = "transportStopage";

    private String applicationName;

    private final StopageService stopageService;

    public StopageResource(StopageService stopageService) {
        this.stopageService = stopageService;
    }

    /**
     * {@code POST  /stopages} : Create a new stopage.
     *
     * @param stopageDTO the stopageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stopageDTO, or with status {@code 400 (Bad Request)} if the stopage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/stopages")
    public ResponseEntity<StopageDTO> createStopage(@RequestBody StopageDTO stopageDTO) throws URISyntaxException {
        log.debug("REST request to save Stopage : {}", stopageDTO);
        if (stopageDTO.getId() != null) {
            throw new BadRequestAlertException("A new stopage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StopageDTO result = stopageService.save(stopageDTO);
        return ResponseEntity.created(new URI("/api/stopages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /stopages} : Updates an existing stopage.
     *
     * @param stopageDTO the stopageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stopageDTO,
     * or with status {@code 400 (Bad Request)} if the stopageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stopageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/stopages")
    public ResponseEntity<StopageDTO> updateStopage(@RequestBody StopageDTO stopageDTO) throws URISyntaxException {
        log.debug("REST request to update Stopage : {}", stopageDTO);
        if (stopageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StopageDTO result = stopageService.save(stopageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stopageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /stopages} : get all the stopages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stopages in body.
     */
    @GetMapping("/stopages")
    public List<StopageDTO> getAllStopages() {
        log.debug("REST request to get all Stopages");
        return stopageService.findAll();
    }

    /**
     * {@code GET  /stopages/:id} : get the "id" stopage.
     *
     * @param id the id of the stopageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stopageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/stopages/{id}")
    public ResponseEntity<StopageDTO> getStopage(@PathVariable Long id) {
        log.debug("REST request to get Stopage : {}", id);
        Optional<StopageDTO> stopageDTO = stopageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stopageDTO);
    }

    /**
     * {@code DELETE  /stopages/:id} : delete the "id" stopage.
     *
     * @param id the id of the stopageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/stopages/{id}")
    public ResponseEntity<Void> deleteStopage(@PathVariable Long id) {
        log.debug("REST request to delete Stopage : {}", id);
        stopageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
