package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.AudioRecord;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RecordMapper implements RowMapper<AudioRecord> {

    @Override
    public AudioRecord mapRow(ResultSet resultSet, int i) throws SQLException {
        AudioRecord audioRecord = new AudioRecord();
        audioRecord.setOid(resultSet.getInt("oid"));
        audioRecord.setRid(resultSet.getInt("rid"));
        audioRecord.setRecordFileName(resultSet.getString("record_file_name"));
        return audioRecord;
    }
}
