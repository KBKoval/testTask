package org.test.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.test.components.Queries;
import org.test.mapper.FileInfoMapper;
import org.test.models.FileInfoDto;
import org.test.services.interfaces.StoreRepository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
public class StoreRepositoryImpl implements StoreRepository {
    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert insert;
    private final Queries queries;
    private final FileInfoMapper fileInfoMapper = new FileInfoMapper();

    @Autowired
    public StoreRepositoryImpl(NamedParameterJdbcTemplate template, Queries queries) {
        this.template = template;
        this.queries = queries;
        this.insert = new SimpleJdbcInsert(template.getJdbcTemplate());
        this.insert.setTableName("files_storage");
        this.insert.usingGeneratedKeyColumns("id");

    }

    @Transactional
    public void save(FileInfoDto fileInfo) {
        Map<String, Object> keys = insert.executeAndReturnKeyHolder(fileInfo.toMap()).getKeys();
        String id = keys.get("id").toString();
        findBy(id).orElseThrow(() -> new IllegalStateException("Ошибка записи в таблицу files_store !"));
        fileInfo.setId(id);
    }

    public Optional<FileInfoDto> findBy(String id) {
        String sql = queries.getFindFileById();
        Map<String, Object> parameters = Collections.singletonMap("id", id);
        return template.queryForStream(sql, parameters, fileInfoMapper).findFirst();
    }

}
