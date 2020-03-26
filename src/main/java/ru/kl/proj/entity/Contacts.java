package ru.kl.proj.entity;

public class Contacts extends Entity {

    private int oid;
    private String name;
    private String secondName;
    private String thirdName;
    private String phoneNumber;

    public Contacts(){

    }

    public Contacts(int oid, String name, String secondName,
                    String thirdName, String phoneNumber) {
        this.oid = oid;
        this.name = name;
        this.secondName = secondName;
        this.thirdName = thirdName;
        this.phoneNumber = phoneNumber;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
