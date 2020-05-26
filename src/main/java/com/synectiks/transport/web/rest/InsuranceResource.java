package com.synectiks.transport.web.rest;

import com.synectiks.transport.service.InsuranceService;
import com.synectiks.transport.web.rest.errors.BadRequestAlertException;
import com.synectiks.transport.service.dto.InsuranceDTO;

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
 * REST controller for managing {@link com.synectiks.transport.domain.Insurance}.
 */
@RestController
@RequestMapping("/api")
public class InsuranceResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceResource.class);

    private static final String ENTITY_NAME = "transportInsurance";

    private String applicationName;

    private final InsuranceService insuranceService;

    public InsuranceResource(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    /**
     * {@code POST  /insurances} : Create a new insurance.
     *
     * @param insuranceDTO the insuranceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceDTO, or with status {@code 400 (Bad Request)} if the insurance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/insurances")
    public ResponseEntity<InsuranceDTO> createInsurance(@RequestBody InsuranceDTO insuranceDTO) throws URISyntaxException {
        log.debug("REST request to save Insurance : {}", insuranceDTO);
        if (insuranceDTO.getId() != null) {
            throw new BadRequestAlertException("A new insurance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InsuranceDTO result = insuranceService.save(insuranceDTO);
        return ResponseEntity.created(new URI("/api/insurances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /insurances} : Updates an existing insurance.
     *
     * @param insuranceDTO the insuranceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/insurances")
    public ResponseEntity<InsuranceDTO> updateInsurance(@RequestBody InsuranceDTO insuranceDTO) throws URISyntaxException {
        log.debug("REST request to update Insurance : {}", insuranceDTO);
        if (insuranceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InsuranceDTO result = insuranceService.save(insuranceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, insuranceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /insurances} : get all the insurances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insurances in body.
     */
    @GetMapping("/insurances")
    public List<InsuranceDTO> getAllInsurances() {
        log.debug("REST request to get all Insurances");
        return insuranceService.findAll();
    }

    /**
     * {@code GET  /insurances/:id} : get the "id" insurance.
     *
     * @param id the id of the insuranceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/insurances/{id}")
    public ResponseEntity<InsuranceDTO> getInsurance(@PathVariable Long id) {
        log.debug("REST request to get Insurance : {}", id);
        Optional<InsuranceDTO> insuranceDTO = insuranceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceDTO);
    }

    /**
     * {@code DELETE  /insurances/:id} : delete the "id" insurance.
     *
     * @param id the id of the insuranceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/insurances/{id}")
    public ResponseEntity<Void> deleteInsurance(@PathVariable Long id) {
        log.debug("REST request to delete Insurance : {}", id);
        insuranceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
