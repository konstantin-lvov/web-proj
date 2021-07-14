package ru.kl.proj.mobileControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.ContactsDaoImpl;
import ru.kl.proj.dao.EndlineTemplatesDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.Contacts;
import ru.kl.proj.entity.EndlineTemplates;

import java.util.List;

@RestController
public class MobileContactsController {

    @Autowired
    AuthToken authToken;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    Contacts contacts;

    @Autowired
    ContactsDaoImpl contactsDao;

    private final String NO_MATCHING = "NO MATCHING";

    @RequestMapping(value = "/mobileContacts", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getContacts(@RequestParam(value = "token", required = true) String token) throws JsonProcessingException {
        authToken = authTokenDao.readByToken(token);
        if (authToken != null) {
            List<Contacts> listOfContacts = contactsDao.readAllContacts(authToken.getOid());
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(listOfContacts);
            return json;
        } else {
            return NO_MATCHING;
        }
    }
}
