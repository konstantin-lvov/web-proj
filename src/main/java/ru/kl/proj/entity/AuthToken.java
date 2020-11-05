package ru.kl.proj.entity;

public class AuthToken extends Entity{

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
}
