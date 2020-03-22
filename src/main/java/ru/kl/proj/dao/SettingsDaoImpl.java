package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.SettingsMapper;
import ru.kl.proj.entity.Settings;

import java.util.List;

public class SettingsDaoImpl implements Dao<Settings>{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Settings> getAll() {
        String sql = "select * from public.settings;";
        return jdbcTemplate.query(sql, new SettingsMapper());
    }

    @Override
    public void create(Settings entity) {
        String sql = "insert into public.settings (oid, deferred_sms, quantity_sms, interval)" +
                "values(?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getDeferred(),
                entity.getQuantity(),
                entity.getInterval());
    }

    @Override
    public Settings read(String entityProp) {
        int oid = Integer.valueOf(entityProp);
        String sql = "select * from public.settings where oid = ?;";
        return jdbcTemplate.queryForObject(sql,
                new SettingsMapper(),
                oid);
    }

    @Override
    public void update(Settings entity) {
        String sql = "update public.settings set deferred_sms = ?," +
                " quantity_sms = ?, interval = ? where oid = ?;";
        jdbcTemplate.update(sql,
                entity.getDeferred(),
                entity.getQuantity(),
                entity.getInterval(),
                entity.getOid());
    }

    @Override
    public void delete(String entityProp) {
        String sql = "delete from public.settings where oid = ?;";
        jdbcTemplate.update(sql,
                entityProp);

    }
}
