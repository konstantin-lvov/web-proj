package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.entity.Organization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

    @Autowired
    private OrganizationDaoImpl organizationDao;

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
        return new ModelAndView("redirect:" + "/");
    }
}
