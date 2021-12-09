package ru.kl.proj.mobileControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.services.TokenGenerator;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MobileLoginController {

    @Autowired
    OrganizationDaoImpl organizationDao;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    AuthToken authToken;

    private final String OK = "OK";
    private final String NO_MATCHING = "NO MATCHING";

    /*
    Входящий запрос имеет поля - имя и пароль
     */
    @RequestMapping(value = "/mobileLogin", method = GET, produces = "text/plain;charset=UTF-8")
    public String mobileLogin(@RequestParam(value = "organization", required = true) String organizationName,
                              @RequestParam(value = "password", required = true) String organizationPassword) {

        TokenGenerator tokenGenerator = new TokenGenerator();
        ObjectMapper mapper = new ObjectMapper();

        String resultJSON;
        int oid;
        boolean tokenExisting = false;

        try {
            //если организации не существует то процесс авторизации прекращается по исключению
            Organization organization = organizationDao.readByName(organizationName);
            oid = organization.getOid();
            tokenExisting = authToken.isExist(oid);

            /*
            Если токен существует и пароль совпал то возвращаем json объекта AuthToken
             */
            if (tokenExisting
                    && organizationPassword.equals(organization.getPassword())) {
                try {
                    authToken = authTokenDao.read(oid);
                    String json = mapper.writeValueAsString(authToken);
                    return json;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

            /*
            Если организация существует и пароль совпал то генерируем токен и возвращаем новый
             */
            if (!tokenExisting
                    && organization != null
                    && organizationPassword.equals(organization.getPassword())) {
                String newToken = tokenGenerator.generateNewToken();
                authToken.setOid(organization.getOid());
                authToken.setToken(newToken);
                authTokenDao.create(authToken);
                try {
                    authToken = authTokenDao.read(oid);
                    String json = mapper.writeValueAsString(authToken);
                    return json;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "401";
    }

    @RequestMapping(value="/mobileTokenLogin", method = GET, produces = "text/plain;charset=UTF-8")
    public String mobileTokenLogin(@RequestParam(value = "token", required = true) String token){
        try {
            authToken = authTokenDao.readByToken(token);
            if (authToken.getToken().equals(token)){
                return OK;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return NO_MATCHING;
    }
}
