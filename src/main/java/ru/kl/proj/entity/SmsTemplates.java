package ru.kl.proj.entity;

public class SmsTemplates extends Entity {

    private int oid;
    private int tid;
    private String template;

    public SmsTemplates(int oid, int tid, String template) {
        this.oid = oid;
        this.tid = tid;
        this.template = template;
    }

    public SmsTemplates(){
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
