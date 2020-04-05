package ru.kl.proj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SimpleController {

    @GetMapping("/admin")
    public String getAdminPage(){
        return "admin";
    }

    @GetMapping("/someTrouble")
    public String getAccessDenied(@RequestParam(value = "kindOfTrouble", required = true) String kindOfTrouble,
    Model model){
        String message = "Упс!";
        if(kindOfTrouble.equals("accessDenied")){
            message = "Недостаточно прав для доступа к запрашиваемой странице.";
        }
        if (kindOfTrouble.equals("expired")){
            message = "Сессия была закрыта, пожалуйста войдите снова.";
        }
        model.addAttribute("message", message);
        return "someTrouble";
    }
}
