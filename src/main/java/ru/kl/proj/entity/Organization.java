package ru.kl.proj.entity;

public class Organization extends Entity{

    private int oid;
    private String organizationName;
    private String email;
    private String password;
    private boolean enabled;
    private String authority;


    public Organization() {
    }

    public Organization(String organizationName, String email, String password, String authority) {
        this.organizationName = organizationName;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
