package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.OrganizationMapper;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.entity.Settings;

import java.util.List;

public class OrganizationDaoImpl implements Dao<Organization> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    OrganizationDaoImpl organizationDao;

    @Autowired
    SettingsDaoImpl settingsDao;

    @Override
    public List<Organization> getAll() {
        String sql = "select * from public.organizations";
        return jdbcTemplate.query(sql, new OrganizationMapper());
    }


    @Override
    public void create(Organization organization) {

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

        organization = organizationDao.read(organization.getOrganizationName());

        Settings settings = new Settings(organization.getOid(), 0, 0, 0);
        settingsDao.create(settings);
    }

    @Override
    public Organization read(String organization) {
        String sql = "select * from public.organizations where organization = ? ";
        return jdbcTemplate.queryForObject(sql, new OrganizationMapper(), organization);
    }

    @Override
    public void update(Organization organization) {
        String sql = "update public.organizations set organization = ?, password = ?, email = ?" +
                " where oid = ?";
        jdbcTemplate.update(sql,
                organization.getOrganizationName(),
                organization.getPassword(),
                organization.getEmail(),
                organization.getOid());
    }

    @Override
    public void delete(String organization) {
        String sql = "delete from public.organizations where organization = ?";
        jdbcTemplate.update(sql, organization);
    }
}
