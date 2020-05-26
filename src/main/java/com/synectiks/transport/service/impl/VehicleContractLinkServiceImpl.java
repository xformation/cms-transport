package com.synectiks.transport.service.impl;

import com.synectiks.transport.service.VehicleContractLinkService;
import com.synectiks.transport.domain.VehicleContractLink;
import com.synectiks.transport.repository.VehicleContractLinkRepository;
import com.synectiks.transport.service.dto.VehicleContractLinkDTO;
import com.synectiks.transport.service.mapper.VehicleContractLinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link VehicleContractLink}.
 */
@Service
@Transactional
public class VehicleContractLinkServiceImpl implements VehicleContractLinkService {

    private final Logger log = LoggerFactory.getLogger(VehicleContractLinkServiceImpl.class);

    private final VehicleContractLinkRepository vehicleContractLinkRepository;

    private final VehicleContractLinkMapper vehicleContractLinkMapper;

    public VehicleContractLinkServiceImpl(VehicleContractLinkRepository vehicleContractLinkRepository, VehicleContractLinkMapper vehicleContractLinkMapper) {
        this.vehicleContractLinkRepository = vehicleContractLinkRepository;
        this.vehicleContractLinkMapper = vehicleContractLinkMapper;
    }

    /**
     * Save a vehicleContractLink.
     *
     * @param vehicleContractLinkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VehicleContractLinkDTO save(VehicleContractLinkDTO vehicleContractLinkDTO) {
        log.debug("Request to save VehicleContractLink : {}", vehicleContractLinkDTO);
        VehicleContractLink vehicleContractLink = vehicleContractLinkMapper.toEntity(vehicleContractLinkDTO);
        vehicleContractLink = vehicleContractLinkRepository.save(vehicleContractLink);
        return vehicleContractLinkMapper.toDto(vehicleContractLink);
    }

    /**
     * Get all the vehicleContractLinks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<VehicleContractLinkDTO> findAll() {
        log.debug("Request to get all VehicleContractLinks");
        return vehicleContractLinkRepository.findAll().stream()
            .map(vehicleContractLinkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one vehicleContractLink by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleContractLinkDTO> findOne(Long id) {
        log.debug("Request to get VehicleContractLink : {}", id);
        return vehicleContractLinkRepository.findById(id)
            .map(vehicleContractLinkMapper::toDto);
    }

    /**
     * Delete the vehicleContractLink by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VehicleContractLink : {}", id);
        vehicleContractLinkRepository.deleteById(id);
    }
}
