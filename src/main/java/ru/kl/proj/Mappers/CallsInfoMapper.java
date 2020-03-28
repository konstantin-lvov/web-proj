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
        callsInfo.setConv_id(resultSet.getInt("conv_id"));
        callsInfo.setDate(resultSet.getDate("date"));
        callsInfo.setPhoneNumber(resultSet.getString("phoneNumber"));
        callsInfo.setParsedSms(resultSet.getString("parsedSms"));
        return callsInfo;
    }
}
