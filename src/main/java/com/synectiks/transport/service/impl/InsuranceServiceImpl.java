package com.synectiks.transport.service.impl;

import com.synectiks.transport.service.InsuranceService;
import com.synectiks.transport.domain.Insurance;
import com.synectiks.transport.repository.InsuranceRepository;
import com.synectiks.transport.service.dto.InsuranceDTO;
import com.synectiks.transport.service.mapper.InsuranceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Insurance}.
 */
@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    private final Logger log = LoggerFactory.getLogger(InsuranceServiceImpl.class);

    private final InsuranceRepository insuranceRepository;

    private final InsuranceMapper insuranceMapper;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, InsuranceMapper insuranceMapper) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceMapper = insuranceMapper;
    }

    /**
     * Save a insurance.
     *
     * @param insuranceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InsuranceDTO save(InsuranceDTO insuranceDTO) {
        log.debug("Request to save Insurance : {}", insuranceDTO);
        Insurance insurance = insuranceMapper.toEntity(insuranceDTO);
        insurance = insuranceRepository.save(insurance);
        return insuranceMapper.toDto(insurance);
    }

    /**
     * Get all the insurances.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InsuranceDTO> findAll() {
        log.debug("Request to get all Insurances");
        return insuranceRepository.findAll().stream()
            .map(insuranceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one insurance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceDTO> findOne(Long id) {
        log.debug("Request to get Insurance : {}", id);
        return insuranceRepository.findById(id)
            .map(insuranceMapper::toDto);
    }

    /**
     * Delete the insurance by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Insurance : {}", id);
        insuranceRepository.deleteById(id);
    }
}
