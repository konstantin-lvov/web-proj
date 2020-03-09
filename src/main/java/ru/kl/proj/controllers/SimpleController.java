package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kl.proj.dao.UserDao;
import ru.kl.proj.entity.User;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class SimpleController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserDao userDao;

    @GetMapping("/")
    public String showHome(){
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        System.out.println(remoteAddr);
        return "index";
    }

    @GetMapping("/accountMainPage")
    public String getAccountMP(){
        return "accountMainPage";
    }

    @GetMapping("/registration")
    public String showForm(Model model) {
        model.addAttribute("user", new User()); //what if user do not create. garbage collector?
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user){
        System.out.println("yup");
        System.out.println(user.getUsername());
        userDao.createUser(user);
        return "redirect:/accountMainPage";
    }
}
