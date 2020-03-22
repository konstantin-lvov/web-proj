package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.dao.SettingsDaoImpl;
import ru.kl.proj.entity.Entity;
import ru.kl.proj.entity.EntityBucket;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.entity.Settings;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/")
public class SimpleController {


    @Autowired
    private OrganizationDaoImpl organizationDao;

    @Autowired
    private SettingsDaoImpl settingsDao;

    @GetMapping("/")
    public String showHome(HttpServletRequest request, Model model){
        System.out.println("get /");
        String remoteAddr;

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            if (request.getRemoteUser() != null){
                Organization organization = organizationDao.read(request.getRemoteUser());
                Settings settings = new Settings();
                ArrayList <Entity> list = new ArrayList<>();
                list.add(organization);
                list.add(settings);
                EntityBucket entityBucket = new EntityBucket(list);
                model.addAttribute("entityBucket", entityBucket);
            }
            System.out.println(remoteAddr);
        }


        return "index";
    }

//    @GetMapping("/{organizationname}")
//    public String getAccountMP(@PathVariable("organizationname") String organizationname, Model model){
//        model.addAttribute("organization", organizationDao.readorganization(organizationname));
//        return "accountMainPage";
//    }

    @PostMapping("/login")
    public ModelAndView logining(@ModelAttribute("organization") Organization organization, Model model,
                           HttpServletRequest request){

        System.out.println(organization.getOrganizationName() + " " + organization.getPassword());
        try {
            request.login(organization.getOrganizationName(), organization.getPassword());
        } catch (ServletException e) {
            String error = e.getMessage();
            System.out.println("exception <---" + error);
            return new ModelAndView("redirect:" + "login", "error",
                    error);
        }
//        String marker = "orgInfo";
//        return new ModelAndView("redirect:" + "accountMainPage", "pageMarker",
//                marker);

        return new ModelAndView("redirect:" + "/");
    }

    @GetMapping("/accountMainPage")
    public String getAccountMP(HttpServletRequest request, Model model,
                               @RequestParam(value = "pageMarker", required = false) String pageMarker){

        Organization organization;
        organization = organizationDao.read(request.getRemoteUser());

        String oid = String.valueOf(organization.getOid());
        Settings settings = settingsDao.read(oid);

        ArrayList <Entity> list = new ArrayList<>();
        list.add(organization);
        list.add(settings);
        EntityBucket entityBucket = new EntityBucket(list);
        model.addAttribute("pageMarker", pageMarker);
        model.addAttribute("entityBucket", entityBucket);

        return "accountMainPage";
    }

    @GetMapping("/registration")
    public String showForm(Model model) {
        model.addAttribute("organization", new Organization()); //what if organization do not create. garbage collector?
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView addOrganization(@ModelAttribute("organization") Organization organization,
                                  HttpServletRequest request){
        System.out.println("in post registration " + organization.getOrganizationName() + " "
                + organization.getPassword());
        organizationDao.create(organization);
        try {
            request.login(organization.getOrganizationName(), organization.getPassword());
        } catch (ServletException e) {
            System.out.println("exception <---" + e.getMessage());
        }

//        model.addAttribute("organization", organization.getOrganizationName());
//        String message = organization.getOrganizationName();
//        return new ModelAndView("redirect:" + "accountMainPage", "organizationName",
//                message);
        return new ModelAndView("redirect:" + "/");
    }

    @PostMapping("/settings")
    public String changeSettings(@ModelAttribute("entityBucket") EntityBucket entityBucket,
                                 HttpServletRequest request, HttpServletResponse response) throws LoginException {

        Organization organization = entityBucket.getOrganization();

        System.out.println("post /settings");

        System.out.println(organization.getOid() + " " +
                organization.getOrganizationName() + " " +
                organization.getEmail() + " " +
                organization.getPassword() + " " +
                organization.getAuthority() + " " +
                organization.isEnabled());
        organizationDao.update(organization);
        Settings settings = entityBucket.getSettings();
        settings.setOid(organization.getOid());
        System.out.println(settings.getOid() + " " +
                settings.getDeferred() + " " +
                settings.getQuantity() + " " +
                settings.getInterval());
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Authentication failed! --> " + error;
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("logout");
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        System.out.println("logout");
//        return "/";
//    }
}
