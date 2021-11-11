package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.Record;
import ru.kl.proj.entity.Settings;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RecordMapper implements RowMapper<Record> {

    @Override
    public Record mapRow(ResultSet resultSet, int i) throws SQLException {
        Record record = new Record();
        record.setOid(resultSet.getInt("oid"));
        record.setRid(resultSet.getInt("rid"));
        record.setRecordFileName(resultSet.getString("record_file_name"));
        return record;
    }
}
