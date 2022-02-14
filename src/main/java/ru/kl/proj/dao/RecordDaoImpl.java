package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.CallsInfoMapper;
import ru.kl.proj.Mappers.RecordMapper;
import ru.kl.proj.entity.AudioRecord;
import ru.kl.proj.entity.CallsInfo;

import java.util.List;

public class RecordDaoImpl implements Dao<AudioRecord> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<AudioRecord> getAll() {
        String sql = "select * from public.records;";
        return jdbcTemplate.query(sql,
                new RecordMapper());
    }

    @Override
    public void create(AudioRecord entity) {
        String sql = "insert into public.records (oid, record_file_name) " +
                "values(?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getRecordFileName());
    }

    public AudioRecord readByName(int oid, String recordFileName) {
        String sql = "select * from public.records where oid = ? and record_file_name = ?";
        return jdbcTemplate.queryForObject(sql,
                new RecordMapper(),
                oid,
                recordFileName);
    }

    public List <AudioRecord> readAllByOid(int oid) {
        String sql = "select * from public.records where oid = ? order by rid;";
        return jdbcTemplate.query(sql,
                new RecordMapper(),
                oid);
    }

    public AudioRecord read(int oid) {
        return null;
    }

    @Override
    public void update(AudioRecord entity) {}

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
