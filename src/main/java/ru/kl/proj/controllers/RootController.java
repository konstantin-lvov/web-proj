package ru.kl.proj.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kl.proj.dao.OrganizationDaoImpl;
import ru.kl.proj.entity.*;

import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@Controller
public class RootController {

    Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private OrganizationDaoImpl organizationDao;

    @GetMapping("/")
    public String showHome(HttpServletRequest request, Model model){

        log.info("infoAAAAAAAAAAAAAA <-----------------");

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
