package ru.kl.proj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.kl.proj.dao.*;
import ru.kl.proj.entity.*;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Scope("request")
public class DatasetFactory {

    private Organization organization;
    @Autowired
    private OrganizationDaoImpl organizationDao;

    private Settings settings;
    @Autowired
    private SettingsDaoImpl settingsDao;

    private SmsTemplates smsTemplates;
    @Autowired
    private SmsTemplatesDaoImpl smsTemplatesDao;

    private Keywords keywords;
    @Autowired
    private KeywordsDaoImpl keywordsDao;

    private EndlineTemplates endlineTemplates;
    @Autowired
    private EndlineTemplatesDaoImpl endlineTemplatesDao;

    private Contacts contacts;
    @Autowired
    ContactsDaoImpl contactsDao;

    private CallsInfo callsInfo;
    @Autowired
    private CallsInfoDaoImpl callsInfoDao;

    private int oid;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void createDataset(Organization organization){
        if(organization.getAuthority()==null){
            organization.setAuthority("ROLE_ORGANIZATION");
        }
        organization.setEnabled(true);
        organizationDao.create(organization);
        organization = organizationDao.read(organization.getOid());
        this.oid = organization.getOid();

        settings = new Settings(oid, 0, 0, 0);
        settingsDao.create(settings);

        String templateSms = "Здравствуйте, #Имя_клиента#!\n" +
                "Напоминаем Вам о прошлом разговоре:\n" +
                "#Ключевое_слово##Найденое_совпадение##Окончание_строки#\n" +
                "Обратится к нам вы можете по телефону:";
        smsTemplates = new SmsTemplates(oid, 1, templateSms);
        smsTemplatesDao.create(smsTemplates);
        templateSms = "Здравствуйте, #Имя_клиента#!\n" +
                "Напоминаем Вам о прошлом разговоре:\n" +
                "#Ключевое_слово##Найденое_совпадение##Окончание_строки#\n" +
                "Обратится к нам вы можете по телефону:";
        smsTemplates = new SmsTemplates(oid, 2, templateSms);
        smsTemplatesDao.create(smsTemplates);


        String templateEndline = ".";
        endlineTemplates = new EndlineTemplates(oid, 1, templateEndline);
        endlineTemplatesDao.create(endlineTemplates);
    }

    public void testCreateDataset(Organization organization){
        createDataset(organization);

        String keyword = "олово";
        keywords = new Keywords(oid, keyword);
        keywordsDao.create(keywords);

        contacts = new Contacts(oid, "Василий", "Петухов",
                "Вадимович", "85555555555");
        contactsDao.create(contacts);

        Date date = new Date(System.currentTimeMillis());
        String parsedSms = "Здравстуйте Константин рады вам сообщить что" +
                "мы обновили цены на ншу продукцию и готовы предложить вам" +
                "олово по 20 рублей за киллограмм аллюминий 12 рублей за киллограмм...";
        callsInfo = new CallsInfo(oid, date, "85555555555", parsedSms);
        callsInfoDao.create(callsInfo);
    }
}
