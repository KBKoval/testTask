package org.test.services.interfaces;

import org.test.models.FileInfoDto;
import org.test.models.SectionsDto;

import java.util.List;

public interface SectionsServiceJDBC {
    public List<SectionsDto> findAll();
    public void save(FileInfoDto fileInfo);
}
