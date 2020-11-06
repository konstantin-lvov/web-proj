package ru.kl.proj.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.kl.proj.dao.AuthTokenDaoImpl;

public class AuthToken extends Entity{

    @Autowired
    private AuthTokenDaoImpl authTokenDao;

    @Autowired
    private ApplicationContext applicationContext;


    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private int oid;

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    private int tokenId;
    private String token;

    public AuthToken (int oid, String token){
        this.oid = oid;
        this.token = token;
    }

    public AuthToken (){

    }

    public boolean isExist(int oid){

        try{
        String token = authTokenDao.read(oid).getToken();

            if(token != null){
                return true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }
}
