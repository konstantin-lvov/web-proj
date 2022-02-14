package ru.kl.proj.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kl.proj.dao.*;
import ru.kl.proj.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.List;
import java.util.Map;

@Controller
public class AccountMainPageController {

    Logger logger = LoggerFactory.getLogger(AccountMainPageController.class);

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private OrganizationDaoImpl organizationDao;

    @Autowired
    private SettingsDaoImpl settingsDao;

    @GetMapping("/accountMainPage")
    public String getAccountMP(HttpServletRequest request, Model model,
                               @RequestParam(value = "addForm", required = false) String addForm,
                               @RequestParam(value = "pageMarker", required = false) String pageMarker,
                               @RequestParam(value = "apply", required = false) String apply){

        Organization organization = organizationDao.readByName(request.getRemoteUser());
        int oid = organization.getOid();

        try {
            if (pageMarker == null) {
                model.addAttribute("organization", organization);
            } else if (pageMarker.equals("orgSettings")) {
                Settings settings = settingsDao.read(oid);
                model.addAttribute("organization", organization);
                model.addAttribute("settings", settings);
            } else if (pageMarker.equals("smsTemplates")) {
                SmsTemplatesDaoImpl smsTemplatesDao = applicationContext.getBean(SmsTemplatesDaoImpl.class);
                List<SmsTemplates> listOfSmsTemplates = smsTemplatesDao.readAllTemplates(oid);
                if (addForm != null && addForm.equals("true")) {
                    SmsTemplates smsTemplate = applicationContext.getBean(SmsTemplates.class);
                    smsTemplate.setOid(listOfSmsTemplates.get(0).getOid());
                    smsTemplate.setTid(listOfSmsTemplates.size() + 1);
                    smsTemplate.setTemplate("");
                    listOfSmsTemplates.add(smsTemplate);
                }
                model.addAttribute("smsTemplates", listOfSmsTemplates);
            } else if (pageMarker.equals("keywords")) {
                KeywordsDaoImpl keywordsDao = applicationContext.getBean(KeywordsDaoImpl.class);
                List<Keywords> listOfKeywords = keywordsDao.readAllKeywords(oid);
                if (addForm != null && addForm.equals("true")) {
                    Keywords keywords = applicationContext.getBean(Keywords.class);
                    keywords.setOid(listOfKeywords.get(0).getOid());
                    keywords.setKid(listOfKeywords.size() + 1);
                    keywords.setKeyword("");
                    listOfKeywords.add(keywords);
                }
                model.addAttribute("keywords", listOfKeywords);
            } else if (pageMarker.equals("endlines")) {
                EndlineTemplatesDaoImpl endlineTemplatesDao = applicationContext.getBean(EndlineTemplatesDaoImpl.class);
                List<EndlineTemplates> listOfEndlines = endlineTemplatesDao.readAllByOid(oid);
                if (addForm != null && addForm.equals("true")) {
                    EndlineTemplates endlineTemplates = applicationContext.getBean(EndlineTemplates.class);
                    endlineTemplates.setOid(listOfEndlines.get(0).getOid());
                    endlineTemplates.setEtid(listOfEndlines.size() + 1);
                    endlineTemplates.setEndlineTemplate("");
                    listOfEndlines.add(endlineTemplates);
                }
                model.addAttribute("endlines", listOfEndlines);
            } else if (pageMarker.equals("contacts")) {
                ContactsDaoImpl contactsDao = applicationContext.getBean(ContactsDaoImpl.class);
                List<Contacts> listOfContacts = contactsDao.readAllContacts(oid);
                if (addForm != null && addForm.equals("true")) {
                    Contacts contacts = applicationContext.getBean(Contacts.class);
                    contacts.setOid(listOfContacts.get(0).getOid());
                /*
                что бы создать новую сущность определяем какой номер у последнего в списке
                (он же имеет самый большой номер, т.к. из бд получаются в отсортированном виде)
                 */
                    contacts.setCid(listOfContacts.get(listOfContacts.size() - 1).getCid() + 1);
                    contacts.setContact("");
                    listOfContacts.add(contacts);
                }
                model.addAttribute("contacts", listOfContacts);
            } else if (pageMarker.equals("callsInfo")) {
                CallsInfoDaoImpl callsInfoDao = applicationContext.getBean(CallsInfoDaoImpl.class);
                List<CallsInfo> listOfCallsInfo = callsInfoDao.readAllByOid(oid);
                model.addAttribute("callsInfo", listOfCallsInfo);

            } else if (pageMarker.equals("audioRecord")) {
                RecordDaoImpl recordDao = applicationContext.getBean(RecordDaoImpl.class);
                List<AudioRecord> listOfAudioRecords = recordDao.readAllByOid(oid);
                int size = listOfAudioRecords.size();
                logger.info(String.valueOf(listOfAudioRecords.get(size-1).getOid() + " " +
                        listOfAudioRecords.get(size-1).getRid() + " " +
                        listOfAudioRecords.get(size-1).getRecordFileName()));
                model.addAttribute("audioRecord", listOfAudioRecords);
            }
        } catch (Exception e){
            logger.info(e.getMessage());
        }

        model.addAttribute("pageMarker", pageMarker);
        model.addAttribute("apply", apply);

        return "accountMainPage";
    }
}
