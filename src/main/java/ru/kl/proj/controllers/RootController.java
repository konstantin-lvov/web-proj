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
                Organization organization = organizationDao.read(request.getRemoteUser());
                Settings settings = new Settings();
                ArrayList<Entity> list = new ArrayList<>();
                list.add(organization);
                list.add(settings);
                EntityBucket entityBucket = new EntityBucket(list);
                model.addAttribute("entityBucket", entityBucket);
            }
            System.out.println(remoteAddr);
        }

        return "index";
    }
}
