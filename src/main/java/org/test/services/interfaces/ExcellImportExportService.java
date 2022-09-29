package org.test.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import org.test.models.FileInfoDto;
import org.test.models.SectionsDto;

import java.io.IOException;
import java.util.List;

public interface ExcellImportExportService {
    public FileInfoDto readExcell(String pathLoadedFile) throws IOException;
}
