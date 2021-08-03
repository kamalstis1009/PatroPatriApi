package com.patropatri.api.repositories;

import com.patropatri.api.models.Authenticate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthenticateRepository extends JpaRepository<Authenticate, Long> {
}
