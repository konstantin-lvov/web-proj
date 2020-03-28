package ru.kl.proj.entity;

import java.sql.Date;
import java.time.LocalDate;

public class CallsInfo extends Entity {

    private int oid;
    private int conv_id;
    private Date date;
    private String phoneNumber;
    private String parsedSms;

    public CallsInfo (){

    }

    public CallsInfo(int oid, Date date,
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
