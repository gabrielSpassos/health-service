package com.poc.repository;

import com.poc.entity.RegistryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends JpaRepository<RegistryEntity, Long> {
}
