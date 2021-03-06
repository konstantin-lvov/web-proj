package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kl.proj.customExceptions.OrganizationExistException;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.entity.Organization;
import ru.kl.proj.services.DatasetFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {
    @Autowired
    ApplicationContext applicationContext;

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
        DatasetFactory datasetFactory = applicationContext.getBean(DatasetFactory.class);

        try{
            datasetFactory.testCreateDataset(organization);
        } catch (OrganizationExistException e) {
            return new ModelAndView("redirect:/registration?errorMessage=" + e.getMessage());
        }
        try {
            request.login(organization.getOrganizationName(), organization.getPassword());
        } catch (ServletException e) {
            System.out.println("exception <---" + e.getMessage());
        }
        return new ModelAndView("redirect:" + "/");
    }
}
