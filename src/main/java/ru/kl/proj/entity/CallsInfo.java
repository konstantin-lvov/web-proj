package ru.kl.proj.entity;

import java.sql.Date;
import java.time.LocalDate;

public class CallsInfo extends Entity {

    private int oid;
    private int convId;
    private Date date;
    private String phoneNumber;
    private String parsedSms;

    public CallsInfo (){

    }

    public CallsInfo(int oid, int convId, Date date,
                     String phoneNumber, String parsedSms) {
        this.oid = oid;
        this.convId = convId;
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

    public int getConvId() {
        return convId;
    }

    public void setConvId(int convId) {
        this.convId = convId;
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
