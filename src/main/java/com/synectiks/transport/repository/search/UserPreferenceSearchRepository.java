package com.synectiks.transport.repository.search;

import com.synectiks.transport.domain.UserPreference;
import com.synectiks.transport.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserPreference} entity.
 */
public interface UserPreferenceSearchRepository extends JPASearchRepository<UserPreference, Long> {
}
