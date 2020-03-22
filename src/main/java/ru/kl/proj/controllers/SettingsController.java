package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.dao.SettingsDaoImpl;
import ru.kl.proj.entity.EntityBucket;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.entity.Settings;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SettingsController {

    @Autowired
    private OrganizationDaoImpl organizationDao;

    @Autowired
    private SettingsDaoImpl settingsDao;

    @PostMapping("/settings")
    public String changeSettings(@ModelAttribute("entityBucket") EntityBucket entityBucket,
                                 HttpServletRequest request, HttpServletResponse response) {
        System.out.println("post /settings");

        Organization organization = entityBucket.getOrganization();
        organizationDao.update(organization);

        Settings settings = entityBucket.getSettings();
        settings.setOid(organization.getOid());
        settingsDao.update(settings);

        if (!request.getRemoteUser().equals(organization.getOrganizationName())) {
            System.out.println("organizationName was changed");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
                return "index";
            }
        }

        return "accountMainPage";
    }
}
