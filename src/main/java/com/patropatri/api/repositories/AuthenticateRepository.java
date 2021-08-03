package com.patropatri.api.repositories;

import com.patropatri.api.models.Authenticate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthenticateRepository extends JpaRepository<Authenticate, Long> {

    @Query("SELECT a FROM Authenticate a WHERE a.username = :username and a.password = :password")
    Authenticate getAuthenticateByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT a FROM Authenticate a WHERE a.username = :username")
    Authenticate getAuthenticateByUsername(@Param("username") String username);

}
