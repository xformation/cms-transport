package com.synectiks.transport.repository;

import com.synectiks.transport.domain.VehicleDriverLink;

import com.synectiks.transport.utils.JPASearchRepository;

import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VehicleDriverLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleDriverLinkRepository extends JPASearchRepository<VehicleDriverLink, Long> {

}
