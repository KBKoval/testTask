package org.test.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.test.models.ClassesDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassesMapper implements RowMapper<ClassesDto> {

    public ClassesDto mapRow(ResultSet resultSet, int i) throws SQLException {
        ClassesDto classesDto = new ClassesDto();
        classesDto.setId(resultSet.getString(1));
        classesDto.setSectionId(resultSet.getString(2));
        classesDto.setClassName(resultSet.getString(3));
        classesDto.setCode(resultSet.getString(4));

        return classesDto;
    }
}