package org.test.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.test.components.Queries;
import org.test.mapper.ClassesMapper;

import org.test.models.ClassesDto;

import org.test.services.interfaces.ClassesRepositoryJDBC;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ClassesRepositoryJDBCImpl implements ClassesRepositoryJDBC {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final Queries queries;
    private final RowMapper<ClassesDto> rowMapper;

    @Autowired
    public ClassesRepositoryJDBCImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate template, Queries queries) {
        this.jdbcTemplate = jdbcTemplate;
        this.template = template;
        this.queries = queries;
        this.rowMapper = new ClassesMapper();
        this.insert = new SimpleJdbcInsert(template.getJdbcTemplate());
    }

    public Stream<ClassesDto> findAll() {
        String sql = queries.getFindClassecAll();
        return jdbcTemplate.queryForStream(sql, rowMapper);
    }

    @Transactional
    public void save(ClassesDto classesDto) {
        Map<String, Object> keys = insert.executeAndReturnKeyHolder(classesDto.toMap()).getKeys();
        String id = keys.get("id").toString();
        findBy(id).orElseThrow(() -> new IllegalStateException("Ошибка записи в таблицу classes !"));
        classesDto.setId(id);
    }

    public Optional<ClassesDto> findBy(String id) {
        String sql = queries.getFindClassById();
        Map<String, Object> parameters = Collections.singletonMap("id", id);
        return template.queryForStream(sql, parameters, rowMapper).findFirst();
    }

    @Transactional
    public int[] batchInsert(List<ClassesDto> classes) {
        return this.jdbcTemplate.batchUpdate(
                queries.getInsertBatchToClasses(),
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, classes.get(i).getSectionId());
                        ps.setString(2, classes.get(i).getClassName());
                        ps.setString(3, classes.get(i).getCode());
                    }

                    public int getBatchSize() {
                        return classes.size();
                    }
                });
    }

}
