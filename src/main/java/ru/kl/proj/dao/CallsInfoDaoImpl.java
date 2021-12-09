package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.CallsInfoMapper;
import ru.kl.proj.entity.CallsInfo;

import java.util.List;

public class CallsInfoDaoImpl implements Dao<CallsInfo> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CallsInfo> getAll() {
        String sql = "select * from public.calls_info;";
        return jdbcTemplate.query(sql,
                new CallsInfoMapper());
    }

    @Override
    public void create(CallsInfo entity) {
        String sql = "insert into public.calls_info " +
                "(oid, conversation_date, parsed_sms) " +
                "values(?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getDate(),
                entity.getParsedSms());
    }

    @Override
    public CallsInfo read(int conv_id) {
        String sql = "select * from public.calls_info where convid = ?;";
        return jdbcTemplate.queryForObject(sql,
                new CallsInfoMapper(),
                conv_id);
    }

    public List <CallsInfo> readAllByOid(int oid) {
        String sql = "select * from public.calls_info where oid = ? order by convid;";
        return jdbcTemplate.query(sql,
                new CallsInfoMapper(),
                oid);
    }

    @Override
    public void update(CallsInfo entity) {
        String sql = "update public.calls_info set conversation_date = ?, " +
                "phone_number = ?, parsed_sms = ? where convid = ?;";
        jdbcTemplate.update(sql,
                entity.getDate(),
                entity.getPhoneNumber(),
                entity.getParsedSms(),
                entity.getConvId());
    }

    @Override
    public void delete(int conv_id) {
        String sql = "delete from public.calls_info where convid = ?;";
        jdbcTemplate.update(sql,
                conv_id);
    }

    public void deleteByConvid(int oid, int convId) {
        String sql = "delete from public.calls_info where oid = ? and convid = ?;";
        jdbcTemplate.update(sql, oid, convId);
    }
}
