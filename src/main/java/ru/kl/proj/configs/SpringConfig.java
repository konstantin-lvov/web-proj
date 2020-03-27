package ru.kl.proj.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.dao.SettingsDaoImpl;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?organizationSll=false");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    public OrganizationDaoImpl getOrganizationDao(){
        return new OrganizationDaoImpl();
    }

    @Bean
    public SettingsDaoImpl getSettingsDaoImpl(){
        return new SettingsDaoImpl();
    }

}
