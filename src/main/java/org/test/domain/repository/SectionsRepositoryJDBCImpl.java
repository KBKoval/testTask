package org.test.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.test.components.Queries;
import org.test.mapper.SectionsMapper;
import org.test.models.ClassesDto;
import org.test.models.FileInfoDto;
import org.test.models.SectionsDto;
import org.test.services.interfaces.SectionsRepositoryJDBC;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


@Repository
public class SectionsRepositoryJDBCImpl implements SectionsRepositoryJDBC {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final Queries queries;
    private final RowMapper<SectionsDto> rowMapper;

    @Autowired
    public SectionsRepositoryJDBCImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate template, Queries queries) {
        this.jdbcTemplate = jdbcTemplate;
        this.template = template;
        this.queries = queries;
        this.rowMapper = new SectionsMapper();
        this.insert = new SimpleJdbcInsert(template.getJdbcTemplate());
    }

    public Stream<SectionsDto> findAll() {
        String sql = queries.getFindAll();
        return jdbcTemplate.queryForStream(sql,  rowMapper);
    }
// TODO не оптимально. Рассмотреть возможность пакетниой вставки
    @Transactional
    public void save(SectionsDto section){
        Map<String, Object> keys = insert.executeAndReturnKeyHolder(section.toMap()).getKeys();
        String id = keys.get("id").toString();
        findBy(id).orElseThrow(() -> new IllegalStateException("Ошибка записи в таблицу sections !"));
        section.setId(id);
        List<ClassesDto> classes = section.getClassesDto();
        classes.parallelStream().forEach(element -> element.setSectionId(id));
    }
    public Optional<SectionsDto> findBy(String id) {
        String sql = queries.getFindSectionById();
        Map<String, Object> parameters = Collections.singletonMap("id", id);
        return template.queryForStream(sql, parameters, rowMapper).findFirst();
    }
}
