package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kl.proj.dao.KeywordsDaoImpl;
import ru.kl.proj.entity.Keywords;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class KeywordsController {

    @Autowired
    ApplicationContext applicationContext;

    @PostMapping("/keywords")
    public String changeKeywords(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        KeywordsDaoImpl keywordsDao = applicationContext.getBean(KeywordsDaoImpl.class);
        String addForm = request.getParameter("hiddenField");

//        request.setCharacterEncoding("UTF-8");

        String [] oids = request.getParameterValues("oid");
        String [] kids = request.getParameterValues("kid");
        String [] keywords = request.getParameterValues("keyword");

        /*
        Если в параменте находтся символ "-" то надо удалить соответствующее слово
         */
        int deleteKeyword = 0  ;
        for (int i = 0; i < kids.length; i++) {
            String deleteKeywordVar = "deleteField" + kids[i];
            String tmp = request.getParameter(deleteKeywordVar);
            if (tmp != null && tmp.equals("Удалить")) {
                deleteKeyword = i + 1;

            }
        }
        if (deleteKeyword > 0) {
            int oid = Integer.parseInt(oids[0]);
            keywordsDao.deleteByKid(oid, deleteKeyword);
            return "redirect:/accountMainPage?pageMarker=keywords&apply=true";
        }
        /*
        Если есть разниза между количеством шаблонов в БД и тем сколько пришло из веб то выставляю флаг
        и в дальнейшем этот флаг будет сигнализировать, что нужно обработать последний (новый) шаблон
        не так как остальные
         */
        List listOfKeywords = keywordsDao.readAllKeywords(Integer.parseInt(oids[0]));
        boolean differenceBtwDbAndWeb = false;
        if(listOfKeywords.size() < oids.length){
            differenceBtwDbAndWeb = true;
        }
//        String addForm = request.getParameter("addForm");

        /*
        Если была добавлено новое слово - то нужно этэто новое слово не update а create
         */
        int iterations;
        if(differenceBtwDbAndWeb){
            iterations = kids.length - 1;
            Keywords keyword = applicationContext.getBean(Keywords.class);
            keyword.setOid(Integer.parseInt(oids[iterations]));
            keyword.setKid(Integer.parseInt(kids[iterations]));
            keyword.setKeyword(keywords[iterations]);
            keywordsDao.create(keyword);
        } else {
            iterations = kids.length;
        }
        for(int i = 0; i < iterations; i++){
            Keywords keyword = applicationContext.getBean(Keywords.class);
            keyword.setOid(Integer.parseInt(oids[i]));
            keyword.setKid(Integer.parseInt(kids[i]));
            keyword.setKeyword(keywords [i]);
            keywordsDao.update(keyword);
        }

        if(addForm != null && addForm.equals("Добавить слово")){
            return "redirect:/accountMainPage?pageMarker=keywords&apply=true&addForm=true";
        } else {
            return "redirect:/accountMainPage?pageMarker=keywords&apply=true";
        }
    }
}
