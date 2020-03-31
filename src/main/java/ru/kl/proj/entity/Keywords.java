package ru.kl.proj.entity;

import org.springframework.context.annotation.Scope;


@Scope("prototype")
public class Keywords extends Entity {

    private int oid;
    private int kid;
    private String keyword;

    public Keywords(int oid, int kid, String keyword) {
        this.oid = oid;
        this.kid = kid;
        this.keyword = keyword;
    }

    public Keywords(){
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
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
