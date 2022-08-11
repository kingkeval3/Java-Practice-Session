package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.ConfigurationModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends PagingAndSortingRepository<ConfigurationModel, Integer> {

    /**
     * fetching active configuration by type
     *
     * @param type type
     * @return configuration model
     */
    Optional<ConfigurationModel> findByTypeAndIsActiveTrue(String type);
}
