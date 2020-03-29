package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kl.proj.dao.SmsTemplatesDaoImpl;
import ru.kl.proj.entity.SmsTemplates;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SmsTemplatesController {
    @Autowired
    ApplicationContext applicationContext;

    @PostMapping("/smsTemplates")
    public String changeSmsTemplates(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("post /smsTemplates");
        request.setCharacterEncoding("UTF-8");
        String [] oids = request.getParameterValues("oid");
        String [] tids = request.getParameterValues("tid");
        String [] templates = request.getParameterValues("template");
        List <SmsTemplates> listOfTemplates = new ArrayList<>();
        SmsTemplatesDaoImpl smsTemplatesDao = applicationContext.getBean(SmsTemplatesDaoImpl.class);
        for(int i = 0; i < tids.length; i++){
            SmsTemplates smsTemplates = applicationContext.getBean(SmsTemplates.class);
            smsTemplates.setOid(Integer.parseInt(oids[i]));
            smsTemplates.setTid(Integer.parseInt(tids[i]));
            smsTemplates.setTemplate(templates [i]);
            System.out.println(smsTemplates.getOid() + " " +
                    smsTemplates.getTid() + " " +
                    smsTemplates.getTemplate());
            smsTemplatesDao.update(smsTemplates);
        }
        return "accountMainPage";
    }
}
