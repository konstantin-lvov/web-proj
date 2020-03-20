package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.kl.proj.dao.OrganizationDao;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.dao.SettingsDaoImpl;
import ru.kl.proj.entity.EntityBucket;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.entity.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class SimpleController {

    @Autowired
    private OrganizationDaoImpl organizationDao;

    @Autowired
    private SettingsDaoImpl settingsDao;

    @GetMapping("/")
    public String showHome(HttpServletRequest request, Model model){
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
            if (request.getRemoteUser() != null){
                Organization organization = organizationDao.read(request.getRemoteUser());
                Settings settings = new Settings();
                EntityBucket entityBucket = new EntityBucket(organization, settings);
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

        EntityBucket entityBucket = new EntityBucket(organization, settings);
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
                                  HttpServletRequest request, Model model){
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
