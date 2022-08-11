package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Integer> {

    /**
     * finding role by id
     */

    Optional<RoleEntity> findByRoleId(int roleId);
}
