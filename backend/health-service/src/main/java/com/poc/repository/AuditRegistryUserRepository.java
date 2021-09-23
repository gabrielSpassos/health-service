package com.poc.repository;

import com.poc.entity.AuditRegistryUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRegistryUserRepository extends JpaRepository<AuditRegistryUserEntity, Long> {

    Page<AuditRegistryUserEntity> findByRegistryId(Long registryId, Pageable page);

}
