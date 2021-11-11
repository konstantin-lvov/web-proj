package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.RecordMapper;
import ru.kl.proj.entity.Record;

import java.util.List;

public class RecordDaoImpl implements Dao<Record> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Record> getAll() {
        String sql = "select * from public.records;";
        return jdbcTemplate.query(sql,
                new RecordMapper());
    }

    @Override
    public void create(Record entity) {
        String sql = "insert into public.records (oid, record_file_name) " +
                "values(?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getRecordFileName());
    }

    public Record readByName(int oid, String recordFileName) {
        String sql = "select * from public.records where oid = ? and record_file_name = ?";
        return jdbcTemplate.queryForObject(sql,
                new RecordMapper(),
                oid,
                recordFileName);
    }

    public Record read(int oid) {
        return null;
    }

    @Override
    public void update(Record entity) {}

    @Override
    public void delete(int oid) {
        String sql = "delete from public.records where oid = ?;";
        jdbcTemplate.update(sql,
                oid);
    }

    public void deleteByRid(int oid, int rid) {
        String sql = "delete from public.records where oid = ? and rid = ?;";
        jdbcTemplate.update(sql, oid, rid);
    }
}
