package ru.kl.proj.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.kl.proj.dao.*;
import ru.kl.proj.entity.*;
import ru.kl.proj.services.DatasetFactory;

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
    public EndlineTemplates getEndline(){return  new EndlineTemplates();}

    @Bean
    public Keywords getKeywords() {
        return new Keywords();
    }

    @Bean
    public SmsTemplates getSmsTemplates(){
        return new SmsTemplates();
    }

    @Bean
    public Organization getOrganization(){
        return new Organization();
    }

    @Bean
    public Settings getSettings(){
        return new Settings();
    }

    @Bean
    public OrganizationDaoImpl getOrganizationDao(){
        return new OrganizationDaoImpl();
    }

    @Bean
    public SettingsDaoImpl getSettingsDaoImpl(){
        return new SettingsDaoImpl();
    }

    @Bean
    public SmsTemplatesDaoImpl getSmsTemplatesDaoImpl(){
        return new SmsTemplatesDaoImpl();
    }

    @Bean
    public KeywordsDaoImpl getKeywordsDaoImpl(){
        return new KeywordsDaoImpl();
    }

    @Bean
    public EndlineTemplatesDaoImpl getEndlineTemplatesDaoImpl(){
        return new EndlineTemplatesDaoImpl();
    }

    @Bean
    public ContactsDaoImpl getContactsDaoImpl(){
        return new ContactsDaoImpl();
    }

    @Bean
    public CallsInfoDaoImpl getCallsInfoDaoImpl(){
        return new CallsInfoDaoImpl();
    }

    @Bean
    public DatasetFactory getDatasetFactory(){
        return new DatasetFactory();
    }
}
