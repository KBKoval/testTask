package org.test.writer;


import org.springframework.batch.item.ItemWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.test.models.ClassesDto;
import org.test.models.FileInfoDto;
import org.test.models.SectionsDto;
import org.test.services.interfaces.ClassesRepositoryJDBC;
import org.test.services.interfaces.SectionsServiceJDBC;
import org.test.services.interfaces.StoreRepository;

import java.util.ArrayList;
import java.util.List;

@Component("writer-reader")
@Scope("step")
public class ExcelWriter implements ItemWriter<FileInfoDto> {

     private  SectionsServiceJDBC service;
    private  StoreRepository storeRepository;
    private ClassesRepositoryJDBC classesRepositoryJDBC;

    public ExcelWriter() {
    }

    /*
        public ExcelWriter(SectionsServiceJDBC service, StoreRepository storeRepository,ClassesRepositoryJDBC classesRepositoryJDBC) {
            this.service = service;
            this.storeRepository = storeRepository;
            this.classesRepositoryJDBC = classesRepositoryJDBC;
        }*/
    @Autowired
    public void setService(SectionsServiceJDBC service) {
        this.service = service;
    }
    @Autowired
    public void setStoreRepository(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }
    @Autowired
    public void setClassesRepositoryJDBC(ClassesRepositoryJDBC classesRepositoryJDBC) {
        this.classesRepositoryJDBC = classesRepositoryJDBC;
    }

    public void write(List<? extends FileInfoDto> filesInfo) throws Exception {
        filesInfo.forEach(fileInfo -> {
            storeRepository.save(fileInfo);
            service.save(fileInfo);
            List<SectionsDto> sections = fileInfo.getSections();
            List<ClassesDto> classes = new ArrayList<>();
            sections.forEach(section -> {
                classes.addAll( section.getClassesDto() );
            });
            classesRepositoryJDBC.batchInsert( classes );
        });

    }
}
