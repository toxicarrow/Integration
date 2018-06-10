package com.main.controller.aController;

import com.main.service.aService.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/adept")
public class aController {
    @Autowired
    private AService aService;

    @RequestMapping("/login")
    public String login(Model model){
        return "/login";
    }
}
