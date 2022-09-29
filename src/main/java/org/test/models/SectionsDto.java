package org.test.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SectionsDto {
    private String id;
    private String name;

    private String idFile;

    private List<ClassesDto> geologicalClasses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSectionName() {
        return name;
    }

    public void setSectionName(String sectionName) {
        this.name = sectionName;
    }

    public List<ClassesDto> getClassesDto() {
        return geologicalClasses;
    }

    public void setClassesDto(List<ClassesDto> classesDto) {
        this.geologicalClasses = classesDto;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("section_name", this.name);
        parameters.put("id_file", this.idFile);

        return parameters;
    }

    @Override
    public String toString() {
        return "SectionsDto{" +
                "id='" + id + '\'' +
                ", sectionName='" + name + '\'' +
                ", classesDto=" + geologicalClasses +
                '}';
    }
}
