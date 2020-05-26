package com.synectiks.transport.repository;

import com.synectiks.transport.domain.Insurance;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Insurance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceRepository extends JPASearchRepository<Insurance, Long> {

}
