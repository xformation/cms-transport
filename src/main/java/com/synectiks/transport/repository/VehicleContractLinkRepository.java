package com.synectiks.transport.repository;

import com.synectiks.transport.domain.VehicleContractLink;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VehicleContractLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleContractLinkRepository extends JPASearchRepository<VehicleContractLink, Long> {

}
