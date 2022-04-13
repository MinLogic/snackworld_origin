package edu.dataworld.snackworld.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value="/index.do")
    public String index() {
        return "redirect:/login/loginForm";
    }

    @RequestMapping(value="/home/main.do")
    public String showMain() {
        return "/home/main.view";
    }
}
