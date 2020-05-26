package com.synectiks.transport.service;

import com.synectiks.transport.service.dto.StopageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.transport.domain.Stopage}.
 */
public interface StopageService {

    /**
     * Save a stopage.
     *
     * @param stopageDTO the entity to save.
     * @return the persisted entity.
     */
    StopageDTO save(StopageDTO stopageDTO);

    /**
     * Get all the stopages.
     *
     * @return the list of entities.
     */
    List<StopageDTO> findAll();

    /**
     * Get the "id" stopage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StopageDTO> findOne(Long id);

    /**
     * Delete the "id" stopage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
