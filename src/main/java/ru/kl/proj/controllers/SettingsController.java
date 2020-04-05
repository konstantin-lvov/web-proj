package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.dao.SettingsDaoImpl;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.entity.Settings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SettingsController {

    @Autowired
    ApplicationContext applicationContext;


    @PostMapping("/settings")
    public String changeSettings(HttpServletRequest request, HttpServletResponse response) {

        Organization organization = applicationContext.getBean(Organization.class);
        Settings settings = applicationContext.getBean(Settings.class);
        OrganizationDaoImpl organizationDao = applicationContext.getBean(OrganizationDaoImpl.class);
        SettingsDaoImpl settingsDao = applicationContext.getBean(SettingsDaoImpl.class);

        organization.setOid(Integer.parseInt(request.getParameter("oid")));
        organization.setOrganizationName(request.getParameter("organizationName"));
        organization.setEmail(request.getParameter("email"));
        organization.setPassword(request.getParameter("password"));
        organization.setEnabled(Boolean.parseBoolean(request.getParameter("enabled")));
        organization.setAuthority(request.getParameter("authority"));

        organizationDao.update(organization);

        settings.setOid(Integer.parseInt(request.getParameter("oid")));
        settings.setDeferred(Integer.parseInt(request.getParameter("deferred")));
        settings.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        settings.setInterval(Integer.parseInt(request.getParameter("interval")));

        settingsDao.update(settings);

        if (!request.getRemoteUser().equals(organization.getOrganizationName())) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
                return "index";
            }
        }

        return "redirect:/accountMainPage?pageMarker=orgSettings&apply=true";
    }
}
