package ru.kl.proj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class RootController {

    @Autowired
    private OrganizationDaoImpl organizationDao;

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
                Organization organization = organizationDao.readByName(request.getRemoteUser());
                model.addAttribute("organization", organization);
            }
            System.out.println(remoteAddr);
        }

        return "index";
    }
}
