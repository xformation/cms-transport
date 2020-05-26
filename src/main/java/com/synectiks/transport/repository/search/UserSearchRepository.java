package com.synectiks.transport.repository.search;

import com.synectiks.transport.domain.User;
import com.synectiks.transport.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends JPASearchRepository<User, Long> {
}
