package com.poc.repository;

import com.poc.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    PatientEntity findByCpfOrRg(String cpf, String rg);
}
