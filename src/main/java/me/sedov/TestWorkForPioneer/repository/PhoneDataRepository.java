package me.sedov.TestWorkForPioneer.repository;

import me.sedov.TestWorkForPioneer.model.PhoneData;
import me.sedov.TestWorkForPioneer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

    Optional<PhoneData> findByUser(User user);
}
