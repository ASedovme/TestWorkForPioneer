package me.sedov.TestWorkForPioneer.repository;

import me.sedov.TestWorkForPioneer.model.EmailData;
import me.sedov.TestWorkForPioneer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

    Optional<Object> findByUser(User user);

    boolean existsByEmail(String newEmail);
}
