package ru.kl.proj.entity;

import java.security.Key;
import java.util.ArrayList;

public class EntityBucket {
    private Organization organization;
    private Settings settings;
    private SmsTemplates smsTemplates;
    private Keywords keywords;
    private EndlineTemplates endlineTemplates;
    private Contacts contacts;
    private CallsInfo callsInfo;


    public EntityBucket(ArrayList<Entity> list) {
        try {
            for (Entity e: list){
                if(e.getClass().getName().equals("Organization")){
                    this.organization = (Organization) e;
                }
                if(e.getClass().getName().equals("Settings")){
                    this.settings = (Settings) e;
                }
                if(e.getClass().getName().equals("SmsTemplates")){
                    this.smsTemplates = (SmsTemplates) e;
                }
                if(e.getClass().getName().equals("Keywords")){
                    this.keywords = (Keywords) e;
                }
                if(e.getClass().getName().equals("EndlineTemplates")){
                    this.endlineTemplates = (EndlineTemplates) e;
                }
                if(e.getClass().getName().equals("Contacts")){
                    this.contacts = (Contacts) e;
                }
                if(e.getClass().getName().equals("CallsInfo")){
                    this.callsInfo = (CallsInfo) e;
                }
            }
        } catch (ClassCastException ex){
            System.out.println(ex.fillInStackTrace());
        }

    }

    public EntityBucket() {
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public SmsTemplates getSmsTemplates() {
        return smsTemplates;
    }

    public void setSmsTemplates(SmsTemplates smsTemplates) {
        this.smsTemplates = smsTemplates;
    }

    public Keywords getKeywords() {
        return keywords;
    }

    public void setKeywords(Keywords keywords) {
        this.keywords = keywords;
    }

    public EndlineTemplates getEndlineTemplates() {
        return endlineTemplates;
    }

    public void setEndlineTemplates(EndlineTemplates endlineTemplates) {
        this.endlineTemplates = endlineTemplates;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public CallsInfo getCallsInfo() {
        return callsInfo;
    }

    public void setCallsInfo(CallsInfo callsInfo) {
        this.callsInfo = callsInfo;
    }
}
