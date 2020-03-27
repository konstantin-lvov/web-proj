package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.SmsTemplates;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SmsTemplatesMapper implements RowMapper<SmsTemplates> {

    @Override
    public SmsTemplates mapRow(ResultSet resultSet, int i) throws SQLException {
        SmsTemplates smsTemplates = new SmsTemplates();
        smsTemplates.setOid(resultSet.getInt("oid"));
        smsTemplates.setTid(resultSet.getInt("tid"));
        smsTemplates.setTemplate(resultSet.getString("template"));
        return smsTemplates;
    }
}
