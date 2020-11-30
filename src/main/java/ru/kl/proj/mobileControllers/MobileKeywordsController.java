package ru.kl.proj.mobileControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.KeywordsDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.Keywords;

import java.util.List;

@RestController
public class MobileKeywordsController {

    @Autowired
    AuthToken authToken;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    Keywords keywords;

    @Autowired
    KeywordsDaoImpl keywordsDao;

    private final String NO_MATCHING = "NO MATCHING";

    @RequestMapping(value = "/mobileKeywords", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String getKeywords(@RequestParam(value = "token", required = true) String token) throws JsonProcessingException {
        authToken = authTokenDao.readByToken(token);
        if (authToken != null) {
            List <Keywords> listOfKeywords = keywordsDao.readAllKeywords(authToken.getOid());
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(listOfKeywords);
            return json;
        } else {
            return NO_MATCHING;
        }
    }
}
