package com.synectiks.transport.service.impl;

import com.synectiks.transport.service.StopageService;
import com.synectiks.transport.domain.Stopage;
import com.synectiks.transport.repository.StopageRepository;
import com.synectiks.transport.service.dto.StopageDTO;
import com.synectiks.transport.service.mapper.StopageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Stopage}.
 */
@Service
@Transactional
public class StopageServiceImpl implements StopageService {

    private final Logger log = LoggerFactory.getLogger(StopageServiceImpl.class);

    private final StopageRepository stopageRepository;

    private final StopageMapper stopageMapper;

    public StopageServiceImpl(StopageRepository stopageRepository, StopageMapper stopageMapper) {
        this.stopageRepository = stopageRepository;
        this.stopageMapper = stopageMapper;
    }

    /**
     * Save a stopage.
     *
     * @param stopageDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StopageDTO save(StopageDTO stopageDTO) {
        log.debug("Request to save Stopage : {}", stopageDTO);
        Stopage stopage = stopageMapper.toEntity(stopageDTO);
        stopage = stopageRepository.save(stopage);
        return stopageMapper.toDto(stopage);
    }

    /**
     * Get all the stopages.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StopageDTO> findAll() {
        log.debug("Request to get all Stopages");
        return stopageRepository.findAll().stream()
            .map(stopageMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one stopage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StopageDTO> findOne(Long id) {
        log.debug("Request to get Stopage : {}", id);
        return stopageRepository.findById(id)
            .map(stopageMapper::toDto);
    }

    /**
     * Delete the stopage by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Stopage : {}", id);
        stopageRepository.deleteById(id);
    }
}
