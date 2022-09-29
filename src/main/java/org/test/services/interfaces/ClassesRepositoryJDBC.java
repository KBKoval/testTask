package org.test.services.interfaces;

import org.test.models.ClassesDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ClassesRepositoryJDBC {
    public Stream<ClassesDto> findAll();
    public void save(ClassesDto classesDto);
    public Optional<ClassesDto> findBy(String id);
    public int[] batchInsert(List<ClassesDto> classes);
}
