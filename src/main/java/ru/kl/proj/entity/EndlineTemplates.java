package ru.kl.proj.entity;

public class EndlineTemplates extends Entity {

    private int oid;
    private int etid;
    private String endlineTemplate;

    public EndlineTemplates (){

    }

    public EndlineTemplates(int oid, int etid, String endlineTemplate) {
        this.oid = oid;
        this.etid = etid;
        this.endlineTemplate = endlineTemplate;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getEtid() {
        return etid;
    }

    public void setEtid(int etid) {
        this.etid = etid;
    }

    public String getEndlineTemplate() {
        return endlineTemplate;
    }

    public void setEndlineTemplate(String endlineTemplate) {
        this.endlineTemplate = endlineTemplate;
    }
}
