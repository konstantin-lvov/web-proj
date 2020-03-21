package ru.kl.proj.entity;

public class Settings extends Entity{

    private int oid;
    private int deferred;
    private int quantity;
    private int interval;

    public Settings(int oid, int deferred, int quantity, int interval) {
        this.oid = oid;
        this.deferred = deferred;
        this.quantity = quantity;
        this.interval = interval;
    }

    public Settings() {
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getDeferred() {
        return deferred;
    }

    public void setDeferred(int deferred) {
        this.deferred = deferred;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
