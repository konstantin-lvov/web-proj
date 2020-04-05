package ru.kl.proj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kl.proj.entity.Organization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Authentication failed! --> " + error;
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView logining(@ModelAttribute("organization") Organization organization, Model model,
                                 HttpServletRequest request){

        try {
            request.login(organization.getOrganizationName(), organization.getPassword());
        } catch (ServletException e) {
            String error = e.getMessage();
            System.out.println("exception <---" + error);
            return new ModelAndView("redirect:" + "login", "error",
                    error);
        }

        return new ModelAndView("redirect:" + "/");
    }
}
