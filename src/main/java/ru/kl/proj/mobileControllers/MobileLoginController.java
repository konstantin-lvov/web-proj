package ru.kl.proj.mobileControllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.services.TokenGenerator;

import java.lang.reflect.Field;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MobileLoginController {

    @Autowired
    OrganizationDaoImpl organizationDao;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    AuthToken authToken;

    private static Logger logger = LogManager.getLogger(MobileLoginController.class);

    /*
    Входящий запрос имеет поля - имя и пароль
     */
    @RequestMapping(value="/mobileLogin", method=GET)
    public String mobileLogin(@RequestParam(value = "organization", required = true) String organizationName,
                        @RequestParam(value = "password", required = true) String organizationPassword){

        logger.info("Info log message");

        TokenGenerator tokenGenerator = new TokenGenerator();
        String existingToken = "";
        int oid;
        boolean tokenExisting = false;

        try{
            //если организации не существует то процесс авторизации прекращается по исключению
            Organization organization = organizationDao.readByName(organizationName);

            oid = organization.getOid();
            tokenExisting = authToken.isExist(oid);

            if(tokenExisting){
                existingToken = authTokenDao.read(organization.getOid()).getToken();
            }

            /*
            Если токен существует и пароль совпал то возвращаем текущий токен
             */
            if(tokenExisting
                    && organizationPassword.equals(organization.getPassword())){
                return existingToken;
            }

            /*
            Если организация существует и пароль совпал то генерируем токен и возвращаем новый
             */
            if (!tokenExisting
                    && organization != null
                    && organizationPassword.equals(organization.getPassword())){
                String newToken = tokenGenerator.generateNewToken();
                authToken.setOid(organization.getOid());
                authToken.setToken(newToken);
                authTokenDao.create(authToken);
                return newToken;
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return "401";
    }
}
