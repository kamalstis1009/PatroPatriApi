package com.patropatri.api.repositories;

import com.patropatri.api.models.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    @Query("SELECT a FROM Authentication a WHERE a.username = :username and a.password = :password")
    Authentication getAuthenticateByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT a FROM Authentication a WHERE a.username = :username")
    Authentication getAuthenticateByUsername(@Param("username") String username);

}
