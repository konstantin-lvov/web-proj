package ru.kl.proj.mobileControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.SmsTemplatesDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.SmsTemplates;

import java.util.List;

@RestController
public class MobileSmsTemplatesController {

    @Autowired
    AuthToken authToken;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    SmsTemplates smsTemplates;

    @Autowired
    SmsTemplatesDaoImpl smsTemplatesDao;

    private final String NO_MATCHING = "NO MATCHING";

    @RequestMapping(value = "/mobileSmsTemplates", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getSmsTemplates(@RequestParam(value = "token", required = true) String token) throws JsonProcessingException {
        authToken = authTokenDao.readByToken(token);
        if (authToken != null) {
            List <SmsTemplates> listOfTemplates = smsTemplatesDao.readAllTemplates(authToken.getOid());
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(listOfTemplates);
            return json;
        } else {
            return NO_MATCHING;
        }
    }
}
