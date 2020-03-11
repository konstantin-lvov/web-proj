package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import ru.kl.proj.dao.UserDao;
import ru.kl.proj.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

//    @GetMapping("/{username}")
//    public String getAccountMP(@PathVariable("username") String username, Model model){
//        model.addAttribute("user", userDao.readUser(username));
//        return "accountMainPage";
//    }

    @PostMapping("/login")
    public String logining(@ModelAttribute("user") User user, Model model){
        String username = user.getUsername();
        model.addAttribute("username", username);
        System.out.println(user.getUsername());
        return "accountMainPage";
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
    public String addUser(@ModelAttribute("user") User user, HttpServletRequest request, Model model){
        System.out.println("in post registration " + user.getUsername() + " " + user.getPassword());
        userDao.createUser(user);
        try {
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
            System.out.println("exception <---" + e.getMessage());
        }
        String username = user.getUsername();
        model.addAttribute("username", username);
        return "accountMainPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if(error != null) {
            errorMessge = "Username or Password is incorrect !!";
        }
        if(logout != null) {
            errorMessge = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
