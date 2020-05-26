package com.synectiks.transport.repository;

import com.synectiks.transport.domain.Contract;

import com.synectiks.transport.utils.JPASearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Contract entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractRepository extends JPASearchRepository<Contract, Long> {

}
