package ru.kl.proj.entity;

public class EntityBucket {
    private Organization organization;
    private Settings settings;

    public EntityBucket(Organization organization, Settings settings) {
        this.organization = organization;
        this.settings = settings;
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
