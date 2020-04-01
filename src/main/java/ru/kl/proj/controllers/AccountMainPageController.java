package ru.kl.proj.controllers;

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

@Controller
public class AccountMainPageController {
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
        System.out.println(addForm + "from accMP");
        if (pageMarker == null){
            model.addAttribute("organization", organization);
        } else if (pageMarker.equals("orgSettings")){
            Settings settings = settingsDao.read(oid);
            model.addAttribute("organization", organization);
            model.addAttribute("settings", settings);
        } else if (pageMarker.equals("smsTemplates")){
            SmsTemplatesDaoImpl smsTemplatesDao = applicationContext.getBean(SmsTemplatesDaoImpl.class);
            List<SmsTemplates> listOfSmsTemplates = smsTemplatesDao.readAllTemplates(oid);
            if(addForm != null && addForm.equals("true")){
                SmsTemplates smsTemplate = applicationContext.getBean(SmsTemplates.class);
                smsTemplate.setOid(listOfSmsTemplates.get(0).getOid());
                smsTemplate.setTid(listOfSmsTemplates.size()+1);
                smsTemplate.setTemplate("");
                listOfSmsTemplates.add(smsTemplate);
            }
            model.addAttribute("smsTemplates", listOfSmsTemplates);
        } else if (pageMarker.equals("keywords")){
            KeywordsDaoImpl keywordsDao = applicationContext.getBean(KeywordsDaoImpl.class);
            List<Keywords> listOfKeywords = keywordsDao.readAllKeywords(oid);
            if(addForm != null && addForm.equals("true")){
                Keywords keywords = applicationContext.getBean(Keywords.class);
                keywords.setOid(listOfKeywords.get(0).getOid());
                keywords.setKid(listOfKeywords.size()+1);
                keywords.setKeyword("");
                listOfKeywords.add(keywords);
            }
            model.addAttribute("keywords", listOfKeywords);
        } else if (pageMarker.equals("endlines")){
            EndlineTemplatesDaoImpl endlineTemplatesDao = applicationContext.getBean(EndlineTemplatesDaoImpl.class);
            List<EndlineTemplates> listOfEndlines = endlineTemplatesDao.readAllByOid(oid);
            if(addForm != null && addForm.equals("true")){
                EndlineTemplates endlineTemplates = applicationContext.getBean(EndlineTemplates.class);
                endlineTemplates.setOid(listOfEndlines.get(0).getOid());
                endlineTemplates.setEtid(listOfEndlines.size()+1);
                endlineTemplates.setEndlineTemplate("");
                listOfEndlines.add(endlineTemplates);
            }
            model.addAttribute("endlines", listOfEndlines);
        }

        model.addAttribute("pageMarker", pageMarker);
        model.addAttribute("apply", apply);

        return "accountMainPage";
    }
}
