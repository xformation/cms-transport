package com.synectiks.transport.service.impl;

import com.synectiks.transport.service.TransportRouteService;
import com.synectiks.transport.domain.TransportRoute;
import com.synectiks.transport.repository.TransportRouteRepository;
import com.synectiks.transport.service.dto.TransportRouteDTO;
import com.synectiks.transport.service.mapper.TransportRouteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TransportRoute}.
 */
@Service
@Transactional
public class TransportRouteServiceImpl implements TransportRouteService {

    private final Logger log = LoggerFactory.getLogger(TransportRouteServiceImpl.class);

    private final TransportRouteRepository transportRouteRepository;

    private final TransportRouteMapper transportRouteMapper;

    public TransportRouteServiceImpl(TransportRouteRepository transportRouteRepository, TransportRouteMapper transportRouteMapper) {
        this.transportRouteRepository = transportRouteRepository;
        this.transportRouteMapper = transportRouteMapper;
    }

    /**
     * Save a transportRoute.
     *
     * @param transportRouteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransportRouteDTO save(TransportRouteDTO transportRouteDTO) {
        log.debug("Request to save TransportRoute : {}", transportRouteDTO);
        TransportRoute transportRoute = transportRouteMapper.toEntity(transportRouteDTO);
        transportRoute = transportRouteRepository.save(transportRoute);
        return transportRouteMapper.toDto(transportRoute);
    }

    /**
     * Get all the transportRoutes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransportRouteDTO> findAll() {
        log.debug("Request to get all TransportRoutes");
        return transportRouteRepository.findAll().stream()
            .map(transportRouteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one transportRoute by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransportRouteDTO> findOne(Long id) {
        log.debug("Request to get TransportRoute : {}", id);
        return transportRouteRepository.findById(id)
            .map(transportRouteMapper::toDto);
    }

    /**
     * Delete the transportRoute by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransportRoute : {}", id);
        transportRouteRepository.deleteById(id);
    }
}
