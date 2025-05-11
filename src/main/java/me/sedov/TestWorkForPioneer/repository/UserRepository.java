package me.sedov.TestWorkForPioneer.repository;

import me.sedov.TestWorkForPioneer.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNameContainingIgnoreCase(String name);

    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR u.name LIKE %:name%) " +
            "AND (:email IS NULL OR EXISTS (SELECT 1 FROM u.emailDataList e WHERE e.email LIKE %:email%)) " +
            "AND (:phone IS NULL OR EXISTS (SELECT 1 FROM u.phoneDataList p WHERE p.phone LIKE %:phone%)) " +
            "AND (:dateOfBirth IS NULL OR u.dateOfBirth = :dateOfBirth)")
    List<User> searchUser(@Param("name") String name,
                          @Param("email") String email,
                          @Param("phone") String phone,
                          @Param("dateOfBirth") LocalDateTime dateOfBirth);


    Optional<User> findById(Long userId);
}
