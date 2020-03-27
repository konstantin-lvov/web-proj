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
                "(oid, conv_id, conversation_date, phone_number, parsed_sms) " +
                "values(?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getConv_id(),
                entity.getDate(),
                entity.getPhoneNumber(),
                entity.getParsedSms());
    }

    @Override
    public CallsInfo read(String entityProp) {
        int conv_id = Integer.parseInt(entityProp);
        String sql = "select * from public.calls_info where conv_id = ?;";
        return jdbcTemplate.queryForObject(sql,
                new CallsInfoMapper(),
                conv_id);
    }

    @Override
    public void update(CallsInfo entity) {
        String sql = "update public.calls_info set conversation_date = ?, " +
                "phone_number = ?, parsed_sms = ? where conv_id = ?;";
        jdbcTemplate.update(sql,
                entity.getDate(),
                entity.getPhoneNumber(),
                entity.getParsedSms(),
                entity.getConv_id());
    }

    @Override
    public void delete(String entityProp) {
        int conv_id = Integer.parseInt(entityProp);
        String sql = "delete from public.calls_info where conv_id = ?;";
        jdbcTemplate.update(sql,
                conv_id);
    }
}
