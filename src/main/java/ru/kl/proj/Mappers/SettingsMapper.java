package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.Settings;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SettingsMapper implements RowMapper<Settings> {

    @Override
    public Settings mapRow(ResultSet resultSet, int i) throws SQLException {
        Settings settings = new Settings();
        settings.setOid(resultSet.getInt("oid"));
        settings.setDeferred(resultSet.getInt("deferred_sms"));
        settings.setQuantity(resultSet.getInt("quantity_sms"));
        settings.setInterval(resultSet.getInt("interval"));
        return settings;
    }
}
