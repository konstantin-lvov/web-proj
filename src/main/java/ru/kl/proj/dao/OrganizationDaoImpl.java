package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.OrganizationMapper;
import ru.kl.proj.entity.Organization;

import java.util.List;

public class OrganizationDaoImpl implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Organization> getAllOrganizations() {
        String sql = "select * from public.organizations";
        return jdbcTemplate.query(sql, new OrganizationMapper());
    }


    @Override
    public void createOrganization(Organization organization) {
        if(organization.getAuthority()==null){
            organization.setAuthority("ROLE_ORGANIZATION");
        }
        organization.setEnabled(true);

        System.out.println(organization.getOrganizationName() + " " + organization.getPassword() + " " +
                organization.getAuthority() + " " + organization.isEnabled());

        String sql = "insert into public.organizations (organization, email, password, enabled, authority) " +
                "values(?, ?, ?, ?, ?)";
        int tmpa =jdbcTemplate.update(sql,
                organization.getOrganizationName(),
                organization.getEmail(),
                organization.getPassword(),
                organization.isEnabled(),
                organization.getAuthority());

        System.out.println(tmpa + " first query");
    }

    @Override
    public Organization readOrganization(String organization) {
        String sql = "select * from public.organizations where organization = ? ";
        return jdbcTemplate.queryForObject(sql, new OrganizationMapper(), organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        String sql = "update public.organizations set password = ?, email = ?" +
                " where organization = ?";
        jdbcTemplate.update(sql, organization.getPassword(), organization.getEmail(),
                organization.getOrganizationName());
    }

    @Override
    public void deleteOrganization(String organization) {
        String sql = "delete from public.organizations where organization = ?";
        jdbcTemplate.update(sql, organization);
    }
}
