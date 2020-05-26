package com.synectiks.transport.service;

import com.synectiks.transport.service.dto.VehicleContractLinkDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.transport.domain.VehicleContractLink}.
 */
public interface VehicleContractLinkService {

    /**
     * Save a vehicleContractLink.
     *
     * @param vehicleContractLinkDTO the entity to save.
     * @return the persisted entity.
     */
    VehicleContractLinkDTO save(VehicleContractLinkDTO vehicleContractLinkDTO);

    /**
     * Get all the vehicleContractLinks.
     *
     * @return the list of entities.
     */
    List<VehicleContractLinkDTO> findAll();

    /**
     * Get the "id" vehicleContractLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehicleContractLinkDTO> findOne(Long id);

    /**
     * Delete the "id" vehicleContractLink.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
