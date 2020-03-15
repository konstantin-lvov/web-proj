package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.Organization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationMapper implements RowMapper<Organization> {

    @Override
    public Organization mapRow(ResultSet resultSet, int i) throws SQLException {
        Organization organization = new Organization();
        organization.setOid(resultSet.getInt("oid"));
        organization.setOrganizationName(resultSet.getString("organization"));
        organization.setEmail(resultSet.getString("email"));
        organization.setPassword(resultSet.getString("password"));
        organization.setEnabled(resultSet.getBoolean("enabled"));
        organization.setAuthority(resultSet.getString("authority"));
        return organization;
    }
}
