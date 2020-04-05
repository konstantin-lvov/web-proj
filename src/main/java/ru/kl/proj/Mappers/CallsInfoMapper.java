package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.CallsInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CallsInfoMapper implements RowMapper<CallsInfo> {
    @Override
    public CallsInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        CallsInfo callsInfo = new CallsInfo();
        callsInfo.setOid(resultSet.getInt("oid"));
        callsInfo.setConvId(resultSet.getInt("convid"));
        callsInfo.setDate(resultSet.getDate("conversation_date"));
        callsInfo.setPhoneNumber(resultSet.getString("phone_number"));
        callsInfo.setParsedSms(resultSet.getString("parsed_sms"));
        return callsInfo;
    }
}
