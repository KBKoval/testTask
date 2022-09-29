package org.test.services.interfaces;

import org.test.models.FileInfoDto;
import org.test.models.SectionsDto;

import java.util.Optional;
import java.util.stream.Stream;

public interface SectionsRepositoryJDBC {
    public Stream<SectionsDto> findAll();
    public void save(SectionsDto section);
    public Optional<SectionsDto> findBy(String id);
}
