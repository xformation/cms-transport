package com.synectiks.transport.repository;

import com.synectiks.transport.domain.TransportRouteVehicleLink;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TransportRouteVehicleLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportRouteVehicleLinkRepository extends JPASearchRepository<TransportRouteVehicleLink, Long> {

}
