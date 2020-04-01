package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kl.proj.dao.EndlineTemplatesDaoImpl;
import ru.kl.proj.dao.KeywordsDaoImpl;
import ru.kl.proj.entity.EndlineTemplates;
import ru.kl.proj.entity.Keywords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class EndlineTemplatesController {
    @Autowired
    ApplicationContext applicationContext;

    @PostMapping("/endlines")
    public String changeEndlines(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        System.out.println("post /endlines");
        EndlineTemplatesDaoImpl endlineTemplatesDao = applicationContext.getBean(EndlineTemplatesDaoImpl.class);
        String addForm = request.getParameter("hiddenField");

//        request.setCharacterEncoding("UTF-8");

        String [] oids = request.getParameterValues("oid");
        String [] etids = request.getParameterValues("etid");
        String [] endlines = request.getParameterValues("endline");

        for (String s : endlines) {
            System.out.println(s);
        }

        /*
        Если в параменте находтся символ "-" то надо удалить соответствующее слово
         */
        int deleteKeyword = 0  ;
        for (int i = 0; i < oids.length; i++) {
            String deleteEndlineVar = "deleteField" + (i + 1);
            String tmp = request.getParameter(deleteEndlineVar);
            if (tmp != null && tmp.equals("Удалить")) {
                deleteKeyword = i + 1;

            }
        }
        if (deleteKeyword > 0) {
            int oid = Integer.parseInt(oids[0]);
            endlineTemplatesDao.deleteByEtid(oid, deleteKeyword);
            return "redirect:/accountMainPage?pageMarker=endlines&apply=true";
        }
        /*
        Если есть разниза между количеством шаблонов в БД и тем сколько пришло из веб то выставляю флаг
        и в дальнейшем этот флаг будет сигнализировать, что нужно обработать последний (новый) шаблон
        не так как остальные
         */
        List listOfEndlines = endlineTemplatesDao.readAllByOid(Integer.parseInt(oids[0]));
        boolean differenceBtwDbAndWeb = false;
        System.out.println(listOfEndlines.size() + " " + oids.length);
        if(listOfEndlines.size() < oids.length){
            differenceBtwDbAndWeb = true;
        }
//        String addForm = request.getParameter("addForm");

        /*
        Если была добавлено новое слово - то нужно этэто новое слово не update а create
         */
        int iterations;
        if(differenceBtwDbAndWeb){
            iterations = etids.length - 1;
            EndlineTemplates endlineTemplates = applicationContext.getBean(EndlineTemplates.class);
            endlineTemplates.setOid(Integer.parseInt(oids[iterations]));
            endlineTemplates.setEtid(Integer.parseInt(etids[iterations]));
            endlineTemplates.setEndlineTemplate(endlines[iterations]);
            System.out.println(endlineTemplates.getOid() + " " +
                    endlineTemplates.getEtid() + " " +
                    endlineTemplates.getEndlineTemplate());
            endlineTemplatesDao.create(endlineTemplates);
        } else {
            iterations = etids.length;
        }
        for(int i = 0; i < iterations; i++){
            EndlineTemplates endlineTemplates = applicationContext.getBean(EndlineTemplates.class);
            endlineTemplates.setOid(Integer.parseInt(oids[i]));
            endlineTemplates.setEtid(Integer.parseInt(etids[i]));
            endlineTemplates.setEndlineTemplate(endlines [i]);
            System.out.println(endlineTemplates.getOid() + " " +
                    endlineTemplates.getEtid() + " " +
                    endlineTemplates.getEndlineTemplate());
            endlineTemplatesDao.update(endlineTemplates);
        }

        if(addForm != null && addForm.equals("Добавить слово")){
            return "redirect:/accountMainPage?pageMarker=endlines&apply=true&addForm=true";
        } else {
            return "redirect:/accountMainPage?pageMarker=endlines&apply=true";
        }
    }

}
