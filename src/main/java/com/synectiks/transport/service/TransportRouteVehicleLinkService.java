package com.synectiks.transport.service;

import com.synectiks.transport.service.dto.TransportRouteVehicleLinkDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.transport.domain.TransportRouteVehicleLink}.
 */
public interface TransportRouteVehicleLinkService {

    /**
     * Save a transportRouteVehicleLink.
     *
     * @param transportRouteVehicleLinkDTO the entity to save.
     * @return the persisted entity.
     */
    TransportRouteVehicleLinkDTO save(TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO);

    /**
     * Get all the transportRouteVehicleLinks.
     *
     * @return the list of entities.
     */
    List<TransportRouteVehicleLinkDTO> findAll();

    /**
     * Get the "id" transportRouteVehicleLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransportRouteVehicleLinkDTO> findOne(Long id);

    /**
     * Delete the "id" transportRouteVehicleLink.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
