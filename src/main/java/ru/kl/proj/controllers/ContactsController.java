package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kl.proj.dao.ContactsDaoImpl;
import ru.kl.proj.entity.Contacts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class ContactsController {
    @Autowired
    ApplicationContext applicationContext;

    @PostMapping("/contacts")
    public String changeKeywords(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        System.out.println("post /contacts");
        ContactsDaoImpl contactsDao = applicationContext.getBean(ContactsDaoImpl.class);
        Contacts contact = applicationContext.getBean(Contacts.class);
        String addForm = request.getParameter("hiddenField");

//        request.setCharacterEncoding("UTF-8");

        String [] oids = request.getParameterValues("oid");
        String [] cids = request.getParameterValues("cid");
        String [] contacts = request.getParameterValues("contact");

        for (String s : contacts) {
            System.out.println(s);
        }

        /*
        Если в параменте находтся символ "-" то надо удалить соответствующее слово
         */
        int deleteKeyword = 0  ;
        for (int i = 0; i < oids.length; i++) {
            String deleteContactVar = "deleteField" + (i + 1);
            String tmp = request.getParameter(deleteContactVar);
            if (tmp != null && tmp.equals("Удалить")) {
                deleteKeyword = Integer.parseInt(cids [i]);
            }
        }
        if (deleteKeyword > 0) {
            int oid = Integer.parseInt(oids[0]);
            contactsDao.deleteByCid(oid, deleteKeyword);
            return "redirect:/accountMainPage?pageMarker=contacts&apply=true";
        }
        /*
        Если есть разниза между количеством шаблонов в БД и тем сколько пришло из веб то выставляю флаг
        и в дальнейшем этот флаг будет сигнализировать, что нужно обработать последний (новый) шаблон
        не так как остальные
         */
        List listOfContacts = contactsDao.readAllContacts(Integer.parseInt(oids[0]));
        boolean differenceBtwDbAndWeb = false;
        System.out.println(listOfContacts.size() + " " + oids.length);
        if(listOfContacts.size() < oids.length){
            differenceBtwDbAndWeb = true;
        }
//        String addForm = request.getParameter("addForm");

        /*
        Если была добавлено новое слово - то нужно этэто новое слово не update а create
         */
        int iterations;
        if(differenceBtwDbAndWeb){
            iterations = cids.length - 1;
            contact.setOid(Integer.parseInt(oids[iterations]));
            contact.setCid(Integer.parseInt(cids[iterations]));
            contact.setContact(contacts[iterations]);
            System.out.println(contact.getOid() + " " +
                    contact.getCid() + " " +
                    contact.getContact());
            contactsDao.create(contact);
        } else {
            iterations = cids.length;
        }
        for(int i = 0; i < iterations; i++){
            contact.setOid(Integer.parseInt(oids[i]));
            contact.setCid(Integer.parseInt(cids[i]));
            contact.setContact(contacts [i]);
            System.out.println(contact.getOid() + " " +
                    contact.getCid() + " " +
                    contact.getContact());
            contactsDao.update(contact);
        }

        if(addForm != null && addForm.equals("Добавить контакт")){
            return "redirect:/accountMainPage?pageMarker=contacts&apply=true&addForm=true";
        } else {
            return "redirect:/accountMainPage?pageMarker=contacts&apply=true";
        }
    }
}
