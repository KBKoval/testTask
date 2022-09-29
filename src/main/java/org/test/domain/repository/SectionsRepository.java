package org.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.domain.entity.SectionsEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionsRepository extends JpaRepository<SectionsEntity, UUID> {
}
