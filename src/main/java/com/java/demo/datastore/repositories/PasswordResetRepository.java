package com.java.demo.datastore.repositories;

import com.java.demo.datastore.models.PasswordResetToken;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PasswordResetRepository extends PagingAndSortingRepository<PasswordResetToken, Integer> {

    Optional<PasswordResetToken> findByResetToken(String token);
}
