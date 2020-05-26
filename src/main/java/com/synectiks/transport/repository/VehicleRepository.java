package com.synectiks.transport.repository;

import com.synectiks.transport.domain.Vehicle;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Vehicle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleRepository extends JPASearchRepository<Vehicle, Long> {

}
