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
            for (Entity e: list){
                if(e.getClass().getName().contains("Organization")){
                    this.organization = (Organization) e;
                }
                if(e.getClass().getName().contains("Settings")){
                    this.settings = (Settings) e;
                }
                if(e.getClass().getName().contains("SmsTemplates")){
                    this.smsTemplates = (SmsTemplates) e;
                }
                if(e.getClass().getName().contains("Keywords")){
                    this.keywords = (Keywords) e;
                }
                if(e.getClass().getName().contains("EndlineTemplates")){
                    this.endlineTemplates = (EndlineTemplates) e;
                }
                if(e.getClass().getName().contains("Contacts")){
                    this.contacts = (Contacts) e;
                }
                if(e.getClass().getName().contains("CallsInfo")){
                    this.callsInfo = (CallsInfo) e;
                }
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
