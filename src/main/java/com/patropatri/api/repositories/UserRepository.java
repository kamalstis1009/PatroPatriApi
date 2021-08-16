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

    @Query(value = "SELECT id, full_name as fullName, gender, profession, district, summary, comment_amount as commentAmount, liked_amount as likedAmount, image_name as imageName, created_at as createdAt FROM users WHERE is_active = true", nativeQuery = true)
    List<ICandidate> getAllCandidates();

    @Query(value = "SELECT id, full_name as fullName, gender, profession, district, summary, comment_amount as commentAmount, liked_amount as likedAmount, image_name as imageName, created_at as createdAt FROM users WHERE is_active = true", nativeQuery = true)
    List<ICandidate> getAllCandidatesByPaginated(Pageable pageable);

    @Query(value = "SELECT u.id, u.full_name as fullName, u.gender, u.profession, u.district, u.summary, u.comment_amount as commentAmount, u.liked_amount as likedAmount, u.image_name as imageName, u.created_at as createdAt FROM users as u WHERE "
            + "u.is_active = true and "
            + "u.gender =:gender and " //like %:inputString%)
            + "u.marital_status =:maritalStatus and "
            + "u.religion =:religion and "
            + "u.profession =:profession and "
            + "YEAR(u.birth_date) BETWEEN :fromYear AND :toYear and "
            + "u.district =:district", nativeQuery = true)
    List<ICandidate> getUsersByFilter(@Param("gender") String gender, @Param("maritalStatus") String maritalStatus, @Param("religion") String religion, @Param("profession") String profession, @Param("fromYear") Integer fromYear, @Param("toYear") Integer toYear, @Param("district") String district, Pageable pageable);

}
