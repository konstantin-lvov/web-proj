package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.EndlineTemplatesMapper;
import ru.kl.proj.entity.EndlineTemplates;

import java.util.List;

public class EndlineTemplatesDaoImpl implements Dao<EndlineTemplates> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<EndlineTemplates> getAll() {
        String sql = "select * from public.endline_template;";
        return jdbcTemplate.query(sql, new EndlineTemplatesMapper());
    }

    @Override
    public void create(EndlineTemplates entity) {
        String sql = "insert into public.endline_template " +
                "(oid, etid, endline_template) values(?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getEtid(),
                entity.getEndlineTemplate());
    }

    @Override
    public EndlineTemplates read(int oid) {
        String sql = "select * from public.endline_template " +
                "where oid = ?;";
        return jdbcTemplate.queryForObject(sql,
                new EndlineTemplatesMapper(),
                oid);
    }

    @Override
    public void update(EndlineTemplates entity) {
        String sql = "update public.endline_template " +
                "set endline_template = ? where oid = ? and etid = ?;";
        jdbcTemplate.update(sql,
                entity.getEndlineTemplate(),
                entity.getOid(),
                entity.getEtid());
    }

    @Override
    public void delete(int etid) {
        String sql = "delete from public.endline_template where etid = ?;";
        jdbcTemplate.update(sql,
                etid);
    }
}
