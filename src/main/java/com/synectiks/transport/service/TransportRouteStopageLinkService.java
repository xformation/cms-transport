package com.synectiks.transport.service;

import com.synectiks.transport.service.dto.TransportRouteStopageLinkDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.transport.domain.TransportRouteStopageLink}.
 */
public interface TransportRouteStopageLinkService {

    /**
     * Save a transportRouteStopageLink.
     *
     * @param transportRouteStopageLinkDTO the entity to save.
     * @return the persisted entity.
     */
    TransportRouteStopageLinkDTO save(TransportRouteStopageLinkDTO transportRouteStopageLinkDTO);

    /**
     * Get all the transportRouteStopageLinks.
     *
     * @return the list of entities.
     */
    List<TransportRouteStopageLinkDTO> findAll();

    /**
     * Get the "id" transportRouteStopageLink.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransportRouteStopageLinkDTO> findOne(Long id);

    /**
     * Delete the "id" transportRouteStopageLink.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
