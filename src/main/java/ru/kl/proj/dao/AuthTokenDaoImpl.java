package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.AuthTokenMapper;
import ru.kl.proj.Mappers.CallsInfoMapper;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.CallsInfo;

import java.util.List;

public class AuthTokenDaoImpl implements Dao<AuthToken>{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AuthToken authToken;

    @Override
    public List<AuthToken> getAll() {
        String sql = "select * from public.auth_token;";
        return jdbcTemplate.query(sql,
                new AuthTokenMapper());
    }

    @Override
    public void create(AuthToken entity) {
        String sql = "insert into public.auth_token " +
                "(oid, token) " +
                "values(?, ?);";
        jdbcTemplate.update(sql,
                entity.getOid(),
                entity.getToken());
    }

    @Override
    public AuthToken read(int oid) {
        String sql = "select * from public.auth_token where oid = ?;";
        AuthToken newAuthToken = jdbcTemplate.queryForObject(sql,
                new AuthTokenMapper(),
                oid);
        authToken.setOid(newAuthToken.getOid());
        authToken.setToken(newAuthToken.getToken());
        authToken.setTokenId(newAuthToken.getTokenId());
        return authToken;
    }

    @Override
    public void update(AuthToken entity) {
        String sql = "update public.auth_token set token = ? where tokenId = ?;";
        jdbcTemplate.update(sql,
                entity.getToken(),
                entity.getTokenId());
    }


    @Override
    public void delete(int tokenId) {
        String sql = "delete from public.auth_token where tokenId = ?;";
        jdbcTemplate.update(sql,
                tokenId);
    }

//    public AuthToken readByOid(int oid) {
//        String sql = "select * from public.auth_token where oid = ?;";
//        return jdbcTemplate.queryForObject(sql,
//                new AuthTokenMapper(),
//                oid);
//    }

    public void deleteByTokenId(int oid, int tokenId) {
        String sql = "delete from public.auth_token where oid = ? and tokenId = ?;";
        jdbcTemplate.update(sql, oid, tokenId);
    }
}
