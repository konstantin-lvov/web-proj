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
        String sql = "insert into public.keywords (oid, kid, keyword) " +
                "values(?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getKid(),
                entity.getKeyword());
    }

    @Override
    public Keywords read(int oid) {
        return null;
    }

    public List<Keywords> readAllKeywords(int oid) {
        String sql = "select * from public.keywords where oid = ? order by kid";
        return jdbcTemplate.query(sql,
                new KeywordsMapper(),
                oid);
    }

    @Override
    public void update(Keywords entity) {
        String sql = "update public.keywords " +
                "set keyword = ? where oid = ? and kid = ?;";
        jdbcTemplate.update(sql,
                entity.getKeyword(),
                entity.getOid(),
                entity.getKid());
    }

    @Override
    public void delete(int oid) {
        String sql = "delete from public.keywords where oid = ?;";
        jdbcTemplate.update(sql,
                oid);
    }

    public void deleteByKid(int oid, int kid) {
        String sql = "delete from public.keywords where oid = ? and kid = ?;";
        jdbcTemplate.update(sql, oid, kid);
    }
}
