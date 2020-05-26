package com.synectiks.transport.service;

import com.synectiks.transport.service.dto.VehicleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.transport.domain.Vehicle}.
 */
public interface VehicleService {

    /**
     * Save a vehicle.
     *
     * @param vehicleDTO the entity to save.
     * @return the persisted entity.
     */
    VehicleDTO save(VehicleDTO vehicleDTO);

    /**
     * Get all the vehicles.
     *
     * @return the list of entities.
     */
    List<VehicleDTO> findAll();
    /**
     * Get all the VehicleDTO where Insurance is {@code null}.
     *
     * @return the list of entities.
     */
    List<VehicleDTO> findAllWhereInsuranceIsNull();

    /**
     * Get the "id" vehicle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VehicleDTO> findOne(Long id);

    /**
     * Delete the "id" vehicle.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
