package ru.kl.proj.entity;

public class Record {

    private int oid;
    private int rid;
    private String recordFileName;

    public Record (){}
    public Record(int oid, int rid, String recordFileName) {
        this.oid = oid;
        this.rid = rid;
        this.recordFileName = recordFileName;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRecordFileName() {
        return recordFileName;
    }

    public void setRecordFileName(String recordFileName) {
        this.recordFileName = recordFileName;
    }
}
