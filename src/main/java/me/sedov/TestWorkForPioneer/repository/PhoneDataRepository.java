package me.sedov.TestWorkForPioneer.repository;

import me.sedov.TestWorkForPioneer.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {


}
