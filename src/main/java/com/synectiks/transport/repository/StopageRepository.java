package com.synectiks.transport.repository;

import com.synectiks.transport.domain.Stopage;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Stopage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StopageRepository extends JPASearchRepository<Stopage, Long> {

}
