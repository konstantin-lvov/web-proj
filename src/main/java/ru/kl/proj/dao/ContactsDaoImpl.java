package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.ContactsMapper;
import ru.kl.proj.entity.Contacts;

import java.util.List;

@Scope("request")
public class ContactsDaoImpl implements Dao<Contacts> {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Contacts> getAll() {
        String sql = "select * from public.contacts;";
        return jdbcTemplate.query(sql, new ContactsMapper());
    }

    @Override
    public void create(Contacts entity) {
        Contacts contact = applicationContext.getBean(Contacts.class);
        contact.setOid(entity.getOid());
        contact.setCid(entity.getCid());
        contact.parseContact(entity.getContact());
        System.out.println(contact.getName() + " from contact dao");
        String sql = "insert into public.contacts " +
                "(oid, cid, name, second_name, third_name, phone_number) " +
                "values(?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getCid(),
                contact.getName(),
                contact.getSecondName(),
                contact.getThirdName(),
                contact.getPhoneNumber());
        System.out.println(contact.getPhoneNumber());
    }

    @Override
    public Contacts read(int oid) {
        String sql = "select * from public.contacts where oid = ?;";
        return jdbcTemplate.queryForObject(sql,
                new ContactsMapper(),
                oid);
    }

    public List <Contacts> readAllContacts(int oid) {
        String sql = "select * from public.contacts where oid = ? order by cid;";
        return jdbcTemplate.query(sql,
                new ContactsMapper(),
                oid);
    }

    @Override
    public void update(Contacts entity) {
        Contacts contact = applicationContext.getBean(Contacts.class);
        contact.setOid(entity.getOid());
        contact.setCid(entity.getCid());
        contact.parseContact(entity.getContact());
        String sql = "update public.contacts set name = ?, " +
                "second_name = ?, third_name = ?, phone_number = ? " +
                "where oid = ? and cid = ?;";
        jdbcTemplate.update(sql,
                contact.getName(),
                contact.getSecondName(),
                contact.getThirdName(),
                contact.getPhoneNumber(),
                entity.getOid(),
                entity.getCid());
    }

    @Override
    public void delete(int oid) {
        String sql = "delete from public.contacts where oid = ?;";
        jdbcTemplate.update(sql,
                oid);
    }

    public void deleteByCid(int oid, int cid) {
        String sql = "delete from public.contacts where oid = ? and cid = ?;";
        jdbcTemplate.update(sql, oid, cid);
    }
}
