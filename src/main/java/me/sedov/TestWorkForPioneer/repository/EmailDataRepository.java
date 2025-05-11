package me.sedov.TestWorkForPioneer.repository;

import me.sedov.TestWorkForPioneer.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

}
