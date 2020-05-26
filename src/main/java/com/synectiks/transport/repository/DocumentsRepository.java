package com.synectiks.transport.repository;

import com.synectiks.transport.domain.Documents;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Documents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentsRepository extends JPASearchRepository<Documents, Long> {

}
