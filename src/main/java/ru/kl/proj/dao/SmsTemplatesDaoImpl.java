package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.SmsTemplatesMapper;
import ru.kl.proj.entity.SmsTemplates;

import java.util.List;

public class SmsTemplatesDaoImpl implements Dao<SmsTemplates> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<SmsTemplates> getAll() {
        String sql = "select * from public.sms_templates;";
        return jdbcTemplate.query(sql, new SmsTemplatesMapper());
    }

    @Override
    public void create(SmsTemplates entity) {
        String sql = "insert into public.sms_templates " +
                "(oid, tid, template) values(?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getTid(),
                entity.getTemplate());
    }

    @Override
    public SmsTemplates read(int entityProp) {
        return null;
    }


    public List<SmsTemplates> readAllTemplates(int oid) {
        String sql = "select * from public.sms_templates where oid = ? order by tid;";
        return jdbcTemplate.query(sql,
                new SmsTemplatesMapper(),
                oid);
    }

    @Override
    public void update(SmsTemplates entity) {
        String sql = "update public.sms_templates set " +
                "template = ? where oid = ? and tid = ?;";
        jdbcTemplate.update(sql,
                entity.getTemplate(),
                entity.getOid(),
                entity.getTid());
    }

    @Override
    public void delete(int entityProp) {

    }

    public void deleteByTid(int oid, int tid) {
        String sql = "delete from public.sms_templates where oid = ? and tid = ?;";
        jdbcTemplate.update(sql, oid, tid);
    }
}
