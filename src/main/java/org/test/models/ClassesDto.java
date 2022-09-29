package org.test.models;

import java.util.HashMap;
import java.util.Map;

public class ClassesDto {
    private String id;
    private String className;
    private String code;
private String sectionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("parent_id", this.sectionId);
        parameters.put("class_name", this.className);
        parameters.put("code", this.code);

        return parameters;
    }
    @Override
    public String toString() {
        return "ClassesDto{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
