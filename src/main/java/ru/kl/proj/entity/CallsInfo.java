package ru.kl.proj.entity;

public class CallsInfo extends Entity {

    private int oid;
    private String date;
    private String phoneNumber;
    private String parsedSms;

    public CallsInfo (){

    }

    public CallsInfo(int oid, String date,
                     String phoneNumber, String parsedSms) {
        this.oid = oid;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.parsedSms = parsedSms;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getParsedSms() {
        return parsedSms;
    }

    public void setParsedSms(String parsedSms) {
        this.parsedSms = parsedSms;
    }
}
