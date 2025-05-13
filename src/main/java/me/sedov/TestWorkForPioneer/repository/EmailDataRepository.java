package me.sedov.TestWorkForPioneer.repository;

import me.sedov.TestWorkForPioneer.model.EmailData;
import me.sedov.TestWorkForPioneer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

    Optional<Object> findByUser(User user);

    boolean existsByEmail(String newEmail);

    Optional<EmailData> findByEmail(String email);


    @Query("SELECT p.user.id FROM EmailData p WHERE LOWER(p.email) = LOWER(:email)")
    Long findUserIdByEmailIgnoreCase(@Param("email") String email);
}
