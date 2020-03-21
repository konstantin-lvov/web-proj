package ru.kl.proj.entity;

import java.util.ArrayList;

public class EntityBucket {
    private Organization organization = new Organization();
    private Settings settings = new Settings();

    public EntityBucket(ArrayList<Entity> list) {
        for (Entity e: list){
            if(e.getClass() == organization.getClass()){
                this.organization = (Organization) e;
            }
            if(e.getClass() == settings.getClass()){
                this.settings = (Settings) e;
            }
        }
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
}
