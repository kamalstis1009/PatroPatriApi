package com.patropatri.api.repositories;

import com.patropatri.api.models.ICandidate;
import com.patropatri.api.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT id, full_name as fullName, gender, profession, home_district as district, summary, comment_amount as commentAmount, liked_amount as likedAmount, viewer_amount as viewerAmount, image_name as imageName, image_cover_name as imageCoverName, created_at as createdAt FROM users WHERE is_active = true AND is_approved = true", nativeQuery = true)
    List<ICandidate> getAllCandidates();

    @Query(value = "SELECT id, full_name as fullName, gender, profession, home_district as district, summary, comment_amount as commentAmount, liked_amount as likedAmount, viewer_amount as viewerAmount, image_name as imageName, image_cover_name as imageCoverName, created_at as createdAt FROM users WHERE is_active = true AND is_approved = true", nativeQuery = true)
    List<ICandidate> getAllCandidatesByPaginated(Pageable pageable);

    @Query(value = "SELECT u.id, u.full_name as fullName, u.gender, u.profession, u.home_district as district, u.summary, u.comment_amount as commentAmount, u.liked_amount as likedAmount, u.viewer_amount as viewerAmount, u.image_name as imageName, u.image_cover_name as imageCoverName, u.created_at as createdAt FROM users as u WHERE "
            + "u.is_active = true AND u.is_approved = true AND "
            + "(:gender IS NULL OR u.gender =:gender) AND " //like %:inputString%)
            + "(:maritalStatus IS NULL OR u.marital_status =:maritalStatus) AND "
            + "(:religion IS NULL OR u.religion =:religion) AND "
            + "(:profession IS NULL OR u.profession =:profession) AND "
            + "(:fromYear IS NULL OR YEAR(u.birth_date) BETWEEN :fromYear AND :toYear) AND "
            + "(table.value = :value OR :value IS NULL)(:district IS NULL OR u.home_district =:district)", nativeQuery = true)
    List<ICandidate> getUsersByFilter(@Param("gender") String gender, @Param("maritalStatus") String maritalStatus, @Param("religion") String religion, @Param("profession") String profession, @Param("fromYear") Integer fromYear, @Param("toYear") Integer toYear, @Param("district") String district, Pageable pageable);

}
