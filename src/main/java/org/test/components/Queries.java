package org.test.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Queries {
    @Value("${sections.query.find.all}")
    private String findAll;
    @Value("${files.query.insert}")
    private String insertToFile;
    @Value("files.query.find.id")
    private String findFileById;
    @Value("${section.query.find.id}")
    private String findSectionById;
    @Value("${section.query.insert}")
    private String insertToSection;
    @Value("${classes.query.find.all}")
    private String findClassecAll;
    @Value("${classes.query.find.id}")
    private String findClassById;
    @Value("${classes.query.insert}")
    private String inserToClasses;
 @Value("${classes.query.batch.insert}")
    private String insertBatchToClasses;

    public String getFindAll() {
        return findAll;
    }

    public String getInsertToFile() {
        return insertToFile;
    }

    public String getFindFileById() {
        return findFileById;
    }

    public String getFindSectionById() {
        return findSectionById;
    }

    public String getFindClassecAll() {
        return findClassecAll;
    }

    public String getFindClassById() {
        return findClassById;
    }

    public String getInserToClasses() {
        return inserToClasses;
    }

    public String getInsertToSection() {
        return insertToSection;
    }

    public String getInsertBatchToClasses() {
        return insertBatchToClasses;
    }
}
