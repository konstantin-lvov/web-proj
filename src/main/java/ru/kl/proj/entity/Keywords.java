package ru.kl.proj.entity;

public class Keywords extends Entity {

    private int oid;
    private String keyword;

    public Keywords(int oid, String keyword) {
        this.oid = oid;
        this.keyword = keyword;
    }

    public Keywords(){
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
