package com.synectiks.transport.service.impl;

import com.synectiks.transport.service.TransportRouteStopageLinkService;
import com.synectiks.transport.domain.TransportRouteStopageLink;
import com.synectiks.transport.repository.TransportRouteStopageLinkRepository;
import com.synectiks.transport.service.dto.TransportRouteStopageLinkDTO;
import com.synectiks.transport.service.mapper.TransportRouteStopageLinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TransportRouteStopageLink}.
 */
@Service
@Transactional
public class TransportRouteStopageLinkServiceImpl implements TransportRouteStopageLinkService {

    private final Logger log = LoggerFactory.getLogger(TransportRouteStopageLinkServiceImpl.class);

    private final TransportRouteStopageLinkRepository transportRouteStopageLinkRepository;

    private final TransportRouteStopageLinkMapper transportRouteStopageLinkMapper;

    public TransportRouteStopageLinkServiceImpl(TransportRouteStopageLinkRepository transportRouteStopageLinkRepository, TransportRouteStopageLinkMapper transportRouteStopageLinkMapper) {
        this.transportRouteStopageLinkRepository = transportRouteStopageLinkRepository;
        this.transportRouteStopageLinkMapper = transportRouteStopageLinkMapper;
    }

    /**
     * Save a transportRouteStopageLink.
     *
     * @param transportRouteStopageLinkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TransportRouteStopageLinkDTO save(TransportRouteStopageLinkDTO transportRouteStopageLinkDTO) {
        log.debug("Request to save TransportRouteStopageLink : {}", transportRouteStopageLinkDTO);
        TransportRouteStopageLink transportRouteStopageLink = transportRouteStopageLinkMapper.toEntity(transportRouteStopageLinkDTO);
        transportRouteStopageLink = transportRouteStopageLinkRepository.save(transportRouteStopageLink);
        return transportRouteStopageLinkMapper.toDto(transportRouteStopageLink);
    }

    /**
     * Get all the transportRouteStopageLinks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransportRouteStopageLinkDTO> findAll() {
        log.debug("Request to get all TransportRouteStopageLinks");
        return transportRouteStopageLinkRepository.findAll().stream()
            .map(transportRouteStopageLinkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one transportRouteStopageLink by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransportRouteStopageLinkDTO> findOne(Long id) {
        log.debug("Request to get TransportRouteStopageLink : {}", id);
        return transportRouteStopageLinkRepository.findById(id)
            .map(transportRouteStopageLinkMapper::toDto);
    }

    /**
     * Delete the transportRouteStopageLink by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransportRouteStopageLink : {}", id);
        transportRouteStopageLinkRepository.deleteById(id);
    }
}
