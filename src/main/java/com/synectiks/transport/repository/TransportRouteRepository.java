package com.synectiks.transport.repository;

import com.synectiks.transport.domain.TransportRoute;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransportRoute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportRouteRepository extends JPASearchRepository<TransportRoute, Long> {

}
