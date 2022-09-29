package org.test.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileInfoDto {
    private String id;
    private String createdDate;
    private String author;
    private byte[] context;
    private String fileName;
    private long size;
    private List<SectionsDto> sections;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public byte[] getContext() {
        return context;
    }

    public void setContext(byte[] context) {
        this.context = context;
    }

    public List<SectionsDto> getSections() {
        return sections;
    }

    public void setSections(List<SectionsDto> sections) {
        this.sections = sections;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", this.author);
        parameters.put("context", this.context);
        parameters.put("size", this.size);
        parameters.put("file_name", this.fileName);

        return parameters;
    }

    @Override
    public String toString() {
        return "FileInfoDto{" +
                "id='" + id + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
