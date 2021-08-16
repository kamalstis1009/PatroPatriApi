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

    @Query(value = "SELECT id, full_name as fullName, gender, profession, district, summary, comment_amount as commentAmount, liked_amount as likedAmount, image_name as imageName, created_at as createdAt FROM users WHERE "
            + "is_active = true and "
            + "(:gender is null or gender = :gender and " //like %:inputString%)
            + "(:maritalStatus is null or marital_status = :maritalStatus and "
            + "(:religion is null or religion = :religion and "
            + "(:profession is null or profession = :profession and "
            + "(:fromYear is null or YEAR(birth_place) BETWEEN :fromYear AND :toYear and "
            + "(:district is null or district = :district", nativeQuery = true)
    List<ICandidate> getUsersByFilter(String gender, String maritalStatus, String religion, String profession, Integer fromYear, Integer toYear, String district, Pageable pageable);

}
