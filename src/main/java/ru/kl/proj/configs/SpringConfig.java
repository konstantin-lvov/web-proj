package ru.kl.proj.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.kl.proj.dao.*;
import ru.kl.proj.entity.*;
import ru.kl.proj.services.DatasetFactory;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"ru.kl.proj.entity"})
public class SpringConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://summary-db:5432/postgres?organizationSll=false");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    /*
    Entity classes
     */
    @Bean
    public CallsInfo getCallsInfo() {
        return new CallsInfo();
    }

    @Bean
    public Contacts getContacts() {
        return new Contacts();
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
    public AuthToken getAuthToken() { return new AuthToken();}

    @Bean
    public Record getRecord() { return new Record();}

    /*
    DaoImplementation classes
     */

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
    public AuthTokenDaoImpl getAuthTokenDaoImpl(){ return new AuthTokenDaoImpl();}

    @Bean
    public RecordDaoImpl getRecordDaoImpl(){ return new RecordDaoImpl();}

    /*
    other
     */

    @Bean
    public DatasetFactory getDatasetFactory(){
        return new DatasetFactory();
    }


}
