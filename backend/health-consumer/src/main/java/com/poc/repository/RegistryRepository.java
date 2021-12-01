package com.poc.repository;

import com.poc.entity.RegistryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends CrudRepository<RegistryEntity, String> {
}
