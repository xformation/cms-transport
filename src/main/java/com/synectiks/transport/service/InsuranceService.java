package com.synectiks.transport.service;

import com.synectiks.transport.service.dto.InsuranceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.transport.domain.Insurance}.
 */
public interface InsuranceService {

    /**
     * Save a insurance.
     *
     * @param insuranceDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceDTO save(InsuranceDTO insuranceDTO);

    /**
     * Get all the insurances.
     *
     * @return the list of entities.
     */
    List<InsuranceDTO> findAll();

    /**
     * Get the "id" insurance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceDTO> findOne(Long id);

    /**
     * Delete the "id" insurance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
