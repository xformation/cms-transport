package com.synectiks.transport.service.impl;

import com.synectiks.transport.service.VehicleDriverLinkService;
import com.synectiks.transport.domain.VehicleDriverLink;
import com.synectiks.transport.repository.VehicleDriverLinkRepository;
import com.synectiks.transport.service.dto.VehicleDriverLinkDTO;
import com.synectiks.transport.service.mapper.VehicleDriverLinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link VehicleDriverLink}.
 */
@Service
@Transactional
public class VehicleDriverLinkServiceImpl implements VehicleDriverLinkService {

    private final Logger log = LoggerFactory.getLogger(VehicleDriverLinkServiceImpl.class);

    private final VehicleDriverLinkRepository vehicleDriverLinkRepository;

    private final VehicleDriverLinkMapper vehicleDriverLinkMapper;

    public VehicleDriverLinkServiceImpl(VehicleDriverLinkRepository vehicleDriverLinkRepository, VehicleDriverLinkMapper vehicleDriverLinkMapper) {
        this.vehicleDriverLinkRepository = vehicleDriverLinkRepository;
        this.vehicleDriverLinkMapper = vehicleDriverLinkMapper;
    }

    /**
     * Save a vehicleDriverLink.
     *
     * @param vehicleDriverLinkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VehicleDriverLinkDTO save(VehicleDriverLinkDTO vehicleDriverLinkDTO) {
        log.debug("Request to save VehicleDriverLink : {}", vehicleDriverLinkDTO);
        VehicleDriverLink vehicleDriverLink = vehicleDriverLinkMapper.toEntity(vehicleDriverLinkDTO);
        vehicleDriverLink = vehicleDriverLinkRepository.save(vehicleDriverLink);
        return vehicleDriverLinkMapper.toDto(vehicleDriverLink);
    }

    /**
     * Get all the vehicleDriverLinks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<VehicleDriverLinkDTO> findAll() {
        log.debug("Request to get all VehicleDriverLinks");
        return vehicleDriverLinkRepository.findAll().stream()
            .map(vehicleDriverLinkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one vehicleDriverLink by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleDriverLinkDTO> findOne(Long id) {
        log.debug("Request to get VehicleDriverLink : {}", id);
        return vehicleDriverLinkRepository.findById(id)
            .map(vehicleDriverLinkMapper::toDto);
    }

    /**
     * Delete the vehicleDriverLink by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VehicleDriverLink : {}", id);
        vehicleDriverLinkRepository.deleteById(id);
    }
}
