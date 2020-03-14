package ru.kl.proj.dao;

import ru.kl.proj.entity.Organization;

import java.util.List;

public interface OrganizationDao {

    List<Organization> getAllOrganizations();
    void createOrganization(Organization organization);
    Organization readOrganization(String organizationName);
    void updateOrganization(Organization organization);
    void deleteOrganization(String organizationName);
}
