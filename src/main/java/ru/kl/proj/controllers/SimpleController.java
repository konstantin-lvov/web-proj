package ru.kl.proj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SimpleController {

    @GetMapping("/admin")
    public String getAdminPage(){
        System.out.println("get admin");
        return "admin";
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
