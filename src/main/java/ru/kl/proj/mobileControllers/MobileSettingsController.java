package ru.kl.proj.mobileControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.SettingsDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.Settings;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MobileSettingsController {

    @Autowired
    AuthToken authToken;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    Settings settings;

    @Autowired
    SettingsDaoImpl settingsDao;

    private final String NO_MATCHING = "NO MATCHING";

    @RequestMapping(value = "/mobileSettings", method = GET, produces = "text/plain;charset=UTF-8")
    public String getSettings(@RequestParam(value = "token", required = true) String token) throws JsonProcessingException {
        authToken = authTokenDao.readByToken(token);
        if (authToken != null){
            settings = settingsDao.read(authToken.getOid());
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(settings);
            return json;
        } else {
            return NO_MATCHING;
        }
    }
}
