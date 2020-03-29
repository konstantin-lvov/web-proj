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
import ru.kl.proj.entity.Entity;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.entity.Settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class SettingsController {

    @Autowired
    private OrganizationDaoImpl organizationDao;

    @Autowired
    private SettingsDaoImpl settingsDao;

    @PostMapping("/settings")
    public String changeSettings(@ModelAttribute("list") List<Entity> list,
                                 HttpServletRequest request, HttpServletResponse response) {
        System.out.println("post /settings");
        Organization organization = null;
        Settings settings = null;

        for (Entity e: list) {
            if(e.getClass().getName().contains("Organization")){
                organization = (Organization) e;
            }
            if(e.getClass().getName().contains("Settings")){
                settings = (Settings) e;
            }
        }

        organizationDao.update(organization);

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
