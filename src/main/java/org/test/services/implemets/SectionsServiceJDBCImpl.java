package org.test.services.implemets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.models.FileInfoDto;
import org.test.models.SectionsDto;
import org.test.services.interfaces.SectionsRepositoryJDBC;
import org.test.services.interfaces.SectionsServiceJDBC;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SectionsServiceJDBCImpl implements SectionsServiceJDBC {
    private final SectionsRepositoryJDBC repository;

    @Autowired
    public SectionsServiceJDBCImpl(SectionsRepositoryJDBC repository) {
        this.repository = repository;
    }

    public List<SectionsDto> findAll() {
        Stream<SectionsDto> stream = repository.findAll();
        return stream.collect(Collectors.toList());
    }

    public void save(FileInfoDto fileInfo) {
        List<SectionsDto> sections = fileInfo.getSections();
        sections.parallelStream().forEach(section -> section.setIdFile(fileInfo.getId()));
        sections.parallelStream().forEach(section -> repository.save(section)); //TODO пакетный insert ?
    }
}
