package me.sedov.TestWorkForPioneer.repository;

import me.sedov.TestWorkForPioneer.model.PhoneData;
import me.sedov.TestWorkForPioneer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

    Optional<PhoneData> findByUser(User user);

    boolean existsByPhone(String newPhone);

    List<User> findByPhoneContainingIgnoreCase(String phone);

    @Query("SELECT p.user.id FROM PhoneData p WHERE p.phone = :phone")
    Long findUserIdByPhone(@Param("phone") String phone);
}
