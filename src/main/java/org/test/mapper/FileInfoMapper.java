package org.test.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.test.models.FileInfoDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FileInfoMapper implements RowMapper<FileInfoDto> {

    public FileInfoDto mapRow(ResultSet resultSet, int i) throws SQLException {
        FileInfoDto fileInfo = new FileInfoDto();
        fileInfo.setId(resultSet.getString(1));
        fileInfo.setCreatedDate(resultSet.getString(2));
        fileInfo.setAuthor(resultSet.getString(3));
        fileInfo.setSize(resultSet.getLong(4));
        fileInfo.setFileName(resultSet.getString(5));

        return fileInfo;
    }
}
