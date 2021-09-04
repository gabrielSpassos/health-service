package com.poc.repository;

import com.poc.entity.MedicalRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Long> {

    MedicalRecordEntity findByPatientId(Long patientId);

}
