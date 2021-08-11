package com.patropatri.api.repositories;

import com.patropatri.api.models.Candidate;
import com.patropatri.api.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT id, full_name, gender, profession, district, summary, comment_amount, liked_amount, image_name, created_at FROM users WHERE is_active = true", nativeQuery = true)
    List<Candidate> getAllCandidates();

    @Query(value = "SELECT id, full_name, gender, profession, district, summary, comment_amount, liked_amount, image_name, created_at FROM users WHERE is_active = true", nativeQuery = true)
    List<Candidate> getAllCandidatesByPaginated(Pageable pageable);

}
