package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.ContactsMapper;
import ru.kl.proj.entity.Contacts;

import java.util.List;

public class ContactsDaoImpl implements Dao<Contacts> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Contacts> getAll() {
        String sql = "select * from public.contacts;";
        return jdbcTemplate.query(sql, new ContactsMapper());
    }

    @Override
    public void create(Contacts entity) {
        String sql = "insert into public.contacts " +
                "(oid, name, second_name, third_name, phone_number) " +
                "values(?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getName(),
                entity.getSecondName(),
                entity.getThirdName(),
                entity.getPhoneNumber());
    }

    @Override
    public Contacts read(String entityProp) {
        int oid = Integer.parseInt(entityProp);
        String sql = "select * from public.contacts where oid = ?;";
        return jdbcTemplate.queryForObject(sql,
                new ContactsMapper(),
                oid);
    }

    @Override
    public void update(Contacts entity) {
        String sql = "update public.contacts set name = ?, " +
                "second_name = ?, third_name = ?, phone_number = ? " +
                "where oid = ?;";
        jdbcTemplate.update(sql,
                entity.getName(),
                entity.getSecondName(),
                entity.getThirdName(),
                entity.getPhoneNumber(),
                entity.getOid());
    }

    @Override
    public void delete(String entityProp) {
        int oid = Integer.parseInt(entityProp);
        String sql = "delete from public.contacts where oid = ?;";
        jdbcTemplate.update(sql,
                oid);
    }
}
