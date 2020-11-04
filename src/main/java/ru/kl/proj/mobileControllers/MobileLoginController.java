package ru.kl.proj.mobileControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.entity.Organization;

@Controller
public class MobileLoginController {

    @Autowired
    OrganizationDaoImpl organizationDao;

    @PostMapping("/mobileLogin")
    public String mobileLogin(@RequestParam(value = "organization", required = true) String organizationName,
                        @RequestParam(value = "password", required = true) String organizationPassword){

        Organization organization = organizationDao.readByName(organizationName);
        if (organization != null && organizationPassword.equals(organization.getPassword())){
            return "Success!";
        }

        return "Access denied!";
    }
}
