package com.spotify.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @GetMapping(value = "/")
    public ModelAndView showWelcomePage() {
        return new ModelAndView("welcome");
    }


    @GetMapping(value = {"/adminPage"})
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("adminPage");
        return model;
    }

    @GetMapping(value = "/loginPage")
    public ModelAndView loginPage(@RequestParam(value = "error",required = false) String error,
                                  @RequestParam(value = "logout",	required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid Credentials provided.");
        }

        if (logout != null) {
            model.addObject("message", "Logged out from Spotify successfully.");
        }

        model.setViewName("loginPage");
        return model;
    }
}
