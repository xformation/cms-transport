package com.synectiks.transport.repository;

import com.synectiks.transport.domain.TransportRouteStopageLink;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransportRouteStopageLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportRouteStopageLinkRepository extends JPASearchRepository<TransportRouteStopageLink, Long> {

}
