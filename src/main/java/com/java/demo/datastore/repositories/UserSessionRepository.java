package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.UserSessionModel;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSessionRepository extends PagingAndSortingRepository<UserSessionModel, Integer> {
    /**
     * getting user session token details
     */
    Optional<UserSessionModel> findByUserId(int id);

    /**
     * getting the session details based on json token
     */
    Optional<UserSessionModel> findByJsonToken(String header);

    /**
     * getting user session details using user code
     */
    Optional<UserSessionModel> findByUserCode(String token);
}
