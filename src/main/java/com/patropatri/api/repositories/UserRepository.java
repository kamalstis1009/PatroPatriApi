package com.patropatri.api.repositories;

import com.patropatri.api.models.ICandidate;
import com.patropatri.api.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT id, full_name as fullName, gender, profession, district, summary, comment_amount as commentAmount, liked_amount as likedAmount, image_name as imageName, created_at as createdAt FROM users WHERE is_active = true", nativeQuery = true)
    List<ICandidate> getAllCandidates();

    @Query(value = "SELECT id, full_name as fullName, gender, profession, district, summary, comment_amount as commentAmount, liked_amount as likedAmount, image_name as imageName, created_at as createdAt FROM users WHERE is_active = true", nativeQuery = true)
    List<ICandidate> getAllCandidatesByPaginated(Pageable pageable);

    @Query(value = "SELECT e.id, e.full_name as fullName, e.gender, e.profession, e.district, e.summary, e.comment_amount as commentAmount, e.liked_amount as likedAmount, e.image_name as imageName, e.created_at as createdAt FROM users as e WHERE "
            + "(:inputString is null or e.gender like %:inputString% ) and "
            + "(:inputString is null or e.marital_status like %:inputString%) and "
            + "(:inputString is null or e.religion like %:inputString%) and "
            + "(:inputString is null or e.profession like %:inputString%) and "
            //+ "(:inputString is null or YEAR(e.birth_place) BETWEEN 10 AND 20 and "
            + "(:inputString is null or e.district like %:inputString%)", nativeQuery = true)
    List<ICandidate> findAllByInputString(String inputString, Pageable pageable);

}
