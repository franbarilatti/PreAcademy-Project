package com.eventapp.auth_service.repository;


import com.eventapp.auth_service.model.InvalidateToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidateToken, Long> {

    Optional<InvalidateToken> findByToken(String token);

}
