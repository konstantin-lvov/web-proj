package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.SmsTemplatesMapper;
import ru.kl.proj.entity.SmsTemplates;

import javax.persistence.Access;
import java.util.List;

public class SmsTemplatesDaoImpl implements Dao<SmsTemplates> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    SmsTemplates smsTemplates;

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
    public SmsTemplates read(String entityProp) {
        int oid = Integer.parseInt(entityProp);
        String sql = "select * from public.sms_templates where oid = ?;";
        return jdbcTemplate.queryForObject(sql,
                new SmsTemplatesMapper(),
                oid);
    }

    @Override
    public void update(SmsTemplates entity) {
        String sql = "update public.sms_templates set tid = ?, " +
                "template = ? where oid = ?;";
        jdbcTemplate.update(sql,
                new SmsTemplatesMapper(),
                entity.getTid(),
                entity.getTemplate(),
                entity.getOid());
    }

    @Override
    public void delete(String entityProp) {
        int oid = Integer.parseInt(entityProp);
        String sql = "delete from public.sms_templates where oid = ?;";
        jdbcTemplate.update(sql,
                oid);
    }
}
