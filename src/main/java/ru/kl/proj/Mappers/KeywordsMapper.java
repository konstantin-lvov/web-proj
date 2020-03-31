package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.Keywords;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KeywordsMapper implements RowMapper<Keywords> {
    @Override
    public Keywords mapRow(ResultSet resultSet, int i) throws SQLException {
        Keywords keywords = new Keywords();
        keywords.setOid(resultSet.getInt("oid"));
        keywords.setKid(resultSet.getInt("kid"));
        keywords.setKeyword(resultSet.getString("keyword"));
        return keywords;
    }
}
