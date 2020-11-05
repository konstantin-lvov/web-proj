package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.CallsInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthTokenMapper implements RowMapper<AuthToken> {
    @Override
    public AuthToken mapRow(ResultSet resultSet, int i) throws SQLException {
        AuthToken authToken = new AuthToken();
        authToken.setOid(resultSet.getInt("oid"));
        authToken.setTokenId(resultSet.getInt("tokenId"));
        authToken.setToken(resultSet.getString("token"));
        return authToken;
    }
}
