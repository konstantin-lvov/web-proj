package ru.kl.proj.entity;

public class CallsInfo extends Entity {

    private int oid;
    private int conv_id;
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

    public int getConv_id() {
        return conv_id;
    }

    public void setConv_id(int conv_id) {
        this.conv_id = conv_id;
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
