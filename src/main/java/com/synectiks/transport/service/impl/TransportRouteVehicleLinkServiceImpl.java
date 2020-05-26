package com.synectiks.transport.service.impl;

import com.synectiks.transport.service.TransportRouteVehicleLinkService;
import com.synectiks.transport.domain.TransportRouteVehicleLink;
import com.synectiks.transport.repository.TransportRouteVehicleLinkRepository;
import com.synectiks.transport.service.dto.TransportRouteVehicleLinkDTO;
import com.synectiks.transport.service.mapper.TransportRouteVehicleLinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TransportRouteVehicleLink}.
 */
@Service
@Transactional
public class TransportRouteVehicleLinkServiceImpl implements TransportRouteVehicleLinkService {

    private final Logger log = LoggerFactory.getLogger(TransportRouteVehicleLinkServiceImpl.class);

    private final TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository;

    private final TransportRouteVehicleLinkMapper transportRouteVehicleLinkMapper;

    public TransportRouteVehicleLinkServiceImpl(TransportRouteVehicleLinkRepository transportRouteVehicleLinkRepository, TransportRouteVehicleLinkMapper transportRouteVehicleLinkMapper) {
        this.transportRouteVehicleLinkRepository = transportRouteVehicleLinkRepository;
        this.transportRouteVehicleLinkMapper = transportRouteVehicleLinkMapper;
    }

    /**
     * Save a transportRouteVehicleLink.
     *
     * @param transportRouteVehicleLinkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransportRouteVehicleLinkDTO save(TransportRouteVehicleLinkDTO transportRouteVehicleLinkDTO) {
        log.debug("Request to save TransportRouteVehicleLink : {}", transportRouteVehicleLinkDTO);
        TransportRouteVehicleLink transportRouteVehicleLink = transportRouteVehicleLinkMapper.toEntity(transportRouteVehicleLinkDTO);
        transportRouteVehicleLink = transportRouteVehicleLinkRepository.save(transportRouteVehicleLink);
        return transportRouteVehicleLinkMapper.toDto(transportRouteVehicleLink);
    }

    /**
     * Get all the transportRouteVehicleLinks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransportRouteVehicleLinkDTO> findAll() {
        log.debug("Request to get all TransportRouteVehicleLinks");
        return transportRouteVehicleLinkRepository.findAll().stream()
            .map(transportRouteVehicleLinkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one transportRouteVehicleLink by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransportRouteVehicleLinkDTO> findOne(Long id) {
        log.debug("Request to get TransportRouteVehicleLink : {}", id);
        return transportRouteVehicleLinkRepository.findById(id)
            .map(transportRouteVehicleLinkMapper::toDto);
    }

    /**
     * Delete the transportRouteVehicleLink by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransportRouteVehicleLink : {}", id);
        transportRouteVehicleLinkRepository.deleteById(id);
    }
}
