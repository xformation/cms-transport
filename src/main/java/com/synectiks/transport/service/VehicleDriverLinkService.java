package com.synectiks.transport.service;

import com.synectiks.transport.service.dto.VehicleDriverLinkDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.transport.domain.VehicleDriverLink}.
 */
public interface VehicleDriverLinkService {

    /**
     * Save a vehicleDriverLink.
     *
     * @param vehicleDriverLinkDTO the entity to save.
     * @return the persisted entity.
     */
    VehicleDriverLinkDTO save(VehicleDriverLinkDTO vehicleDriverLinkDTO);

    /**
     * Get all the vehicleDriverLinks.
     *
     * @return the list of entities.
     */
    List<VehicleDriverLinkDTO> findAll();

    /**
     * Get the "id" vehicleDriverLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehicleDriverLinkDTO> findOne(Long id);

    /**
     * Delete the "id" vehicleDriverLink.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
