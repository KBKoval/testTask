package org.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.test.domain.entity.ClassesEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<ClassesEntity, UUID> {
}

