package ru.kl.proj.entity;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Contacts extends Entity {

    private int oid;
    private int cid;
    private String name;
    private String secondName;
    private String thirdName;
    private String phoneNumber;
    private String contact;

    public Contacts(){

    }

    public Contacts(int oid, int cid, String contact) {
        this.oid = oid;
        this.cid = cid;
        this.contact = contact;

    }

    public int getOid() {
        return oid;
    }
/*
Если пустые строки не присвоить то создастся дубликат
 */
    public void parseContact(String contact){
        if(contact.equals("")){
            this.name = "";
            this.secondName = "";
            this.thirdName = "";
            this.phoneNumber = "";
        } else {
            String [] parsedTmpStr = contact.split("\\s");
            for (int i = 0; i < parsedTmpStr.length; i++) {
                switch (i) {
                    case 0:
                        name = parsedTmpStr[i];
                        break;
                    case 1:
                        secondName = parsedTmpStr[i];
                        break;
                    case 2:
                        thirdName = parsedTmpStr[i];
                        break;
                    case 3:
                        phoneNumber = parsedTmpStr[i];
                        break;
                }

            }
        }
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
