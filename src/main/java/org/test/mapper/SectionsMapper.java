package org.test.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.test.models.SectionsDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionsMapper  implements RowMapper<SectionsDto> {

    public SectionsDto mapRow(ResultSet resultSet, int i) throws SQLException {
        SectionsDto section = new SectionsDto();
        section.setId(resultSet.getString(1));
        section.setSectionName(resultSet.getString(2));
        return section;
    }
}