package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.dao.SettingsDaoImpl;
import ru.kl.proj.dao.SmsTemplatesDaoImpl;
import ru.kl.proj.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                               @RequestParam(value = "pageMarker", required = false) String pageMarker){

        Organization organization = organizationDao.readByName(request.getRemoteUser());
        int oid = organization.getOid();

        if (pageMarker == null){
            model.addAttribute("organization", organization);
        } else if (pageMarker.equals("orgSettings")){
            Map <String, Entity> map = new HashMap<>();
            Settings settings = settingsDao.read(oid);
            map.put("organization", organization);
            map.put("settings", settings);
            model.addAttribute("list", map);
        } else if (pageMarker.equals("smsTemplates")){
            SmsTemplatesDaoImpl smsTemplatesDao = applicationContext.getBean(SmsTemplatesDaoImpl.class);
            List<SmsTemplates> smsTemplates = smsTemplatesDao.readAllTemplates(oid);
            model.addAttribute("smsTemplates", smsTemplates);
        }

        model.addAttribute("pageMarker", pageMarker);


        return "accountMainPage";
    }
}
