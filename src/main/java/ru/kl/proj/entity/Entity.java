package ru.kl.proj.entity;

abstract public class Entity <T> {
    private int oid;
    public int getOid() {
        return oid;
    }
    public void setOid(int oid) {
        this.oid = oid;
    }
}
