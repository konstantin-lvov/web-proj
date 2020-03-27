package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.KeywordsMapper;
import ru.kl.proj.entity.Keywords;

import java.util.List;

public class KeywordsDaoImpl implements Dao<Keywords> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Keywords> getAll() {
        String sql = "select * from public.keywords;";
        return jdbcTemplate.query(sql,
                new KeywordsMapper());
    }

    @Override
    public void create(Keywords entity) {
        String sql = "insert into public.keywords (oid, keyword) " +
                "values(?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getKeyword());
    }

    @Override
    public Keywords read(String entityProp) {
        int oid = Integer.parseInt(entityProp);
        String sql = "select * from public.keywords where oid = ?";
        return jdbcTemplate.queryForObject(sql,
        new KeywordsMapper(),
        oid);
    }

    @Override
    public void update(Keywords entity) {
        String sql = "update public.keywords " +
                "set keyword = ? where oid = ?;";
        jdbcTemplate.update(sql,
                entity.getKeyword(),
                entity.getOid());
    }

    @Override
    public void delete(String entityProp) {
        int oid = Integer.parseInt(entityProp);
        String sql = "delete from public.keywords where oid = ?;";
        jdbcTemplate.update(sql,
                oid);
    }
}
