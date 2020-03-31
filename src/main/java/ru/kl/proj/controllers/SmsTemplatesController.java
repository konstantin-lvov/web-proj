package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        SmsTemplatesDaoImpl smsTemplatesDao = applicationContext.getBean(SmsTemplatesDaoImpl.class);
        String addForm = request.getParameter("hiddenField");

//        request.setCharacterEncoding("UTF-8");

        String [] oids = request.getParameterValues("oid");
        String [] tids = request.getParameterValues("tid");
        String [] templates = request.getParameterValues("template");

        /*
        Если в параменте находтся символ "-" то надо удалить соответствующий шаблон
         */
        int deleteTemplate = 0  ;
        for (int i = 0; i < oids.length; i++) {
            String deleteTemplateVar = "deleteField" + (i + 1);
            String tmp = request.getParameter(deleteTemplateVar);
            if (tmp != null && tmp.equals("Удалить")) {
                deleteTemplate = i + 1;

            }
        }
        if (deleteTemplate > 0) {
            int oid = Integer.parseInt(oids[0]);
            smsTemplatesDao.deleteByTid(oid, deleteTemplate);
            return "redirect:/accountMainPage?pageMarker=smsTemplates&apply=true";
        }
        /*
        Если есть разниза между количеством шаблонов в БД и тем сколько пришло из веб то выставляю флаг
        и в дальнейшем этот флаг будет сигнализировать, что нужно обработать последний (новый) шаблон
        не так как остальные
         */
        List listOfSmsTemplates = smsTemplatesDao.readAllTemplates(Integer.parseInt(oids[0]));
        boolean differenceBtwDbAndWeb = false;
        System.out.println(listOfSmsTemplates.size() + " " + oids.length);
        if(listOfSmsTemplates.size() < oids.length){
            differenceBtwDbAndWeb = true;
        }
//        String addForm = request.getParameter("addForm");

        /*
        Если была добавлена новая форма для шаблона - то нужно эту новую форму не update а create
         */
        int iterations;
        if(differenceBtwDbAndWeb){
            iterations = tids.length - 1;
            SmsTemplates smsTemplates = applicationContext.getBean(SmsTemplates.class);
            smsTemplates.setOid(Integer.parseInt(oids[iterations]));
            smsTemplates.setTid(Integer.parseInt(tids[iterations]));
            smsTemplates.setTemplate(templates[iterations]);
            smsTemplatesDao.create(smsTemplates);
        } else {
            iterations = tids.length;
        }
        for(int i = 0; i < iterations; i++){
            SmsTemplates smsTemplates = applicationContext.getBean(SmsTemplates.class);
            smsTemplates.setOid(Integer.parseInt(oids[i]));
            smsTemplates.setTid(Integer.parseInt(tids[i]));
            smsTemplates.setTemplate(templates [i]);
            System.out.println(smsTemplates.getOid() + " " +
                    smsTemplates.getTid() + " " +
                    smsTemplates.getTemplate());
            smsTemplatesDao.update(smsTemplates);
        }

        if(addForm != null && addForm.equals("Добавить шаблон")){
            return "redirect:/accountMainPage?pageMarker=smsTemplates&apply=true&addForm=true";
        } else {
            return "redirect:/accountMainPage?pageMarker=smsTemplates&apply=true";
        }
    }
}
