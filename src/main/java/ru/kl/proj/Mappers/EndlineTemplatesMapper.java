package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.EndlineTemplates;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EndlineTemplatesMapper implements RowMapper<EndlineTemplates> {
    @Override
    public EndlineTemplates mapRow(ResultSet resultSet, int i) throws SQLException {
        EndlineTemplates endlineTemplates = new EndlineTemplates();
        endlineTemplates.setOid(resultSet.getInt("oid"));
        endlineTemplates.setEtid(resultSet.getInt("etid"));
        endlineTemplates.setEndlineTemplate(resultSet.getString("endlineTemplate"));
        return endlineTemplates;
    }
}
